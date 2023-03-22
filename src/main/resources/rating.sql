insert into rating (one_star, two_star, three_star, four_star, five_star) values (10, 15, 30, 45, 50);
insert into rating (one_star, two_star, three_star, four_star, five_star) values (5, 15, 15, 15, 5);
insert into rating (one_star, two_star, three_star, four_star, five_star) values (5, 5, 5, 25, 55);
insert into rating (one_star, two_star, three_star, four_star, five_star) values (10, 10, 25, 10, 10);
update books set id_rating = 1 where id_book = 1;
update books set id_rating = 2 where id_book = 2;
update books set id_rating = 3 where id_book = 3;
update books set id_rating = 4 where id_book = 4;