package com.groupTuAnh.repository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.groupTuAnh.dto.PageResponse;
import com.groupTuAnh.dto.ReaderDetailsResponse;
import com.groupTuAnh.dto.ReaderSearchRequest;
import com.groupTuAnh.model.Book;
import com.groupTuAnh.model.Reader;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Component
public class SearchRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PageResponse<?> getReadersWithFilterByManyColumnAndSortBy(ReaderSearchRequest request) {
        StringBuilder query = new StringBuilder("SELECT r FROM Reader r WHERE 1=1 ");

        if (StringUtils.hasText(request.getKeyword())) {
            query.append(" AND (LOWER(r.userProfile.name) LIKE LOWER(:keyword) ");
            query.append(" OR LOWER(r.studentCode) LIKE LOWER(:keyword)) ");
        }

        // Filter by membership
        if (request.getMemberShipId() != null) {
            query.append(" AND r.membership.packageId = :memberShipId ");
        }

        // Filter by min, max balance
        if (request.getMinBalance() != null) {
            query.append(" AND r.balance >= :minBalance ");
        }

        if (request.getMaxBalance() != null) {
            query.append(" AND r.balance <= :maxBalance ");
        }

        // Add sorting
        if (StringUtils.hasText(request.getSortBy())) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(asc|desc)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(request.getSortBy());

            if (matcher.find()) {
                String field = matcher.group(1);
                String direction = matcher.group(3).toUpperCase();

                // Validate field name to prevent SQL injection
                if (isValidSortField(field)) {
                    query.append(String.format(" ORDER BY r.userProfile.%s %s", field, direction));
                }
            }
        }

        // Set pagination parameters
        int pageNo = request.getPageNo();
        int pageSize = request.getPageSize();

        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        int offset = (pageNo - 1) * pageSize;

        Query selectQuery = entityManager.createQuery(query.toString());
        selectQuery.setFirstResult(offset);
        selectQuery.setMaxResults(pageSize);

        // Set parameters
        if (StringUtils.hasText(request.getKeyword())) {
            selectQuery.setParameter("keyword", "%" + request.getKeyword() + "%");
        }
        if (request.getMemberShipId() != null) {
            selectQuery.setParameter("memberShipId", request.getMemberShipId());
        }
        if (request.getMinBalance() != null) {
            selectQuery.setParameter("minBalance", request.getMinBalance());
        }
        if (request.getMaxBalance() != null) {
            selectQuery.setParameter("maxBalance", request.getMaxBalance());
        }

        List<Reader> readers = selectQuery.getResultList();

        List<ReaderDetailsResponse> details = readers.stream()
                .map(r -> ReaderDetailsResponse.builder()
                        .phone(r.getUserProfile().getPhone())
                        .name(r.getUserProfile().getName())
                        .code(r.getStudentCode())
                        .memberShipName(r.getMembership() != null ? r.getMembership().getName() : "N/A")
                        .expiryDate(r.getExpMembership())
                        .balance(r.getBalance())
                        .debt(r.getDebt())
                        .build())
                .toList();

        // Count query - use the same WHERE conditions as the main query
        StringBuilder countSql = new StringBuilder("SELECT COUNT(DISTINCT r) FROM Reader r WHERE 1=1 ");
        if (StringUtils.hasText(request.getKeyword())) {
            countSql.append(" AND (LOWER(r.userProfile.name) LIKE LOWER(:keyword) ");
            countSql.append(" OR LOWER(r.studentCode) LIKE LOWER(:keyword) ");
            countSql.append(" OR LOWER(r.userProfile.phone) LIKE LOWER(:keyword)) ");
        }
        if (request.getMemberShipId() != null) {
            countSql.append(" AND r.membership.packageId = :memberShipId ");
        }
        if (request.getMinBalance() != null) {
            countSql.append(" AND r.balance >= :minBalance ");
        }
        if (request.getMaxBalance() != null) {
            countSql.append(" AND r.balance <= :maxBalance ");
        }

        Query countQuery = entityManager.createQuery(countSql.toString());
        if (StringUtils.hasText(request.getKeyword())) {
            countQuery.setParameter("keyword", "%" + request.getKeyword() + "%");
        }
        if (request.getMemberShipId() != null) {
            countQuery.setParameter("memberShipId", request.getMemberShipId());
        }
        if (request.getMinBalance() != null) {
            countQuery.setParameter("minBalance", request.getMinBalance());
        }
        if (request.getMaxBalance() != null) {
            countQuery.setParameter("maxBalance", request.getMaxBalance());
        }

        long totalItems = (long) countQuery.getSingleResult();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .items(details)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .build();


    }

    /**
     * Search books for reader by keyword (title or author) and filter by category.
     * Sorting format: field:asc|desc (e.g., title:asc, stockQuantity:desc). No pagination.
     */
    public List<Book> searchBooksForReader(String keyword, String categoryName, String sortBy) {
        StringBuilder jpql = new StringBuilder("SELECT DISTINCT b FROM Book b ");
        jpql.append(" LEFT JOIN b.authors a ");
        jpql.append(" LEFT JOIN b.categories c ");
        jpql.append(" WHERE b.isDeleted = false ");

        if (StringUtils.hasText(keyword)) {
            jpql.append(" AND (LOWER(b.title) LIKE LOWER(:kw) OR LOWER(a.name) LIKE LOWER(:kw)) ");
        }

        if (StringUtils.hasText(categoryName)) {
            jpql.append(" AND LOWER(c.name) = LOWER(:category) ");
        }

        // Sorting
        String orderBy = " ORDER BY b.title ASC";
        if (StringUtils.hasText(sortBy)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(asc|desc)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                String field = matcher.group(1);
                String direction = matcher.group(3).toUpperCase();
                if (isValidBookSortField(field)) {
                    orderBy = String.format(" ORDER BY b.%s %s", field, direction);
                }
            }
        }
        jpql.append(orderBy);

        Query q = entityManager.createQuery(jpql.toString());
        if (StringUtils.hasText(keyword)) {
            q.setParameter("kw", "%" + keyword + "%");
        }
        if (StringUtils.hasText(categoryName)) {
            q.setParameter("category", categoryName);
        }

        List<Book> books = q.getResultList();
        return books;
    }

    /**
     * Validate sort field to prevent SQL injection
     */
    private boolean isValidSortField(String field) {
        // Only allow specific fields that exist in UserProfile
        return field != null && (field.equals("name") || field.equals("phone"));
    }

    private boolean isValidBookSortField(String field) {
        return field != null && (field.equals("title") || field.equals("stockQuantity"));
    }
}