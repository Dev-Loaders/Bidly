-- INSERT into bidly_user (jwt_id, full_name, email) values ('1', 'Test Tester', 'test@test.com') ON CONFLICT DO NOTHING;
-- INSERT into job (title, description, location, image_url, materials) values ('test title', 'test description', 'stockholm', 'https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRyQm5KEbQq_UcD2ov-f3-HkPL49WielK_fvsCOh8EXmP-rsNP_KZnow56OTiOqVJl5', false);
-- INSERT into bid (bid_amount) values (25) ON CONFLICT DO NOTHING;

-- USERS

INSERT INTO bidly_user (jwt_id, full_name, email)
VALUES
    ('JWT_ID_1', 'John Doe', 'john@example.com'),
    ('JWT_ID_2', 'Jane Smith', 'jane@example.com'),
    ('JWT_ID_3', 'Bob Johnson', 'bob@example.com');

-- JOBS

INSERT INTO job (title, description, location, image_url, materials, created, updated)
VALUES
    ('Job Title 1', 'Job Description 1', 'Location 1', 'http://example.com/image1.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Job Title 2', 'Job Description 2', 'Location 2', 'http://example.com/image2.jpg', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Job Title 3', 'Job Description 3', 'Location 3', 'http://example.com/image3.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Job Title 4', 'Job Description 4', 'Location 4', 'http://example.com/image4.jpg', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
    ('Job Title 5', 'Job Description 5', 'Location 5', 'http://example.com/image5.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);
