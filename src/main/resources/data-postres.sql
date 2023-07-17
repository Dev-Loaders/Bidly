INSERT into bidly_user (jwt_id, full_name, email) values ('1', 'Test Tester', 'test@test.com') ON CONFLICT DO NOTHING;
