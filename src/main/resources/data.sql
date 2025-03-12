INSERT INTO roles (role_name) VALUES ('USER'), ('ADMIN');

INSERT INTO events (available_tickets, date, description, location, time, title) VALUES
(100, '2025-04-01', 'Мероприятие для любителей технологий', 'Минск', '14:00', 'TechFest 2025'),
(200, '2025-05-10', 'Выставка современного искусства', 'Гродно', '12:00', 'Art Expo 2025'),
(150, '2025-06-15', 'Книжная ярмарка', 'Брест', '10:00', 'BookFest 2025'),
(300, '2025-07-20', 'Международный музыкальный фестиваль', 'Минск', '18:00', 'Music World 2025'),
(250, '2025-08-05', 'Фестиваль кулинарии', 'Витебск', '11:00', 'Foodie Fest 2025'),
(180, '2025-09-12', 'Спортивные соревнования', 'Могилёв', '09:00', 'Sporting Challenge 2025'),
(120, '2025-10-03', 'Научная конференция', 'Минск', '16:00', 'Science Forum 2025'),
(400, '2025-11-25', 'Киноклуб и обсуждение фильмов', 'Брест', '19:30', 'Cinema Nights 2025'),
(90, '2025-12-05', 'Литературный вечер', 'Гродно', '20:00', 'Poetry Night 2025'),
(500, '2026-01-10', 'Новогодний концерт', 'Минск', '21:00', 'New Year Gala 2026');

INSERT INTO users (username, password, role_id) VALUES
('user', '$2a$10$9eVDH9sZrg9G9Ptr5o6Vr.9AbeiyPXg72MXpWBmkNYs0YfLY8noB.', 1),
('admin', '$2a$10$BiAKSa792t80sHOhzyoJvumiZRcEHtelFokORFKU9.C1Pt.HbZmYC', 2);