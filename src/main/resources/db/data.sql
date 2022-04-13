    insert into users (id, first_name, last_name, email, phone, username, password) values (1, 'John', 'Mayer', 'test1@mail.com', 'somephone', 'jmayer', 'test'),
                                                                                  (2, 'Orken', 'Nik', 'test2@mail.com', 'somephone', 'onik', 'test'),
                                                                                  (3, 'Test', 'test', 'test3@mail.com', 'somephone', 'testtest1', 'test'),
                                                                                  (4, 'asdf', 'gqrg', 'test4@mail.com', 'somephone', 'adfadf', 'test'),
                                                                                  (5, 'John2', 'Mayer2', 'test1@mail.com', 'somephone', 'jmay2er', 'test');

insert into account (id, number, amount, is_blocked, user_id) values (1, 'ASDFG@', 150000, true, 1),
                                                     (2, 'HIUFHIW@', 450000, false, 2),
                                                     (3, 'GUEIOEG', 1245555, false, 3),
                                                     (4, 'HESOYAM@', 250000, false, 4);

