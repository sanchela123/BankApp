
--Only for test/delete db
--postgresql base/ name - bankdb
INSERT INTO bank_role VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO bank_currency VALUES (1, null, null, 'USD', null), (2, null, null, 'EUR', null) ON CONFLICT DO NOTHING;