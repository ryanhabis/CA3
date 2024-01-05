use bookrentalsystem;

SET FOREIGN_KEY_CHECKS = 0;
-- Author: Ryan and Heidi
INSERT INTO genres VALUES
                       (4, 'Fantasy'),
                       (1, 'Fiction'),
                       (3, 'Historical'),
                       (9, 'Horror'),
                       (6, 'Mystery'),
                       (2, 'Non-fiction'),
                       (7, 'Romance'),
                       (5, 'Science-Fiction'),
                       (8, 'Thriller');


-- Author: Evan
INSERT INTO users VALUES
                      (1, 'JohnDoe2012', 'JohnTheDoe', 'John', 'Doe', '12/08/2004', '086 456 9087','JohnDoe2012@gmail.com',
                       '11 johnstown estate','Castletown road', 'Dundalk', 'Louth', 'A92 R578', 'enabled', 'Customer'),
                      (2, 'JaneDoe2009', 'JaneTheDoeess', 'Jane', 'Doe', '17/06/2002', '087 476 9689','JaneDoe2009@gmail.com',
                       '08 johnstown estate','Castletown road', 'Dundalk', 'Louth', 'A92 R578', 'enabled', 'Customer'),
                      (3, 'TonyKane2005', 'TonyKaneth', 'Tony', 'Kane', '28/10/1998', '084 479 9264','TonyKane2005@gmail.com',
                       '22 campbell lane','Dublin road', 'Dublin', 'Dublin', 'A91 R678', 'enabled', 'Customer'),
                      (4, 'PatriciaKane1990', 'PatriciaKane', 'Patricia', 'Kane', '31/02/1978', '082 879 9584','PatriciaKane2007@gmail.com',
                       '27 campbell lane','Dublin road', 'Dublin', 'Dublin', 'A91 R678', 'enabled', 'Customer'),
                      (5, 'LionelBiden1968', 'LionelBiden', 'Lionel', 'Biden', '15/05/1987', '085 790 9054','LionelBiden2007@gmail.com',
                       '12 main road','Castletown lane', 'Drogheda', 'Louth', 'A93 R469', 'enabled', 'Admin'),
                      (6, 'DavidCastle1985', 'DavidCastle85', 'David', 'Castle', '23/06/1985', '083 294 7584','DavidCastle85@gmail.com',
                       '01 main road','Sheppards lane', 'Drogheda', 'Louth', 'A97 R371', 'enabled', 'Customer'),
                      (7, 'MacAlexster', 'AlliAlex', 'Alex', 'Mac Allister', '03/09/2004', '081 454 7674','AlexMacAllister@gmail.com',
                       '08 leopards lane','Cork Road', 'Cork', 'Cork', 'A96 R421', 'enabled', 'Customer'),
                      (8, 'Cathy123', '4312Cathy123', 'Catherine', 'Donally','12/11/1998', '089 285 0604','CatherineDonally@gmail.com',
                       '03 evergreen','Grange gardens', 'Johnstown', 'Limerick', 'A92 R167', 'enabled', 'Customer'),
                      (9, 'ReDane67', 'DaneReece1421', 'Reece', 'Dane', '19/10/1989', '085 359 8401','DaneReece@gmail.com',
                       '05 grangebellew','Malten gardens', 'Johnstown', 'Limerick', 'A94 R177', 'enabled', 'Customer'),
                      (10, 'ToriGrace', 'Gracie5312', 'Grace', 'Tori', '26/07/1975', '082 146 4602','ToriGrace@gmail.com',
                       '09 dromiskin estate','Dromin road', 'Dundalk', 'Louth', 'A96 R421', 'enabled', 'Admin');



-- Author: Heidi
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO BOOKS VALUES
                      (1, 4, 'Harry Potter and the Chamber of Secrets', 'Harry Potter must not go back to Hogwarts this year - theres a big snake waiting for you.', 'J.K. Rowling', 4, 8.99),
                      (2, 4, 'Harry Potter and the Order of the Phoenix', 'Neither can live while the other survives - its a race to grab the prophecy.', 'J.K. Rowling', 3, 11.99),
                      (3, 4, 'Twilight', 'Hold on Spider-Monkey.', 'Stephenie Meyer', 8, 9.99),
                      (4, 9, 'The Shining', 'Heres Johnny!', 'Stephen King', 1, 7.99),
                      (5, 7, 'Pride and Prejudice', 'A story about pride and prejudice and Colin Firth.', 'Jane Austen', 2, 6.99),
                      (6, 1, 'To Kill a Mockingbird', 'An innocent man is convicted of a crime he did not commit. Themes of strong racism and abuse.', 'Harper Lee', 4, 7.99);

-- Author: Heidi

-- reference url: https://www.sqlines.com/mysql/set_foreign_key_checks#:~:text=Temporarily%20disabling%20referential%20constraints%20(set,in%20any%20parent%2Dchild%20order.*/
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO LOANS VALUES
                      (1, 2, 'ReDane67', '2020-08-27', '2020-09-02', 0, '2020-09-01'),
                      (2, 4, 'TonyKane2005', '2021-09-16', '2021-09-22', 3.99, '2021-09-23'),
                      (3, 1, 'MacAlexster', '2022-05-05', '2022-05-12', 7.99, '2022-11-10'),
                      (4, 7, 'Cathy123', '2022-06-06', '2022-06-16', 0, '2022-06-15');
