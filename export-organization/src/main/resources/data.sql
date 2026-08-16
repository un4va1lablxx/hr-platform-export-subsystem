INSERT INTO organization (id, name, type, status, is_raid_card)
VALUES ('11111111-1111-1111-1111-111111111111', 'ООО Технологии Надежности', 'EXECUTOR', 'APPROVED', false)
ON CONFLICT (id) DO NOTHING;

INSERT INTO legal_profile (id, inn, kpp, ogrn, legal_address, actual_address, type_legal_entity)
VALUES ('11111111-1111-1111-1111-111111111111', '7712345678', '770101001', '1027700123456', 'Москва, ул. Ленина 1', 'Москва, ул. Ленина 1', 'OOO')
ON CONFLICT (id) DO NOTHING;
