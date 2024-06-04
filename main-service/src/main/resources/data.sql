-- Insert sample data for user table
INSERT INTO user (id, active, first_name, last_name, password, username)
VALUES (UUID_TO_BIN('a1f6b8e3-4a7d-4c5e-9f8a-1b2c3d4e5f6a'), true, 'trainee', 'trainee',
        '$2a$10$ube7iNw699GvvYLY.f2UUO1t5oqJNJKbGsdUaizCoKEHmeiVk0wQC', 'trainee.trainee'), -- password = YAgU86xxdBEhKA
       (UUID_TO_BIN('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7b'), true, 'trainer', 'trainer', 'hashed_password2', 'trainer.trainer'),
       (UUID_TO_BIN('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7c'), true, 'trainer', 'train', 'hashed_password3', 'trainer.train'),
       (UUID_TO_BIN('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7d'), true, 'trainer', 'train', 'hashed_password4', 'trainer.train1');

-- Insert sample data for training_type table
INSERT INTO training_type (id, name)
VALUES (UUID_TO_BIN('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c'), 'Strength Training'),
       (UUID_TO_BIN('d4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d'), 'Cardio'),
       (UUID_TO_BIN('e5f7d9a1-3c4d-4e5f-8a9f-1b2c3d4e5f7e'), 'Yoga');

-- Insert sample data for trainee table
INSERT INTO trainee (id, birth_date, user_id, address)
VALUES (UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'), '1990-05-15', UUID_TO_BIN('a1f6b8e3-4a7d-4c5e-9f8a-1b2c3d4e5f6a'), '123 Main St');

-- Insert sample data for trainer table
INSERT INTO trainer (id, specialization_id, user_id)
VALUES (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('d4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d'),
        UUID_TO_BIN('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7b')),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440010'), UUID_TO_BIN('d4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d'),
        UUID_TO_BIN('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7c')),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440011'), UUID_TO_BIN('d4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d'),
        UUID_TO_BIN('b2e4f6d8-1c3d-4e5f-8a9f-2b3c4d5e6f7d'));

-- Insert sample data for trainer_trainee table (associations)
INSERT INTO trainer_trainee (trainee_id, trainer_id)
VALUES (UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'), UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'));

-- Insert sample data for training table
INSERT INTO training (id, date, duration, trainee_id, trainer_id, type_id, name)
VALUES (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440006'), '2030-01-15 10:00:00', 3600, UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'),
        UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c'), 'Strength Training Session'),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440007'), '2030-02-15 10:00:00', 3600, UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'),
        UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c'), 'Strength Training Session'),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440008'), '2030-03-15 10:00:00', 3600, UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'),
        UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c'), 'Strength Training Session'),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440009'), '2030-04-15 10:00:00', 3600, UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'),
        UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c'), 'Strength Training Session'),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440010'), '2031-01-20 15:30:00', 3600, UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'),
        UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('d4f6e8c2-1b3c-4d5e-9f8a-2c3d4e5f6d8d'), 'Cardio Workout'),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440011'), '2032-01-25 15:30:00', 3600, UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'),
        UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c'), 'Strength Training Session'),
       (UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440012'), '2033-01-25 15:30:00', 3600, UUID_TO_BIN('f7d9a1b2-4c5d-4e6f-8a9f-1b2c3d4e5f7f'),
        UUID_TO_BIN('550e8400-e29b-41d4-a716-446655440002'), UUID_TO_BIN('c3e5f7d9-2a4b-4c6d-8e9f-3c4d5e6f7a8c'), 'Strength Training Session');
