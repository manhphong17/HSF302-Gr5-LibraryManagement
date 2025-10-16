package com.groupTuAnh.service;

import com.groupTuAnh.dto.PageResponse;
import com.groupTuAnh.dto.ReaderDetailsResponse;
import com.groupTuAnh.dto.ReaderSearchRequest;
import com.groupTuAnh.model.Reader;
import com.groupTuAnh.repository.ReaderRepository;
import com.groupTuAnh.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final SearchRepository searchRepository;

    public List<ReaderDetailsResponse> getAll() {
        List<Reader> readers = readerRepository.findAll();
        return null;
    }

    public PageResponse<?> getAllByMember(ReaderSearchRequest req) {
        return searchRepository.getReadersWithFilterByManyColumnAndSortBy(req);
    }
}
