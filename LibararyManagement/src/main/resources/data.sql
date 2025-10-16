-- Clear existing data to avoid duplicates
-- WARNING: These DELETE statements will clear all data in the tables
-- This is intentional for seeding test data
DELETE
FROM borrow_records
WHERE 1 = 1;
DELETE
FROM book_items
WHERE 1 = 1;
DELETE
FROM book_categories
WHERE 1 = 1;
DELETE
FROM book_authors
WHERE 1 = 1;
DELETE
FROM books
WHERE 1 = 1;
DELETE
FROM categories
WHERE 1 = 1;
DELETE
FROM authors
WHERE 1 = 1;

-- Seed authors
INSERT INTO authors (name)
VALUES ('J.K. Rowling'),
       ('George Orwell'),
       ('J.R.R. Tolkien'),
       ('Harper Lee'),
       ('Suzanne Collins'),
       ('Dan Brown'),
       ('Agatha Christie'),
       ('Stephen King'),
       ('Mark Twain'),
       ('Jane Austen'),
       ('Charles Dickens'),
       ('Leo Tolstoy'),
       ('F. Scott Fitzgerald'),
       ('Ernest Hemingway'),
       ('Virginia Woolf'),
       ('Gabriel García Márquez'),
       ('Maya Angelou'),
       ('Toni Morrison'),
       ('Chimamanda Ngozi Adichie'),
       ('Haruki Murakami');

-- Seed categories
INSERT INTO categories (name)
VALUES ('Fantasy'),
       ('Dystopian'),
       ('Classic'),
       ('Science Fiction'),
       ('Adventure'),
       ('Thriller'),
       ('Mystery'),
       ('Horror'),
       ('Romance'),
       ('Historical Fiction'),
       ('Biography'),
       ('Self-Help'),
       ('Philosophy'),
       ('Poetry'),
       ('Drama'),
       ('Comedy'),
       ('Non-Fiction'),
       ('Young Adult'),
       ('Children'),
       ('Business');

-- Seed books
INSERT INTO books (title, price, stock_quantity, is_deleted)
VALUES ('Harry Potter and the Sorcerer''s Stone', 150000, 10, false),
       ('Harry Potter and the Chamber of Secrets', 160000, 8, false),
       ('Harry Potter and the Prisoner of Azkaban', 170000, 9, false),
       ('1984', 90000, 5, false),
       ('Animal Farm', 80000, 6, false),
       ('The Hobbit', 120000, 8, false),
       ('The Lord of the Rings', 200000, 6, false),
       ('To Kill a Mockingbird', 95000, 7, false),
       ('The Hunger Games', 110000, 12, false),
       ('Catching Fire', 115000, 10, false),
       ('Mockingjay', 120000, 8, false),
       ('The Da Vinci Code', 130000, 9, false),
       ('Angels & Demons', 125000, 7, false),
       ('Murder on the Orient Express', 100000, 6, false),
       ('The Shining', 140000, 5, false),
       ('It', 180000, 4, false),
       ('The Adventures of Tom Sawyer', 85000, 8, false),
       ('Pride and Prejudice', 90000, 7, false),
       ('Great Expectations', 95000, 6, false),
       ('War and Peace', 220000, 3, false),
       ('The Great Gatsby', 85000, 9, false),
       ('The Old Man and the Sea', 75000, 8, false),
       ('To the Lighthouse', 80000, 5, false),
       ('One Hundred Years of Solitude', 110000, 6, false),
       ('I Know Why the Caged Bird Sings', 90000, 7, false),
       ('Beloved', 95000, 5, false),
       ('Half of a Yellow Sun', 100000, 6, false),
       ('Norwegian Wood', 85000, 8, false),
       ('The Art of War', 70000, 10, false),
       ('The 7 Habits of Highly Effective People', 120000, 15, false);


-- Link books with authors
INSERT INTO book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM books b
         JOIN authors a ON 1 = 1
