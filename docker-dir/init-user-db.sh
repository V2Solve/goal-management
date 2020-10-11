#!/bin/bash
set -e

psql --echo-all -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

-- create the super client, owned by the super app created in the initialization SQL
-- insert into client (id,client_identifier,description,application_id) values (0,:'SUPER_CLIENT_ID','This client is built in super user.',0);

EOSQL