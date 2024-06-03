-- Insert sample data for user table
INSERT INTO user (id, active, first_name, last_name, password, username)
VALUES ('a1f6b8e3-4a7d-4c5e-9f8a-1b2c3d4e5f6a', true, 'trainee', 'trainee',
        '$2a$10$ube7iNw699GvvYLY.f2UUO1t5oqJNJKbGsdUaizCoKEHmeiVk0wQC', 'trainee.trainee'), -- password = YAgU86xxdBEhKA
       ('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7b', true, 'trainer', 'trainer', 'hashed_password2', 'trainer.trainer'),
       ('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7c', true, 'trainer', 'train', 'hashed_password3', 'trainer.train'),
       ('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7d', true, 'trainer', 'train', 'hashed_password4', 'trainer.train1');

-- Insert sample data for training_type table
INSERT INTO training_type (id, name)
VALUES ('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c', 'Strength Training'),
       ('d4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d', 'Cardio'),
       ('e5f7d9a1-3c4d-4e5f-8a9f-1b2c3d4e5f7e', 'Yoga');

-- Insert sample data for trainee table
INSERT INTO trainee (id, birth_date, user_id, address)
VALUES ('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f', '1990-05-15', 'a1f6b8e3-4a7d-4c5e-9f8a-1b2c3d4e5f6a', '123 Main St');

-- Insert sample data for trainer table
INSERT INTO trainer (id, specialization_id, user_id)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'd4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d',
        'b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7b'),
       ('550e8400-e29b-41d4-a716-446655440010', 'd4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d',
        'b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7c'),
       ('550e8400-e29b-41d4-a716-446655440011', 'd4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d',
        'b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7d');

-- Insert sample data for trainer_trainee table (associations)
INSERT INTO trainer_trainee (trainee_id, trainer_id)
VALUES ('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f', '550e8400-e29b-41d4-a716-446655440002');

-- Insert sample data for training table
INSERT INTO training (id, date, duration, trainee_id, trainer_id, type_id, name)
VALUES ('550e8400-e29b-41d4-a716-446655440003', '2024-04-10 10:00:00', 60, 'f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f',
        '550e8400-e29b-41d4-a716-446655440002', 'c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c', 'Strength Training Session'),
       ('550e8400-e29b-41d4-a716-446655440004', '2024-04-12 15:30:00', 45, 'f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f',
        '550e8400-e29b-41d4-a716-446655440002', 'd4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d', 'Cardio Workout'),
       ('550e8400-e29b-41d4-a716-446655440005', '2024-04-12 15:30:00', 45, 'f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f',
        '550e8400-e29b-41d4-a716-446655440010', 'd4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d', 'Cardio Workout');