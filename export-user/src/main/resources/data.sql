INSERT INTO "user" (id, organization_id, last_name, first_name, middle_name, specialization, email, status)
VALUES ('22222222-2222-2222-2222-222222222222', '11111111-1111-1111-1111-111111111111', 'Иванов', 'Иван', 'Иванович', 'JAVA_DEVELOPER', 'ivanov@gmail.com', 'ON_PROJECT')
ON CONFLICT (id) DO NOTHING;