WHERE (b.title LIKE 'Harry Potter%' AND a.name = 'J.K. Rowling')
   OR (b.title IN ('1984', 'Animal Farm') AND a.name = 'George Orwell')
   OR (b.title IN ('The Hobbit', 'The Lord of the Rings') AND a.name = 'J.R.R. Tolkien')
   OR (b.title = 'To Kill a Mockingbird' AND a.name = 'Harper Lee')
   OR (b.title LIKE 'The Hunger Games%' AND a.name = 'Suzanne Collins')
   OR (b.title IN ('The Da Vinci Code', 'Angels & Demons') AND a.name = 'Dan Brown')
   OR (b.title = 'Murder on the Orient Express' AND a.name = 'Agatha Christie')
   OR (b.title IN ('The Shining', 'It') AND a.name = 'Stephen King')
   OR (b.title = 'The Adventures of Tom Sawyer' AND a.name = 'Mark Twain')
   OR (b.title = 'Pride and Prejudice' AND a.name = 'Jane Austen')
   OR (b.title = 'Great Expectations' AND a.name = 'Charles Dickens')
   OR (b.title = 'War and Peace' AND a.name = 'Leo Tolstoy')
   OR (b.title = 'The Great Gatsby' AND a.name = 'F. Scott Fitzgerald')
   OR (b.title = 'The Old Man and the Sea' AND a.name = 'Ernest Hemingway')
   OR (b.title = 'To the Lighthouse' AND a.name = 'Virginia Woolf')
   OR (b.title = 'One Hundred Years of Solitude' AND a.name = 'Gabriel García Márquez')
   OR (b.title = 'I Know Why the Caged Bird Sings' AND a.name = 'Maya Angelou')
   OR (b.title = 'Beloved' AND a.name = 'Toni Morrison')
   OR (b.title = 'Half of a Yellow Sun' AND a.name = 'Chimamanda Ngozi Adichie')
   OR (b.title = 'Norwegian Wood' AND a.name = 'Haruki Murakami')
   OR (b.title = 'The Art of War' AND a.name = 'Sun Tzu')
   OR (b.title = 'The 7 Habits of Highly Effective People' AND a.name = 'Stephen Covey');

-- Link books with categories
INSERT INTO book_categories (book_id, category_id)
SELECT b.book_id, c.category_id
FROM books b
         JOIN categories c ON 1 = 1
WHERE (b.title LIKE 'Harry Potter%' AND c.name IN ('Fantasy', 'Young Adult'))
   OR (b.title IN ('1984', 'Animal Farm') AND c.name IN ('Dystopian', 'Classic'))
   OR (b.title IN ('The Hobbit', 'The Lord of the Rings') AND c.name IN ('Fantasy', 'Adventure', 'Classic'))
   OR (b.title = 'To Kill a Mockingbird' AND c.name = 'Classic')
   OR (b.title LIKE 'The Hunger Games%' AND c.name IN ('Dystopian', 'Science Fiction', 'Young Adult'))
   OR (b.title IN ('The Da Vinci Code', 'Angels & Demons') AND c.name = 'Thriller')
   OR (b.title = 'Murder on the Orient Express' AND c.name = 'Mystery')
   OR (b.title IN ('The Shining', 'It') AND c.name = 'Horror')
   OR (b.title = 'The Adventures of Tom Sawyer' AND c.name IN ('Adventure', 'Classic'))
   OR (b.title = 'Pride and Prejudice' AND c.name IN ('Romance', 'Classic'))
   OR (b.title = 'Great Expectations' AND c.name = 'Classic')
   OR (b.title = 'War and Peace' AND c.name IN ('Historical Fiction', 'Classic'))
   OR (b.title = 'The Great Gatsby' AND c.name = 'Classic')
   OR (b.title = 'The Old Man and the Sea' AND c.name = 'Classic')
   OR (b.title = 'To the Lighthouse' AND c.name IN ('Classic', 'Drama'))
   OR (b.title = 'One Hundred Years of Solitude' AND c.name IN ('Magical Realism', 'Classic'))
   OR (b.title = 'I Know Why the Caged Bird Sings' AND c.name IN ('Biography', 'Non-Fiction'))
   OR (b.title = 'Beloved' AND c.name IN ('Historical Fiction', 'Classic'))
   OR (b.title = 'Half of a Yellow Sun' AND c.name IN ('Historical Fiction', 'Drama'))
   OR (b.title = 'Norwegian Wood' AND c.name IN ('Fiction', 'Drama'))
   OR (b.title = 'The Art of War' AND c.name IN ('Philosophy', 'Business'))
   OR (b.title = 'The 7 Habits of Highly Effective People' AND c.name IN ('Self-Help', 'Business'));

