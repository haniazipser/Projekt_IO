-- Kurs
INSERT INTO course (id, name, instructor, creator, start_date, end_date, frequency, course_code)
VALUES
('a1111111-2222-4333-8444-555555555555','Advanced Algorithms', 'dr.brecht@uni.edu', 'admin@uni.edu', '2025-03-01 00:00:00', '2025-07-31 00:00:00', 1, '110fb02f-4514-40d3-8398-9d1d517b0ebc');

-- Dni tygodnia
INSERT INTO course_lesson_times (course_id, day_of_week)
VALUES
('a1111111-2222-4333-8444-555555555555', 'THURSDAY');

-- Uczestnik
INSERT INTO participant(id, email, course_id, invitation_status)
VALUES
('d4f5e6e7-1234-4567-89ab-101112131415','brecht@rybik.eu', 'a1111111-2222-4333-8444-555555555555', 'ACCEPTED');

-- LEKCJE
INSERT INTO lesson (id, class_date, course_id)
VALUES
('02f5fc3a-f339-432f-a97d-f58be7cfb500','2025-04-10 14:00:00','a1111111-2222-4333-8444-555555555555'),
('b32fa49b-b885-4911-aac2-2910b04817b4','2025-04-17 14:00:00','a1111111-2222-4333-8444-555555555555'),
('31ac5c2d-761e-4d5c-96ed-37d66dabc9cf','2025-05-05 14:00:00','a1111111-2222-4333-8444-555555555555'),
('f6717a87-9dd8-4d59-98ab-2f49e40b3652','2025-05-09 14:00:00','a1111111-2222-4333-8444-555555555555'),
('a89cb383-f4a3-4cb0-949a-b4f7c04c54fc','2025-05-15 14:00:00','a1111111-2222-4333-8444-555555555555'),
('158a7278-c5c6-47d6-b26d-53dc1b13a17f','2025-06-01 14:00:00','a1111111-2222-4333-8444-555555555555');

-- ĆWICZENIA (2 na lekcję)
INSERT INTO exercise (id, lesson_id, exercise_number, subpoint, approved_student)
VALUES
-- PAST
('b0457de3-f1d4-4c5e-84c5-4f5aa3a259a7','02f5fc3a-f339-432f-a97d-f58be7cfb500', 1, 'a', 'brecht@rybik.eu'),
('f938c49d-b1d2-4e1b-a5a2-9f0e071d582f','02f5fc3a-f339-432f-a97d-f58be7cfb500', 2, NULL, NULL),
('6a2f8499-3fc3-45d1-bdfb-dc55d402b865','b32fa49b-b885-4911-aac2-2910b04817b4', 1, 'a', NULL),
('35d8ec74-5c29-4d09-b6f1-46df38d38d87','b32fa49b-b885-4911-aac2-2910b04817b4', 2, NULL, 'brecht@rybik.eu'),
-- NEAR
('ed998758-7207-4a33-b9be-ec33ec137a5e','31ac5c2d-761e-4d5c-96ed-37d66dabc9cf', 1, NULL, NULL),
('126c2bfc-5c57-4b71-b5a1-73c2f0f3fc07','31ac5c2d-761e-4d5c-96ed-37d66dabc9cf', 2, NULL, NULL),
('40d3482f-9f6e-46b7-b6cf-e2eae4a949d1','f6717a87-9dd8-4d59-98ab-2f49e40b3652', 1, NULL, NULL),
('1c5fba12-1656-4680-90f2-e4a9f719e89f','f6717a87-9dd8-4d59-98ab-2f49e40b3652', 2, NULL, NULL),
-- FUTURE
('ac63f2aa-10a6-4d0b-b5f6-3b0a29b57c9d','a89cb383-f4a3-4cb0-949a-b4f7c04c54fc', 1, NULL, NULL),
('3aa6dc86-b29b-420d-bca1-1f13f9e28485','a89cb383-f4a3-4cb0-949a-b4f7c04c54fc', 2, NULL, NULL),
('ed5ce66e-4b2f-4dd0-bfd8-74034378e95c','158a7278-c5c6-47d6-b26d-53dc1b13a17f', 1, NULL, NULL),
('bfae58aa-850c-4f7c-88a0-5701dfb13f8e','158a7278-c5c6-47d6-b26d-53dc1b13a17f', 2, NULL, NULL);

