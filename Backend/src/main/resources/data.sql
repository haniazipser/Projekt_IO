INSERT INTO course (id, name, instructor, creator)
VALUES
('3ed7747e-490f-45f6-a1fb-86c9764e75f2','Computer Science', 'polanski@gmail.com', 'janesmith@gmail.com'),
('e5965121-1fae-428c-a13f-4e6c741db180','Discrete Mathematics', 'zygadlo@gmail.com', 'johndoe@gmail.com');

INSERT INTO course_lesson_times (course_id, day_of_week)
VALUES
('3ed7747e-490f-45f6-a1fb-86c9764e75f2', 'MONDAY'),
('e5965121-1fae-428c-a13f-4e6c741db180', 'WEDNESDAY');

INSERT INTO lesson (id,class_date, course_id)
VALUES
('b0e58980-811a-4832-89c8-ccf4a60bf4be','2025-05-01 08:00:00', '3ed7747e-490f-45f6-a1fb-86c9764e75f2'),
('5bf58f41-75d7-42e6-86a5-e7b4d01d7e62','2025-05-03 10:00:00','e5965121-1fae-428c-a13f-4e6c741db180');

INSERT INTO exercise (id,lesson_id, exercise_number, subpoint, approved_student)
VALUES
('f3b08d44-76d6-41bf-936e-392c90a63307','b0e58980-811a-4832-89c8-ccf4a60bf4be', 1, 'a', 'johndoe@gmail.com'),
('5cbf7d41-744a-46c1-84e3-988e37693436','b0e58980-811a-4832-89c8-ccf4a60bf4be', 2, NULL, NULL),
('bacba752-8fef-4b0d-8c9e-2325e4933e0b','5bf58f41-75d7-42e6-86a5-e7b4d01d7e62', 1, NULL, 'AliceBrown@gmail.com');

INSERT INTO exercise_declaration (id, declaration_date, declaration_status, exercise_id, student)
VALUES
('90cc01ff-cf3f-43e4-839e-5478b51485c9','2025-04-25 10:00:00', 'WAITING', 'f3b08d44-76d6-41bf-936e-392c90a63307', 'johndoe@gmail.com'),
('1a602a7c-3fb9-4777-8521-6405323c962f','2025-04-25 10:05:00', 'APPROVED', '5cbf7d41-744a-46c1-84e3-988e37693436', 'janesmith@gmail.com'),
('4c6e2fc9-0959-4a79-8992-f14cecd45a60','2025-04-25 10:10:00', 'REJECTED', '5cbf7d41-744a-46c1-84e3-988e37693436', 'AliceBrown@gmail.com');

INSERT INTO point (id,student, lesson_id, activity_value) VALUES
('4f5288c2-4a52-4b36-8f5a-8e32ab095922','johndoe@gmail.com', 'b0e58980-811a-4832-89c8-ccf4a60bf4be', 1.0),
('230eff0a-c2a8-44c9-b33a-1520cfaa6121','janesmith@gmail.com', '5bf58f41-75d7-42e6-86a5-e7b4d01d7e62', 0.5),
('3d81c604-d41c-432f-a88c-c073a71bf165','AliceBrown@gmail.com', '5bf58f41-75d7-42e6-86a5-e7b4d01d7e62', 1.0);

INSERT INTO participant(id, email, course_id, invitation_status) values
('f2da439f-ddbc-4151-ba82-04cb46f9dd2e','johndoe@gmail.com', '3ed7747e-490f-45f6-a1fb-86c9764e75f2', 'ACCEPTED'),
('cb8585d5-0a9c-4fe8-a81e-4d14f0d3b795','janesmith@gmail.com', '3ed7747e-490f-45f6-a1fb-86c9764e75f2', 'ACCEPTED'),
('d9534897-596e-4458-8417-4525f92ff8b2','johndoe@gmail.com', 'e5965121-1fae-428c-a13f-4e6c741db180', 'ACCEPTED');

