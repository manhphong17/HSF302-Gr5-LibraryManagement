-- Seed authors
INSERT INTO authors (name)
VALUES ('J.K. Rowling');
INSERT INTO authors (name)
VALUES ('George Orwell');

-- Seed categories
INSERT INTO categories (name)
VALUES ('Fantasy');
INSERT INTO categories (name)
VALUES ('Dystopian');

-- Seed books
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('Harry Potter and the Sorcerer''s Stone', 10, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('1984', 5, false);

-- Link books with authors
INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'Harry Potter and the Sorcerer''s Stone'
  AND a.name = 'J.K. Rowling';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = '1984'
  AND a.name = 'George Orwell';

-- Link books with categories
INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'Harry Potter and the Sorcerer''s Stone'
  AND c.name = 'Fantasy';

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = '1984'
  AND c.name = 'Dystopian';

-- Seed book items
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('HP-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'Harry Potter and the Sorcerer''s Stone';

INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('HP-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'Harry Potter and the Sorcerer''s Stone';

INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('NINETEEN84-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = '1984';

-- More seed authors
INSERT INTO authors (name)
VALUES ('J.R.R. Tolkien');
INSERT INTO authors (name)
VALUES ('Harper Lee');
INSERT INTO authors (name)
VALUES ('Suzanne Collins');
INSERT INTO authors (name)
VALUES ('Dan Brown');

-- More seed categories
INSERT INTO categories (name)
VALUES ('Classic');
INSERT INTO categories (name)
VALUES ('Science Fiction');
INSERT INTO categories (name)
VALUES ('Adventure');
INSERT INTO categories (name)
VALUES ('Thriller');

-- More seed books
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('The Hobbit', 8, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('To Kill a Mockingbird', 7, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('Animal Farm', 6, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('The Hunger Games', 12, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('The Da Vinci Code', 9, false);

-- Link new books with authors
INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'The Hobbit'
  AND a.name = 'J.R.R. Tolkien';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'To Kill a Mockingbird'
  AND a.name = 'Harper Lee';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'Animal Farm'
  AND a.name = 'George Orwell';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'The Hunger Games'
  AND a.name = 'Suzanne Collins';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'The Da Vinci Code'
  AND a.name = 'Dan Brown';

-- Link new books with categories
INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'The Hobbit'
  AND c.name IN ('Fantasy', 'Adventure', 'Classic');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'To Kill a Mockingbird'
  AND c.name IN ('Classic');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'Animal Farm'
  AND c.name IN ('Dystopian', 'Classic');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'The Hunger Games'
  AND c.name IN ('Dystopian', 'Science Fiction', 'Adventure');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'The Da Vinci Code'
  AND c.name IN ('Thriller');

-- More book items for each new book
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('HOB-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Hobbit';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('HOB-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Hobbit';

INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('TKAM-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'To Kill a Mockingbird';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('TKAM-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'BORROWED'
FROM books b
WHERE b.title = 'To Kill a Mockingbird';

INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('AF-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'Animal Farm';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('AF-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'Animal Farm';

INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('THG-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Hunger Games';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('THG-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Hunger Games';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('THG-', LPAD(b.book_id, 4, '0'), '-003'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Hunger Games';

INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('TDVC-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Da Vinci Code';

-- Membership packages
INSERT INTO membership_packages (name, price, max_books_per_month)
VALUES ('Basic', 0, 3);
INSERT INTO membership_packages (name, price, max_books_per_month)
VALUES ('Premium', 99000, 10);
INSERT INTO membership_packages (name, price, max_books_per_month)
VALUES ('Student', 49000, 6);

-- Penalty policies
INSERT INTO penalty_policies (effective_date, fine_per_day)
VALUES ('2024-01-01', 2000);
INSERT INTO penalty_policies (effective_date, fine_per_day)
VALUES ('2025-01-01', 3000);

-- Seed accounts for testing (added back)
INSERT INTO accounts (username, password, role, active)
VALUES ('admin', '000000', 'LIBRIAN', true);
INSERT INTO accounts (username, password, role, active)
VALUES ('librarian2', '123456', 'LIBRIAN', true);
INSERT INTO accounts (username, password, role, active)
VALUES ('reader', '000000', 'READER', true);
INSERT INTO accounts (username, password, role, active)
VALUES ('reader2', '123456', 'READER', true);

-- Seed user profiles mapped to accounts
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
SELECT a.account_id, 'Thủ thư 1', '0900000001', 'Hồ Chí Minh', NULL
FROM accounts a
WHERE a.username = 'admin';
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
SELECT a.account_id, 'Thủ thư 2', '0900000002', 'Hà Nội', NULL
FROM accounts a
WHERE a.username = 'librarian2';
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
SELECT a.account_id, 'Bạn đọc 1', '0910000001', 'Đà Nẵng', NULL
FROM accounts a
WHERE a.username = 'reader';
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
SELECT a.account_id, 'Bạn đọc 2', '0910000002', 'Cần Thơ', NULL
FROM accounts a
WHERE a.username = 'reader2';

-- Seed subclass rows for JOINED inheritance
-- Librarians
INSERT INTO librarians (account_id, staff_code)
SELECT a.account_id, 'LB001'
FROM accounts a
WHERE a.username = 'admin';
INSERT INTO librarians (account_id, staff_code)
SELECT a.account_id, 'LB002'
FROM accounts a
WHERE a.username = 'librarian2';

-- Readers (associate Student and Basic packages)
INSERT INTO readers (account_id, balance, debt, student_code, membership_package_id, start_date, exp_membership)
SELECT a.account_id, 0.0, 0.0, 'SV001', mp.package_id, '2025-10-16', '2025-10-23'
FROM accounts a
         LEFT JOIN membership_packages mp ON mp.name = 'Basic'
WHERE a.username = 'reader';

INSERT INTO readers (account_id, balance, debt, student_code, membership_package_id, start_date, exp_membership)
SELECT a.account_id, 0.0, 0.0, 'SV002', mp.package_id, '2025-10-16', '2025-10-23'
FROM accounts a
         LEFT JOIN membership_packages mp ON mp.name = 'Student'
WHERE a.username = 'reader2';

-- Seed borrow records for testing view borrowed books
-- Borrowed (not returned) by user 'reader' for book item HP-0001-001
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-10-14',
       '2025-10-21',
       NULL,
       false
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code = 'HP-0001-001'
WHERE a.username = 'reader';

-- Mark the borrowed item status accordingly
UPDATE book_items
SET status = 'BORROWED'
WHERE bar_code = 'HP-0001-001';

-- Returned record by user 'reader2' for item NINETEEN84-0002-001
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-10-01',
       '2025-10-08',
       '2025-10-07',
       true
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code = 'NINETEEN84-0002-001'
WHERE a.username = 'reader2';

-- Ensure returned item is available
UPDATE book_items
SET status = 'AVAILABLE'
WHERE bar_code = 'NINETEEN84-0002-001';

-- Seed penalties for testing
-- Overdue penalty for 'reader' on HP-0001-001 (7 days late x policy 3000đ/day snapshot)
INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       21000,
       false,
       3000,
       7
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
WHERE a.username = 'reader'
  AND br.book_item_id = (SELECT id FROM book_items WHERE bar_code = 'HP-0001-001');

-- Returned/paid penalty example for 'reader2' (no days late but mark LOST)
INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       50000,
       true,
       3000,
       0
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
WHERE a.username = 'reader2'
  AND br.book_item_id = (SELECT id FROM book_items WHERE bar_code = 'NINETEEN84-0002-001');


-- Thêm nhiều tác giả mới
INSERT INTO authors (name)
VALUES ('Jane Austen');
INSERT INTO authors (name)
VALUES ('Mark Twain');
INSERT INTO authors (name)
VALUES ('F. Scott Fitzgerald');
INSERT INTO authors (name)
VALUES ('Agatha Christie');
INSERT INTO authors (name)
VALUES ('Stephen King');
INSERT INTO authors (name)
VALUES ('J.D. Salinger');

-- Thêm nhiều thể loại mới
INSERT INTO categories (name)
VALUES ('Romance');
INSERT INTO categories (name)
VALUES ('Mystery');
INSERT INTO categories (name)
VALUES ('Horror');
INSERT INTO categories (name)
VALUES ('Literary Fiction');

-- Thêm nhiều sách mới
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('Pride and Prejudice', 11, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('The Adventures of Huckleberry Finn', 9, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('The Great Gatsby', 8, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('And Then There Were None', 10, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('The Shining', 7, false);
INSERT INTO books (title, stock_quantity, is_deleted)
VALUES ('The Catcher in the Rye', 12, false);

-- Liên kết sách mới với tác giả
INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'Pride and Prejudice'
  AND a.name = 'Jane Austen';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'The Adventures of Huckleberry Finn'
  AND a.name = 'Mark Twain';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'The Great Gatsby'
  AND a.name = 'F. Scott Fitzgerald';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'And Then There Were None'
  AND a.name = 'Agatha Christie';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'The Shining'
  AND a.name = 'Stephen King';

INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE b.title = 'The Catcher in the Rye'
  AND a.name = 'J.D. Salinger';

-- Liên kết sách mới với thể loại
INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'Pride and Prejudice'
  AND c.name IN ('Romance', 'Classic');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'The Adventures of Huckleberry Finn'
  AND c.name IN ('Adventure', 'Classic');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'The Great Gatsby'
  AND c.name IN ('Literary Fiction', 'Classic');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'And Then There Were None'
  AND c.name IN ('Mystery', 'Thriller');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'The Shining'
  AND c.name IN ('Horror');

INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE b.title = 'The Catcher in the Rye'
  AND c.name IN ('Literary Fiction', 'Classic');

-- Thêm các mục sách mới cho từng sách
-- Pride and Prejudice
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('P&P-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'Pride and Prejudice';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('P&P-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'BORROWED'
FROM books b
WHERE b.title = 'Pride and Prejudice';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('P&P-', LPAD(b.book_id, 4, '0'), '-003'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'Pride and Prejudice';

-- The Adventures of Huckleberry Finn
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('HF-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Adventures of Huckleberry Finn';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('HF-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Adventures of Huckleberry Finn';

-- The Great Gatsby
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('GG-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Great Gatsby';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('GG-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'BORROWED'
FROM books b
WHERE b.title = 'The Great Gatsby';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('GG-', LPAD(b.book_id, 4, '0'), '-003'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Great Gatsby';

-- And Then There Were None
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('ATTTWN-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'And Then There Were None';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('ATTTWN-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'And Then There Were None';

-- The Shining
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('TS-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Shining';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('TS-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Shining';

-- The Catcher in the Rye
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('CIR-', LPAD(b.book_id, 4, '0'), '-001'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Catcher in the Rye';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('CIR-', LPAD(b.book_id, 4, '0'), '-002'), b.book_id, 'BORROWED'
FROM books b
WHERE b.title = 'The Catcher in the Rye';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('CIR-', LPAD(b.book_id, 4, '0'), '-003'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Catcher in the Rye';
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('CIR-', LPAD(b.book_id, 4, '0'), '-004'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'The Catcher in the Rye';

-- Thêm các gói thành viên mới
INSERT INTO membership_packages (name, price, max_books_per_month)
VALUES ('VIP', 199000, 20);
INSERT INTO membership_packages (name, price, max_books_per_month)
VALUES ('Family', 149000, 15);

-- Thêm chính sách phạt mới
INSERT INTO penalty_policies (effective_date, fine_per_day)
VALUES ('2026-01-01', 4000);

-- Thêm tài khoản mới cho testing
INSERT INTO accounts (username, password, role, active)
VALUES ('reader3', '000000', 'READER', true);
INSERT INTO accounts (username, password, role, active)
VALUES ('reader4', '123456', 'READER', true);
INSERT INTO accounts (username, password, role, active)
VALUES ('librarian3', '789012', 'LIBRIAN', true);

-- Thêm hồ sơ người dùng mới
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
SELECT a.account_id, 'Bạn đọc 3', '0920000003', 'Hải Phòng', NULL
FROM accounts a
WHERE a.username = 'reader3';
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
SELECT a.account_id, 'Bạn đọc 4', '0920000004', 'Nha Trang', NULL
FROM accounts a
WHERE a.username = 'reader4';
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
SELECT a.account_id, 'Thủ thư 3', '0930000003', 'Đà Lạt', NULL
FROM accounts a
WHERE a.username = 'librarian3';

-- Thêm thủ thư mới
INSERT INTO librarians (account_id, staff_code)
SELECT a.account_id, 'LB003'
FROM accounts a
WHERE a.username = 'librarian3';

-- Thêm bạn đọc mới (gán gói VIP và Family)
INSERT INTO readers (account_id, balance, debt, student_code, membership_package_id, start_date, exp_membership)
SELECT a.account_id, 50000.0, 0.0, 'SV003', mp.package_id, '2025-10-16', '2025-12-16'
FROM accounts a
         LEFT JOIN membership_packages mp ON mp.name = 'VIP'
WHERE a.username = 'reader3';

INSERT INTO readers (account_id, balance, debt, student_code, membership_package_id, start_date, exp_membership)
SELECT a.account_id, 0.0, 10000.0, 'SV004', mp.package_id, '2025-10-16', '2025-11-16'
FROM accounts a
         LEFT JOIN membership_packages mp ON mp.name = 'Family'
WHERE a.username = 'reader4';

-- Thêm hồ sơ mượn sách mới cho testing
-- Mượn chưa trả bởi reader3 cho P&P-0007-002 (giả sử book_id của Pride and Prejudice là 7)
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-10-15',
       '2025-10-22',
       NULL,
       false
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code LIKE 'P&P-%-002'
WHERE a.username = 'reader3';

-- Cập nhật trạng thái item
UPDATE book_items
SET status = 'BORROWED'
WHERE bar_code LIKE 'P&P-%-002';

-- Mượn chưa trả bởi reader4 cho GG-0008-002 (giả sử book_id của The Great Gatsby là 8)
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-10-10',
       '2025-10-17',
       NULL,
       false
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code LIKE 'GG-%-002'
WHERE a.username = 'reader4';

-- Cập nhật trạng thái item
UPDATE book_items
SET status = 'BORROWED'
WHERE bar_code LIKE 'GG-%-002';

-- Mượn đã trả bởi reader3 cho HOB-0003-001 (giả sử book_id của The Hobbit là 3)
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-09-20',
       '2025-09-27',
       '2025-09-26',
       true
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code LIKE 'HOB-%-001'
WHERE a.username = 'reader3';

-- Đảm bảo item đã trả là AVAILABLE
UPDATE book_items
SET status = 'AVAILABLE'
WHERE bar_code LIKE 'HOB-%-001';

-- Thêm phạt mới cho testing
-- Phạt quá hạn cho reader4 trên GG-0008-002 (5 ngày muộn x 3000đ/ngày)
INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       15000,
       false,
       3000,
       5
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.id = br.book_item_id
WHERE a.username = 'reader4'
  AND bi.bar_code LIKE 'GG-%-002';

-- Phạt đã trả cho reader3 trên HOB-0003-001 (0 ngày muộn nhưng phạt mất mát)
INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       30000,
       true,
       3000,
       0
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.id = br.book_item_id
WHERE a.username = 'reader3'
  AND bi.bar_code LIKE 'HOB-%-001';

-- Thêm mượn khác cho reader1 (reader) với CIR-0009-002 (giả sử book_id của The Catcher in the Rye là 9)
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-10-12',
       '2025-10-19',
       NULL,
       false
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code LIKE 'CIR-%-002'
WHERE a.username = 'reader';

UPDATE book_items
SET status = 'BORROWED'
WHERE bar_code LIKE 'CIR-%-002';

-- Phạt quá hạn cho reader1 trên CIR-0009-002 (3 ngày muộn)
INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       9000,
       false,
       3000,
       3
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.id = br.book_item_id
WHERE a.username = 'reader'
  AND bi.bar_code LIKE 'CIR-%-002';



-- Thêm sách đã mượn mới cho reader ('reader' - Bạn đọc 1)
-- Mượn chưa trả: The Da Vinci Code item TDVC-0005-001 (giả sử book_id=5)
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-10-10',
       '2025-10-17',
       NULL,
       false
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code = 'TDVC-0005-001'
WHERE a.username = 'reader';

-- Cập nhật trạng thái item
UPDATE book_items
SET status = 'BORROWED'
WHERE bar_code = 'TDVC-0005-001';

-- Mượn đã trả: The Hobbit item HOB-0003-002 (giả sử book_id=3)
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-09-25',
       '2025-10-02',
       '2025-10-01',
       true
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code = 'HOB-0003-002'
WHERE a.username = 'reader';

-- Đảm bảo item đã trả là AVAILABLE
UPDATE book_items
SET status = 'AVAILABLE'
WHERE bar_code = 'HOB-0003-002';

-- Mượn chưa trả khác: To Kill a Mockingbird item TKAM-0004-002 (đã borrowed, nhưng thêm borrow mới? Chờ, giả sử thêm item mới nếu cần, nhưng dùng cái available)
-- Thêm item mới cho TKAM nếu cần, nhưng để đơn giản, dùng một item available giả sử
-- Giả sử có TKAM-0004-001 available
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT('TKAM-', LPAD(b.book_id, 4, '0'), '-003'), b.book_id, 'AVAILABLE'
FROM books b
WHERE b.title = 'To Kill a Mockingbird';

INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-10-13',
       '2025-10-20',
       NULL,
       false
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code = 'TKAM-0004-003'
WHERE a.username = 'reader';

UPDATE book_items
SET status = 'BORROWED'
WHERE bar_code = 'TKAM-0004-003';

-- Thêm phiếu phạt mới cho reader
-- Phạt quá hạn cho TDVC-0005-001 (6 ngày muộn x 3000đ/ngày, ngày hiện tại 2025-10-16, due 10-17 nhưng borrow 10-10, overdue từ 10-17 nhưng hiện tại chưa overdue? Điều chỉnh borrow_date để overdue
-- Điều chỉnh borrow_date để overdue: borrow '2025-10-05', due '2025-10-12', hiện tại 10-16 -> 4 ngày muộn
UPDATE borrow_records
SET borrow_date = '2025-10-05',
    due_date    = '2025-10-12'
WHERE reader_id = (SELECT account_id FROM accounts WHERE username = 'reader')
  AND book_item_id = (SELECT id FROM book_items WHERE bar_code = 'TDVC-0005-001');

INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       12000,
       false,
       3000,
       4
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.id = br.book_item_id
WHERE a.username = 'reader'
  AND bi.bar_code = 'TDVC-0005-001';

-- Phạt cho mượn đã trả HOB-0003-002 (trả sớm, không phạt overdue nhưng giả sử phạt mất mát nhỏ)
INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       10000,
       true,
       3000,
       0
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.id = br.book_item_id
WHERE a.username = 'reader'
  AND bi.bar_code = 'HOB-0003-002';

-- Phạt cho TKAM-0004-003 (chưa overdue, nhưng thêm phạt dự phòng hoặc 0, để đơn giản thêm một phạt chưa overdue nhưng mark)
-- Hiện tại chưa overdue, nên không phạt, nhưng thêm một borrow cũ overdue
-- Thêm borrow cũ cho Animal Farm AF-0006-001 (giả sử book_id=6)
INSERT INTO borrow_records (reader_id, book_item_id, borrow_date, due_date, return_date, is_returned)
SELECT r.account_id,
       bi.id,
       '2025-09-15',
       '2025-09-22',
       NULL,
       false
FROM readers r
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.bar_code = 'AF-0006-001'
WHERE a.username = 'reader';

UPDATE book_items
SET status = 'BORROWED'
WHERE bar_code = 'AF-0006-001';

-- Phạt overdue cho AF-0006-001 (từ 09-22 đến 10-16 = 24 ngày muộn x 3000 = 72000)
INSERT INTO penalties (record_id, reader_id, policy_id, total_fine, is_paid, fine_snapshot, total_days_late)
SELECT br.record_id,
       r.account_id,
       (SELECT id FROM penalty_policies ORDER BY effective_date DESC LIMIT 1),
       72000,
       false,
       3000,
       24
FROM borrow_records br
         JOIN readers r ON r.account_id = br.reader_id
         JOIN accounts a ON a.account_id = r.account_id
         JOIN book_items bi ON bi.id = br.book_item_id
WHERE a.username = 'reader'
  AND bi.bar_code = 'AF-0006-001';