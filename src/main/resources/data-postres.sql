INSERT into user (user_id, jwtId, fullName, email) values (1, '1', 'Test Tester', 'test@test.com') ON CONFLICT DO NOTHING;
