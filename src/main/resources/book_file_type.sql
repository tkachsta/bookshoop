INSERT INTO book_file_type (id_book_file_type, name) VALUES (1, 'pdf');
INSERT INTO book_file_type (id_book_file_type, name) VALUES (2, 'epub');
INSERT INTO book_file_type (id_book_file_type, name) VALUES (3, 'fb2');

INSERT INTO book_file(id_bookfile, path) VALUES (1, 'Family_Law');
INSERT INTO book_file(id_bookfile, path) VALUES (2, 'Timber_Falls');

INSERT INTO bookfile2type(id_bookfile, id_book_file_type, id_book, description, hash) VALUES (1, 1, 735, '3 MB', 'hjkhkjh23hj');
INSERT INTO bookfile2type(id_bookfile, id_book_file_type, id_book, description, hash) VALUES (1, 2, 735, '5 MB', 'hjkhkjh23hj');
INSERT INTO bookfile2type(id_bookfile, id_book_file_type, id_book, description, hash) VALUES (1, 3, 735, '6 MB', 'hjkhkjh23hj');

INSERT INTO bookfile2type(id_bookfile, id_book_file_type, id_book, description, hash) VALUES (2, 1, 206, '7 MB', 'hjkhkjh23hj');
INSERT INTO bookfile2type(id_bookfile, id_book_file_type, id_book, description, hash) VALUES (2, 2, 206, '8 MB', 'hjkhkjh23hj');
INSERT INTO bookfile2type(id_bookfile, id_book_file_type, id_book, description, hash) VALUES (2, 3, 206, '9 MB', 'hjkhkjh23hj');