-- Generate book items for all books
INSERT INTO book_items (bar_code, book_id, status)
SELECT CONCAT(
               CASE
                   WHEN b.title LIKE 'Harry Potter%' THEN 'HP'
                   WHEN b.title IN ('1984', 'Animal Farm') THEN 'ORW'
                   WHEN b.title IN ('The Hobbit', 'The Lord of the Rings') THEN 'TOL'
                   WHEN b.title = 'To Kill a Mockingbird' THEN 'TKM'
                   WHEN b.title LIKE 'The Hunger Games%' THEN 'THG'
                   WHEN b.title IN ('The Da Vinci Code', 'Angels & Demons') THEN 'DAN'
                   WHEN b.title = 'Murder on the Orient Express' THEN 'AGH'
                   WHEN b.title IN ('The Shining', 'It') THEN 'SK'
                   WHEN b.title = 'The Adventures of Tom Sawyer' THEN 'MT'
                   WHEN b.title = 'Pride and Prejudice' THEN 'JA'
                   WHEN b.title = 'Great Expectations' THEN 'CD'
                   WHEN b.title = 'War and Peace' THEN 'LT'
                   WHEN b.title = 'The Great Gatsby' THEN 'FSF'
                   WHEN b.title = 'The Old Man and the Sea' THEN 'EH'
                   WHEN b.title = 'To the Lighthouse' THEN 'VW'
                   WHEN b.title = 'One Hundred Years of Solitude' THEN 'GGM'
                   WHEN b.title = 'I Know Why the Caged Bird Sings' THEN 'MA'
                   WHEN b.title = 'Beloved' THEN 'TM'
                   WHEN b.title = 'Half of a Yellow Sun' THEN 'CA'
                   WHEN b.title = 'Norwegian Wood' THEN 'HM'
                   WHEN b.title = 'The Art of War' THEN 'ST'
                   WHEN b.title = 'The 7 Habits of Highly Effective People' THEN 'SC'
                   ELSE 'BOOK'
                   END,
               '-',
               LPAD(b.book_id, 4, '0'),
               '-',
               LPAD(ROW_NUMBER() OVER (PARTITION BY b.book_id ORDER BY b.book_id), 3, '0')
       ),
       b.book_id,
       CASE
           WHEN ROW_NUMBER() OVER (PARTITION BY b.book_id ORDER BY b.book_id) <= b.stock_quantity - 2 THEN 'AVAILABLE'
           WHEN ROW_NUMBER() OVER (PARTITION BY b.book_id ORDER BY b.book_id) = b.stock_quantity - 1 THEN 'BORROWED'
           ELSE 'NONAVAILABLE'
           END
FROM books b
         CROSS JOIN (SELECT 1 as n
                     UNION
                     SELECT 2
                     UNION
                     SELECT 3
                     UNION
                     SELECT 4
                     UNION
                     SELECT 5
                     UNION
                     SELECT 6
                     UNION
                     SELECT 7
                     UNION
                     SELECT 8
                     UNION
                     SELECT 9
                     UNION
                     SELECT 10
                     UNION
                     SELECT 11
                     UNION
                     SELECT 12
                     UNION
                     SELECT 13
                     UNION
                     SELECT 14
                     UNION
                     SELECT 15) numbers
WHERE numbers.n <= b.stock_quantity;

