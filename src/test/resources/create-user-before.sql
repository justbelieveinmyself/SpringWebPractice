
INSERT IGNORE INTO users (id, username, password, active) VALUES
    (1, "admin", "$2a$08$TNkUoOLmEMuA5Jy8Dr07b.DvFfcGM12nOyJbrSlGKtG/ZsEFDXFnS", true),
    (2, "user", "$2a$08$TNkUoOLmEMuA5Jy8Dr07b.DvFfcGM12nOyJbrSlGKtG/ZsEFDXFnS", true);
INSERT IGNORE INTO user_role (user_id, roles) VALUES
    (1, 'ADMIN'),
    (1, 'USER'),
    (2, 'USER');