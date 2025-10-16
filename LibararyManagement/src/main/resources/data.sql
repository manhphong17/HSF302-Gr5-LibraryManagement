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
INSERT INTO librarians (account_id, name, staff_code)
SELECT a.account_id, 'Librarian One', 'LB001'
FROM accounts a
WHERE a.username = 'admin';
INSERT INTO librarians (account_id, name, staff_code)
SELECT a.account_id, 'Librarian Two', 'LB002'
FROM accounts a
WHERE a.username = 'librarian2';

-- Readers (associate Student and Basic packages)
INSERT INTO readers (account_id, balance, debt, student_code, membership_package_id, start_date, exp_membership)
SELECT a.account_id, 0, 0, 'SV001', mp.package_id, DATE '2025-10-16', DATE '2025-10-23'
FROM accounts a
         LEFT JOIN membership_packages mp ON mp.name = 'Basic'
WHERE a.username = 'reader';

INSERT INTO readers (account_id, balance, debt, student_code, membership_package_id, start_date, exp_membership)
SELECT a.account_id, 0, 0, 'SV002', mp.package_id, DATE '2025-10-16', DATE '2025-10-23'
FROM accounts a
         LEFT JOIN membership_packages mp ON mp.name = 'Student'
WHERE a.username = 'reader2';
