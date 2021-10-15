INSERT INTO chat.users (login, password) VALUES ('Anna1', '123');
INSERT INTO chat.users (login, password) VALUES ('Anna2', '123');
INSERT INTO chat.users (login, password) VALUES ('Anna3', '123');
INSERT INTO chat.users (login, password) VALUES ('Anna4', '123');
INSERT INTO chat.users (login, password) VALUES ('Anna5', '123');

INSERT INTO chat.rooms (name, owner) VALUES ('room1', 1);
INSERT INTO chat.rooms (name, owner) VALUES ('room2', 2);
INSERT INTO chat.rooms (name, owner) VALUES ('room3', 3);
INSERT INTO chat.rooms (name, owner) VALUES ('room4', 4);
INSERT INTO chat.rooms (name, owner) VALUES ('room5', 5);

INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (1, 1, 'hi 1', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (1, 2, 'bye 1', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (2, 3, 'why? 2', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (5, 1, 'hi 5', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (4, 2, 'bye 4', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (3, 3, 'why? 3', current_timestamp);