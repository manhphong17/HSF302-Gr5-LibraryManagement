-- =============================================
-- LIBRARY MANAGEMENT SYSTEM - TEST DATA
-- =============================================

-- 1. INSERT USERS
INSERT INTO users (user_id, username, password, role, active) VALUES
(1, 'admin', 'admin123', 'MANAGER', true),
(2, 'reader001', 'reader123', 'READER', true),
(3, 'reader002', 'reader456', 'READER', true);

-- 2. INSERT USER PROFILES
INSERT INTO user_profiles (user_id, name, phone, address, avatar) VALUES
(1, 'Nguyễn Văn Admin', '0123456789', '123 Đường ABC, Quận 1, TP.HCM', 'admin.jpg'),
(2, 'Trần Thị Sinh Viên', '0987654321', '456 Đường XYZ, Quận 2, TP.HCM', 'student1.jpg'),
(3, 'Lê Văn Học Sinh', '0369258147', '789 Đường DEF, Quận 3, TP.HCM', 'student2.jpg');

-- 3. INSERT MANAGERS
INSERT INTO managers (user_id, name, employee_id) VALUES
(1, 'Nguyễn Văn Admin', 'EMP001');

-- 4. INSERT MEMBERSHIP PACKAGES
INSERT INTO membership_packages (package_id, name, price, max_books_per_month) VALUES
(1, 'Gói Cơ Bản', 50000, 3),
(2, 'Gói Tiêu Chuẩn', 100000, 5),
(3, 'Gói Cao Cấp', 200000, 10);

-- 5. INSERT READERS
INSERT INTO readers (user_id, balance, debt, student_id, membership_package_id) VALUES
(2, 150000, 0, 'SV001', 2),
(3, 50000, 0, 'SV002', 1);

-- 6. INSERT AUTHORS
INSERT INTO authors (author_id, name) VALUES
(1, 'Robert C. Martin'),
(2, 'Joshua Bloch'),
(3, 'Martin Fowler'),
(4, 'Eric Evans'),
(5, 'Nguyễn Văn Tác Giả');

-- 7. INSERT CATEGORIES
INSERT INTO categories (category_id, name) VALUES
(1, 'Lập Trình'),
(2, 'Khoa Học'),
(3, 'Văn Học'),
(4, 'Kinh Tế'),
(5, 'Lịch Sử');

-- 8. INSERT BOOKS
INSERT INTO books (book_id, title, stock_quantity) VALUES
(1, 'Clean Code', 5),
(2, 'Effective Java', 3),
(3, 'Refactoring', 4),
(4, 'Domain-Driven Design', 2),
(5, 'Truyện Kiều', 10),
(6, 'Lịch Sử Việt Nam', 8);

-- 9. INSERT BOOK_AUTHORS
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 5);

-- 10. INSERT BOOK_CATEGORIES (Many-to-Many relationship)
INSERT INTO book_categories (book_id, category_id) VALUES
(1, 1), -- Clean Code -> Lập Trình
(2, 1), -- Effective Java -> Lập Trình
(3, 1), -- Refactoring -> Lập Trình
(4, 1), -- Domain-Driven Design -> Lập Trình
(5, 3), -- Truyện Kiều -> Văn Học
(6, 5), -- Lịch Sử Việt Nam -> Lịch Sử
-- Example of books with multiple categories
(1, 2), -- Clean Code -> Khoa Học (also)
(2, 2); -- Effective Java -> Khoa Học (also)

-- 11. INSERT PENALTY RATES
INSERT INTO penalty_rates (id, effective_date, fine_per_day) VALUES
(1, '2024-01-01', 5000.0),
(2, '2024-06-01', 7000.0),
(3, '2024-12-01', 10000.0);

-- 12. INSERT BORROW RECORDS
INSERT INTO borrow_records (record_id, user_id, book_id, borrow_date, due_date, return_date, is_returned) VALUES
(1, 2, 1, '2024-01-15', '2024-01-22', '2024-01-20', true),
(2, 2, 2, '2024-01-20', '2024-01-27', '2024-01-30', true),
(3, 2, 3, '2024-02-01', '2024-02-08', '2024-02-15', true),
(4, 2, 4, '2024-02-10', '2024-02-17', NULL, false),
(5, 3, 5, '2024-01-25', '2024-02-01', '2024-02-05', true),
(6, 3, 6, '2024-02-05', '2024-02-12', NULL, false);

-- 13. INSERT PENALTIES
INSERT INTO penalties (penalty_id, record_id, user_id, rate_id, total_fine, is_paid, fine_snapshot) VALUES
(1, 2, 2, 1, 15000.0, false, 5000.0),
(2, 3, 2, 1, 35000.0, false, 5000.0),
(3, 5, 3, 1, 20000.0, false, 5000.0);

-- 14. INSERT TRANSACTIONS
INSERT INTO transactions (transaction_id, user_id, manager_id, type, amount, date, description) VALUES
(1, 2, 1, 'DEPOSIT', 200000, '2024-01-01', 'Nạp tiền vào tài khoản'),
(2, 3, 1, 'DEPOSIT', 100000, '2024-01-01', 'Nạp tiền vào tài khoản'),
(3, 2, 1, 'MEMBERSHIP', 100000, '2024-01-01', 'Mua gói Tiêu Chuẩn'),
(4, 3, 1, 'MEMBERSHIP', 50000, '2024-01-01', 'Mua gói Cơ Bản'),
(5, 2, 1, 'PENALTY', 15000, '2024-01-30', 'Thanh toán phạt trả muộn record 2'),
(6, 2, 1, 'PENALTY', 35000, '2024-02-15', 'Thanh toán phạt trả muộn record 3'),
(7, 3, 1, 'PENALTY', 20000, '2024-02-05', 'Thanh toán phạt trả muộn record 5');

-- 15. UPDATE READER BALANCE & DEBT
UPDATE readers SET balance = balance - 15000 - 35000, debt = 0 WHERE user_id = 2;
UPDATE readers SET balance = balance - 20000, debt = 0 WHERE user_id = 3;

-- 16. UPDATE PENALTIES AS PAID
UPDATE penalties SET is_paid = true WHERE penalty_id IN (1, 2, 3);