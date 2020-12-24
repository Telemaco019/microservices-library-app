----------------------------------------
-------- Catalogue service DB ----------
----------------------------------------
CREATE USER catalogue_service_user WITH PASSWORD 'catalogue_service_password';
create database catalogue_service;
GRANT ALL PRIVILEGES ON DATABASE catalogue_service TO catalogue_service_user;
