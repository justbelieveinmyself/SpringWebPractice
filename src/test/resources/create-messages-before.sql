
INSERT IGNORE INTO messages (id, text, tag, user_id) VALUES
    (1, "for test :)", "my tag for test", 1),
    (2, "mda", "vibe", 1),
    (3, "yeah", "my tag for test", 1),
    (4, "hello", "greetings", 1),
    (5, "meow", "cat", 2);

ALTER TABLE messages AUTO_INCREMENT = 10;