-- DEKLARACJE
INSERT INTO exercise_declaration (id, declaration_date, declaration_status, exercise_id, student)
VALUES
-- PAST
('be00c462-cf56-41a9-850d-8c8ff4d3e307','2025-04-08 10:00:00','APPROVED','b0457de3-f1d4-4c5e-84c5-4f5aa3a259a7','brecht@rybik.eu'),
('0e58e63c-196f-4b85-81bc-4a7d2892d5e7','2025-04-08 10:30:00','REJECTED','f938c49d-b1d2-4e1b-a5a2-9f0e071d582f','brecht@rybik.eu'),
('e7e418d2-e5e3-41ee-bd92-7a02a6e338a6','2025-04-15 11:00:00','CANCELLED','6a2f8499-3fc3-45d1-bdfb-dc55d402b865','brecht@rybik.eu'),
('ce38d81b-2234-4dd5-b15a-6480c1c17b6f','2025-04-15 12:00:00','APPROVED','35d8ec74-5c29-4d09-b6f1-46df38d38d87','brecht@rybik.eu'),
-- NEAR
('daee8887-2041-41a7-8e70-747b4280d8ff','2025-05-02 09:00:00','WAITING','ed998758-7207-4a33-b9be-ec33ec137a5e','brecht@rybik.eu'),
('df6e9b39-31d3-42c4-90c8-25581bb6c278','2025-05-02 09:30:00','REJECTED','126c2bfc-5c57-4b71-b5a1-73c2f0f3fc07','brecht@rybik.eu'),
('b9a7685f-4490-4d14-bc32-9bbfdac240f8','2025-05-02 10:00:00','WAITING','40d3482f-9f6e-46b7-b6cf-e2eae4a949d1','brecht@rybik.eu'),
('a401f6db-eef6-4b5a-acc5-1b52c34467e8','2025-05-02 10:30:00','CANCELLED','1c5fba12-1656-4680-90f2-e4a9f719e89f','brecht@rybik.eu'),
-- FUTURE
('a0f04bfb-41ce-43ec-82a5-8ae6e1bdeae2','2025-05-03 11:00:00','WAITING','ac63f2aa-10a6-4d0b-b5f6-3b0a29b57c9d','brecht@rybik.eu'),
('2ec86eb3-b257-46f3-9a0f-6ad6b9c918b5','2025-05-03 11:30:00','WAITING','3aa6dc86-b29b-420d-bca1-1f13f9e28485','brecht@rybik.eu'),
('e3080533-38d2-4a27-b69f-7f94ea60aa71','2025-05-03 12:00:00','WAITING','ed5ce66e-4b2f-4dd0-bfd8-74034378e95c','brecht@rybik.eu'),
('5f419d2a-6a6c-4b79-b232-f10d7de109a0','2025-05-03 12:30:00','WAITING','bfae58aa-850c-4f7c-88a0-5701dfb13f8e','brecht@rybik.eu');

-- PUNKTY
INSERT INTO point (id, student, lesson_id, activity_value)
VALUES
('6e3a6e8f-00f0-41aa-babe-c93f841d8854','brecht@rybik.eu','02f5fc3a-f339-432f-a97d-f58be7cfb500', 1.0),
('acb5de30-879e-4a5b-bb38-bdbf5c5a90a4','brecht@rybik.eu','b32fa49b-b885-4911-aac2-2910b04817b4', 0.5),
('a595cfaa-5535-4d56-9824-1a60c3f26b2a','brecht@rybik.eu','31ac5c2d-761e-4d5c-96ed-37d66dabc9cf', 0.0),
('df2f208f-36e0-4f9e-9856-d27d0b4d2f62','brecht@rybik.eu','f6717a87-9dd8-4d59-98ab-2f49e40b3652', 0.0);
