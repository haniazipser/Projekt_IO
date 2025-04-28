INSERT INTO course (name, instructor, creator)
VALUES
('Computer Science', 'polanski@gmail.com', 'janesmith@gmail.com'),
('Discrete Mathematics', 'zygadlo@gmail.com', 'johndoe@gmail.com');

INSERT INTO course_lesson_times (course_id, day_of_week, time)
VALUES
(1, 'MONDAY', '10:00:00'),
(2, 'WEDNESDAY', '12:00:00');

INSERT INTO lesson (class_date, course_id)
VALUES
('2025-05-01 08:00:00', 1),
('2025-05-03 10:00:00', 2);

INSERT INTO exercise (lesson_id, exercise_number, subpoint, approved_student)
VALUES
(1, 1, 'a', 'johndoe@gmail.com'),
(1, 2, NULL, 'janesmith@gmail.com'),
(2, 1, NULL, 'AliceBrown@gmail.com');

INSERT INTO exercise_declaration (declaration_date, declaration_status, exercise_id, student)
VALUES
('2025-04-25 10:00:00', 'WAITING', 1, 'johndoe@gmail.com'),
('2025-04-25 10:05:00', 'APPROVED', 1, 'janesmith@gmail.com'),
('2025-04-25 10:10:00', 'REJECTED', 2, 'AliceBrown@gmail.com');

INSERT INTO point (student, source_id, activity_value) VALUES
('johndoe@gmail.com', 1, 1.0),
('janesmith@gmail.com', 3, 0.5),
('AliceBrown@gmail.com', 2, 1.0);

INSERT INTO participant(email, course_id, invitation_status) values
('johndoe@gmail.com', 1, 'ACCEPTED'),
('janesmith@gmail.com', 1, 'ACCEPTED'),
('johndoe@gmail.com', 2, 'ACCEPTED');