-- Clear existing user data
-- WARNING: These DELETE statements will clear all data in the tables
-- This is intentional for seeding test data
DELETE
FROM borrow_records
WHERE 1 = 1;
DELETE
FROM readers
WHERE 1 = 1;
DELETE
FROM librarians
WHERE 1 = 1;
DELETE
FROM user_profiles
WHERE 1 = 1;
DELETE
FROM accounts
WHERE 1 = 1;
DELETE
FROM membership_packages
WHERE 1 = 1;
DELETE
FROM penalty_policies
WHERE 1 = 1;

-- Membership packages
INSERT INTO membership_packages (name, price, max_books_per_month)
VALUES ('Basic', 0, 3),
       ('Premium', 99000, 10),
       ('Student', 49000, 6),
       ('VIP', 199000, 20);

-- Penalty policies
INSERT INTO penalty_policies (effective_date, fine_per_day, lost_book_fine_rate, type)
VALUES ('2025-01-01', 5000, NULL, 'OVERDUE'),
       ('2025-01-01', NULL, 1.5, 'LOST');

-- Seed accounts - 2 librarians and 2 readers only
INSERT INTO accounts (username, password, role, active)
VALUES ('admin', '000000', 'LIBRIAN', true),
       ('librarian2', '000000', 'LIBRIAN', true),
       ('reader1', '000000', 'READER', true),
       ('reader2', '000000', 'READER', true);

-- Seed user profiles
INSERT INTO user_profiles (account_id, name, phone, address, avatar)
VALUES (1, 'Nguyễn Văn Admin', '0900000001', '123 Nguyễn Huệ, Q1, TP.HCM', NULL),
       (2, 'Trần Thị Thủ Thư', '0900000002', '456 Lê Lợi, Q1, TP.HCM', NULL),
       (3, 'Lê Văn Đọc', '0910000001', '789 Nguyễn Thị Minh Khai, Q3, TP.HCM', NULL),
       (4, 'Phạm Thị Sinh Viên', '0910000002', '321 Điện Biên Phủ, Q.Bình Thạnh, TP.HCM', NULL);

-- Seed librarians
INSERT INTO librarians (account_id, staff_code)
VALUES (1, 'LB001'),
       (2, 'LB002');

-- Seed readers with different membership packages
INSERT INTO readers (account_id, balance, debt, student_code, membership_package_id, exp_membership)
VALUES (3, 150000, 0, 'SV001', (SELECT package_id FROM membership_packages WHERE name = 'Basic'), '2025-12-31'),
       (4, 250000, 0, 'SV002', (SELECT package_id FROM membership_packages WHERE name = 'Premium'), '2026-01-15');

-- Generate comprehensive borrow records for testing
INSERT INTO borrow_records (borrow_date, due_date, is_returned, return_date, book_item_id, reader_id)
SELECT DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 30) DAY)                           as borrow_date,
       DATE_ADD(DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 7 DAY) as due_date,
       CASE
           WHEN RAND() > 0.3 THEN true
           ELSE false
           END                                                                        as is_returned,
       CASE
           WHEN RAND() > 0.3 THEN DATE_ADD(DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL
                                           FLOOR(RAND() * 7) DAY)
           ELSE NULL
           END                                                                        as return_date,
       bi.id,
       CASE
           WHEN RAND() > 0.5 THEN 3
           ELSE 4
           END                                                                        as reader_id
FROM book_items bi
WHERE bi.status = 'BORROWED'
   OR (bi.status = 'AVAILABLE' AND RAND() > 0.7)
LIMIT 50;

-- Add some overdue records for testing
INSERT INTO borrow_records (borrow_date, due_date, is_returned, return_date, book_item_id, reader_id)
SELECT DATE_SUB(CURDATE(), INTERVAL 15 DAY) as borrow_date,
       DATE_SUB(CURDATE(), INTERVAL 5 DAY)  as due_date,
       false                                as is_returned,
       NULL                                 as return_date,
       bi.id,
       CASE
           WHEN RAND() > 0.5 THEN 3
           ELSE 4
           END                              as reader_id
FROM book_items bi
WHERE bi.status = 'AVAILABLE'
LIMIT 10;
