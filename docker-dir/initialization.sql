
CREATE TABLE public.goal_management_audit_log (
                id NUMERIC(22) NOT NULL,
                action VARCHAR(50) NOT NULL,
                resource VARCHAR(50) NOT NULL,
                at_time TIMESTAMP NOT NULL,
                by_client_account_id NUMERIC(22) NOT NULL,
                client_unique_display_name VARCHAR(100) NOT NULL,
                original_record VARCHAR(1024),
                final_record VARCHAR(1024) NOT NULL,
                CONSTRAINT goal_management_audit_log_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.goal_management_audit_log IS 'A generic audit_log_table to track what is going
on in the database.';
COMMENT ON COLUMN public.goal_management_audit_log.action IS 'What kind of action happened..?';
COMMENT ON COLUMN public.goal_management_audit_log.resource IS 'On what resource?';
COMMENT ON COLUMN public.goal_management_audit_log.at_time IS 'At what date/time ?';
COMMENT ON COLUMN public.goal_management_audit_log.client_unique_display_name IS 'The unique display name of the client';
COMMENT ON COLUMN public.goal_management_audit_log.original_record IS 'The original record. Could be a JSON object';
COMMENT ON COLUMN public.goal_management_audit_log.final_record IS 'The final record.';


CREATE TABLE public.client_account (
                id NUMERIC(22) NOT NULL,
                primary_email VARCHAR(255) NOT NULL,
                first_name VARCHAR(50) NOT NULL,
                last_name VARCHAR(50) NOT NULL,
                unique_display_name VARCHAR(50) NOT NULL,
                email_verified BOOLEAN NOT NULL,
                CONSTRAINT client_account_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.client_account IS 'This holds the account information of the client.  This happens when the client registers and creates an account.';
COMMENT ON COLUMN public.client_account.id IS 'Unique Primary Key';
COMMENT ON COLUMN public.client_account.primary_email IS 'The primary email of the person. This needs to be unique in the system.';
COMMENT ON COLUMN public.client_account.unique_display_name IS 'A unique display name for purpose of addressing the user.';
COMMENT ON COLUMN public.client_account.email_verified IS 'indicates if the email was verified or not.';


CREATE UNIQUE INDEX client_account_unique_email_idx
 ON public.client_account
 ( primary_email );

CREATE UNIQUE INDEX client_account_unique_display_name_idx
 ON public.client_account
 ( unique_display_name );

CREATE TABLE public.org_goal_domain (
                id NUMERIC(22) NOT NULL,
                title VARCHAR(255) NOT NULL,
                owner_client_account_id NUMERIC(22) NOT NULL,
                description VARCHAR(1024) NOT NULL,
                Parent_id NUMERIC(22),
                CONSTRAINT org_goal_domain_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.org_goal_domain IS 'This classifies a domain under which org goals are being defined.';
COMMENT ON COLUMN public.org_goal_domain.id IS 'The PK';
COMMENT ON COLUMN public.org_goal_domain.title IS 'The title of the domain';
COMMENT ON COLUMN public.org_goal_domain.owner_client_account_id IS 'The domain owner';
COMMENT ON COLUMN public.org_goal_domain.description IS 'The description of the domain.';
COMMENT ON COLUMN public.org_goal_domain.Parent_id IS 'The PK';


CREATE UNIQUE INDEX org_goal_domain_unique_domain_idx
 ON public.org_goal_domain
 ( title );

CREATE TABLE public.tracked_item (
                id NUMERIC(22) NOT NULL,
                unique_name VARCHAR(255) NOT NULL,
                owner_client_account_id NUMERIC(22) NOT NULL,
                description VARCHAR(1024) NOT NULL,
                owning_org_goal_domain_id NUMERIC(22),
                CONSTRAINT tracked_item_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.tracked_item IS 'This is the table for a tracked item.';
COMMENT ON COLUMN public.tracked_item.id IS 'The PK';
COMMENT ON COLUMN public.tracked_item.unique_name IS 'The unique name of the item being tracked.';
COMMENT ON COLUMN public.tracked_item.owner_client_account_id IS 'The owner of the item';
COMMENT ON COLUMN public.tracked_item.description IS 'The description of the item being tracked.';
COMMENT ON COLUMN public.tracked_item.owning_org_goal_domain_id IS 'The domain to which this item belongs. If null, then it means it is global item.';


CREATE UNIQUE INDEX tracked_item_unique_idx
 ON public.tracked_item
 ( unique_name, owning_org_goal_domain_id );

CREATE TABLE public.org_goal_definition (
                id NUMERIC(22) NOT NULL,
                title VARCHAR(255) NOT NULL,
                description VARCHAR(1024) NOT NULL,
                goal_weight DOUBLE PRECISION,
                goal_definition_state VARCHAR(50) NOT NULL,
                owner_client_account_id NUMERIC(22) NOT NULL,
                org_goal_domain_id NUMERIC(22) NOT NULL,
                Parent_id NUMERIC(22),
                CONSTRAINT org_goal_definition_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.org_goal_definition IS 'This defines a goal of an organization.';
COMMENT ON COLUMN public.org_goal_definition.id IS 'The PK.';
COMMENT ON COLUMN public.org_goal_definition.title IS 'Title of the goal.';
COMMENT ON COLUMN public.org_goal_definition.description IS 'Describe the goal. what it entails.';
COMMENT ON COLUMN public.org_goal_definition.goal_weight IS 'The weight of the goal with respect to other goals at the same hierarchy';
COMMENT ON COLUMN public.org_goal_definition.goal_definition_state IS 'The state of the goal, could be draft, could be active.. etc.
Goals definitions in draft state may not be shared across the enterprise..';
COMMENT ON COLUMN public.org_goal_definition.owner_client_account_id IS 'The creator of the goal/editor/owner of the goal definition.';
COMMENT ON COLUMN public.org_goal_definition.org_goal_domain_id IS 'The domain to which this goal belongs.';
COMMENT ON COLUMN public.org_goal_definition.Parent_id IS 'If this is a SUB Goal, then the parent id of the sub goal. If this is a top level goal, then this would be null..';


CREATE UNIQUE INDEX org_goal_definition_unique_goal_title_idx
 ON public.org_goal_definition
 ( title, org_goal_domain_id );

CREATE TABLE public.goal_track_card (
                id NUMERIC(22) NOT NULL,
                owner_client_account_id NUMERIC(22) NOT NULL,
                org_goal_domain_id NUMERIC(22) NOT NULL,
                base_org_goal_definition_id NUMERIC(22) NOT NULL,
                title VARCHAR(255) NOT NULL,
                description VARCHAR(1024) NOT NULL,
                CONSTRAINT goal_track_card_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.goal_track_card IS 'This is a record to track a goal of a particular type against one or more items.';
COMMENT ON COLUMN public.goal_track_card.id IS 'The PK';
COMMENT ON COLUMN public.goal_track_card.owner_client_account_id IS 'The owner who owns this track card.';
COMMENT ON COLUMN public.goal_track_card.org_goal_domain_id IS 'The domain to which this card belongs.';
COMMENT ON COLUMN public.goal_track_card.base_org_goal_definition_id IS 'The base goal that this card tracks.';
COMMENT ON COLUMN public.goal_track_card.title IS 'The title for this card.';
COMMENT ON COLUMN public.goal_track_card.description IS 'Describe the tracking card.';


CREATE UNIQUE INDEX goal_track_card_unique_title_idx
 ON public.goal_track_card
 ( org_goal_domain_id, title );

CREATE TABLE public.tracked_item_to_card (
                id VARCHAR(22) NOT NULL,
                goal_track_card_id NUMERIC(22) NOT NULL,
                tracked_item_id NUMERIC(22) NOT NULL,
                CONSTRAINT tracked_item_to_card_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.tracked_item_to_card IS 'Relationship table to track an item to a card.';
COMMENT ON COLUMN public.tracked_item_to_card.id IS 'The PK';
COMMENT ON COLUMN public.tracked_item_to_card.goal_track_card_id IS 'The PK';
COMMENT ON COLUMN public.tracked_item_to_card.tracked_item_id IS 'The PK';


CREATE INDEX tracked_item_to_card_unique_idx
 ON public.tracked_item_to_card
 ( goal_track_card_id, tracked_item_id );

CREATE TABLE public.tracked_item_goal (
                id NUMERIC(22) NOT NULL,
                org_goal_definition_id NUMERIC(22) NOT NULL,
                current_value DOUBLE PRECISION NOT NULL,
                last_uptated_by_client_account_id NUMERIC(22) NOT NULL,
                last_updated_time TIMESTAMP NOT NULL,
                tracked_item_to_card_id VARCHAR(22) NOT NULL,
                CONSTRAINT tracked_item_goal_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.tracked_item_goal IS 'The actual value table where the tracked_items goal value is stored and tracked.';
COMMENT ON COLUMN public.tracked_item_goal.id IS 'The PK';
COMMENT ON COLUMN public.tracked_item_goal.org_goal_definition_id IS 'The PK.';
COMMENT ON COLUMN public.tracked_item_goal.current_value IS 'The current value of the item, -1 or NULL means not yet defined.';
COMMENT ON COLUMN public.tracked_item_goal.last_uptated_by_client_account_id IS 'The value was last updated by which id.. It is nullable especially when it is never updated..';
COMMENT ON COLUMN public.tracked_item_goal.last_updated_time IS 'The date/time when it was last updated.';
COMMENT ON COLUMN public.tracked_item_goal.tracked_item_to_card_id IS 'The PK';


CREATE TABLE public.tracked_item_goal_history (
                id NUMERIC(22) NOT NULL,
                historical_value DOUBLE PRECISION NOT NULL,
                at_time TIMESTAMP NOT NULL,
                tracked_item_goal_id NUMERIC(22) NOT NULL,
                CONSTRAINT tracked_item_goal_history_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.tracked_item_goal_history IS 'The history of a tracked item.';
COMMENT ON COLUMN public.tracked_item_goal_history.id IS 'The PK';
COMMENT ON COLUMN public.tracked_item_goal_history.historical_value IS 'The value of what it was';
COMMENT ON COLUMN public.tracked_item_goal_history.at_time IS 'at what time did it have this value..';
COMMENT ON COLUMN public.tracked_item_goal_history.tracked_item_goal_id IS 'The PK';


CREATE TABLE public.goal_value_type (
                id NUMERIC(22) NOT NULL,
                title VARCHAR(100) NOT NULL,
                owner_client_account_id NUMERIC(22) NOT NULL,
                org_goal_definition_id NUMERIC(22),
                goal_definition_state VARCHAR(50) NOT NULL,
                org_goal_domain_id NUMERIC(22) NOT NULL,
                CONSTRAINT goal_value_type_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.goal_value_type IS 'This table acts a lookup table for goal_value_types';
COMMENT ON COLUMN public.goal_value_type.id IS 'The PK';
COMMENT ON COLUMN public.goal_value_type.title IS 'The value type definition for a goal.';
COMMENT ON COLUMN public.goal_value_type.owner_client_account_id IS 'The person defining this value type. If the';
COMMENT ON COLUMN public.goal_value_type.org_goal_definition_id IS 'If null, then it means that this goal_value_type can be used in any goal. If not null, it could mean that it could be only used in this chain and below or something like that.';
COMMENT ON COLUMN public.goal_value_type.goal_definition_state IS 'This could be a state, such as draft, or approved, or active.. etc.';
COMMENT ON COLUMN public.goal_value_type.org_goal_domain_id IS 'The domain it belongs to, or NULL if it is global.';


CREATE UNIQUE INDEX goal_value_type_unique_title_idx
 ON public.goal_value_type
 ( title, org_goal_definition_id );

CREATE TABLE public.goal_value_type_options (
                id NUMERIC(22) NOT NULL,
                goal_value_type_id NUMERIC(22) NOT NULL,
                title VARCHAR(50) NOT NULL,
                owner_client_account_id NUMERIC(22) NOT NULL,
                option_value DOUBLE PRECISION NOT NULL,
                CONSTRAINT goal_value_type_options_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.goal_value_type_options IS 'This table contains options for the goal value types';
COMMENT ON COLUMN public.goal_value_type_options.goal_value_type_id IS 'The value type to which this option belongs.';
COMMENT ON COLUMN public.goal_value_type_options.title IS 'The name of the option, should be unique within the option type.';
COMMENT ON COLUMN public.goal_value_type_options.owner_client_account_id IS 'The id of the creator of this option.';
COMMENT ON COLUMN public.goal_value_type_options.option_value IS 'The value of this option in numbers';


CREATE UNIQUE INDEX goal_value_type_options_unique_title_idx
 ON public.goal_value_type_options
 ( goal_value_type_id, title );

CREATE TABLE public.client_identity (
                id NUMERIC(22) NOT NULL,
                unique_identity_id VARCHAR(100) NOT NULL,
                idp_id VARCHAR(100) NOT NULL,
                last_login TIMESTAMP NOT NULL,
                CONSTRAINT client_identity_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.client_identity IS 'This represents a logged in client Identity..';
COMMENT ON COLUMN public.client_identity.id IS 'The PK';
COMMENT ON COLUMN public.client_identity.unique_identity_id IS 'This should be a unique identity in the system within the IDP (provider).';
COMMENT ON COLUMN public.client_identity.idp_id IS 'Identity of the provider, such as google, or facebook.';
COMMENT ON COLUMN public.client_identity.last_login IS 'The timestamp of when it logged in last.';


CREATE UNIQUE INDEX unique_composite_identity_idx
 ON public.client_identity
 ( unique_identity_id, idp_id );

CREATE TABLE public.client_identities (
                id NUMERIC(22) NOT NULL,
                client_account_id NUMERIC(22) NOT NULL,
                client_identity_id NUMERIC(22) NOT NULL,
                CONSTRAINT client_identities_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.client_identities IS 'This is a relationship table of mapping multiple login identities to a client account.';
COMMENT ON COLUMN public.client_identities.id IS 'The PK';
COMMENT ON COLUMN public.client_identities.client_account_id IS 'Unique Primary Key';


CREATE UNIQUE INDEX client_identities_unique_combo_idx
 ON public.client_identities
 ( client_account_id, client_identity_id );

ALTER TABLE public.client_identities ADD CONSTRAINT client_account_client_identities_fk1
FOREIGN KEY (client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.org_goal_definition ADD CONSTRAINT client_account_org_goal_definition_fk
FOREIGN KEY (id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.org_goal_definition ADD CONSTRAINT client_account_org_goal_definition_fk1
FOREIGN KEY (owner_client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_value_type ADD CONSTRAINT client_account_goal_value_type_fk1
FOREIGN KEY (owner_client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.org_goal_domain ADD CONSTRAINT client_account_org_goal_domain_fk1
FOREIGN KEY (owner_client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_value_type_options ADD CONSTRAINT client_account_goal_value_type_options_fk1
FOREIGN KEY (owner_client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_track_card ADD CONSTRAINT client_account_goal_track_card_fk1
FOREIGN KEY (owner_client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item ADD CONSTRAINT client_account_tracked_item_fk1
FOREIGN KEY (owner_client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item_goal ADD CONSTRAINT client_account_tracked_item_goal_fk1
FOREIGN KEY (last_uptated_by_client_account_id)
REFERENCES public.client_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.org_goal_definition ADD CONSTRAINT org_goal_domain_org_goal_definition_fk1
FOREIGN KEY (org_goal_domain_id)
REFERENCES public.org_goal_domain (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_value_type ADD CONSTRAINT org_goal_domain_goal_value_type_fk1
FOREIGN KEY (org_goal_domain_id)
REFERENCES public.org_goal_domain (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_track_card ADD CONSTRAINT org_goal_domain_goal_track_card_fk1
FOREIGN KEY (org_goal_domain_id)
REFERENCES public.org_goal_domain (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item ADD CONSTRAINT org_goal_domain_tracked_item_fk1
FOREIGN KEY (owning_org_goal_domain_id)
REFERENCES public.org_goal_domain (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.org_goal_domain ADD CONSTRAINT org_goal_domain_org_goal_domain_fk
FOREIGN KEY (Parent_id)
REFERENCES public.org_goal_domain (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item_to_card ADD CONSTRAINT tracked_item_tracked_item_to_card_fk
FOREIGN KEY (tracked_item_id)
REFERENCES public.tracked_item (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_value_type ADD CONSTRAINT org_goal_definition_goal_value_type_fk1
FOREIGN KEY (org_goal_definition_id)
REFERENCES public.org_goal_definition (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.org_goal_definition ADD CONSTRAINT org_goal_definition_org_goal_definition_fk
FOREIGN KEY (Parent_id)
REFERENCES public.org_goal_definition (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_track_card ADD CONSTRAINT org_goal_definition_goal_track_card_fk1
FOREIGN KEY (base_org_goal_definition_id)
REFERENCES public.org_goal_definition (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item_goal ADD CONSTRAINT org_goal_definition_tracked_item_goal_fk1
FOREIGN KEY (org_goal_definition_id)
REFERENCES public.org_goal_definition (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item_to_card ADD CONSTRAINT goal_track_card_tracked_item_to_card_fk
FOREIGN KEY (goal_track_card_id)
REFERENCES public.goal_track_card (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item_goal ADD CONSTRAINT tracked_item_to_card_tracked_item_goal_fk
FOREIGN KEY (tracked_item_to_card_id)
REFERENCES public.tracked_item_to_card (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tracked_item_goal_history ADD CONSTRAINT tracked_item_goal_tracked_item_goal_history_fk1
FOREIGN KEY (tracked_item_goal_id)
REFERENCES public.tracked_item_goal (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.goal_value_type_options ADD CONSTRAINT goal_value_type_goal_value_type_options_fk1
FOREIGN KEY (goal_value_type_id)
REFERENCES public.goal_value_type (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.client_identities ADD CONSTRAINT client_identity_client_identities_fk1
FOREIGN KEY (client_identity_id)
REFERENCES public.client_identity (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- Create the SEQUENCES for each table..
CREATE SEQUENCE "seq_client_identity_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_client_identity_id" OWNER TO postgres;

CREATE SEQUENCE "seq_client_identitities_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_client_identitities_id" OWNER TO postgres;

CREATE SEQUENCE "seq_client_account_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_client_account_id" OWNER TO postgres;

CREATE SEQUENCE "seq_org_goal_domain_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_org_goal_domain_id" OWNER TO postgres;

CREATE SEQUENCE "seq_org_goal_definition_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_org_goal_definition_id" OWNER TO postgres;

CREATE SEQUENCE "seq_goal_value_type_options_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_goal_value_type_options_id" OWNER TO postgres;

CREATE SEQUENCE "seq_goal_value_type_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_goal_value_type_id" OWNER TO postgres;

CREATE SEQUENCE "seq_tracked_item_goal_history_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_tracked_item_goal_history_id" OWNER TO postgres;

CREATE SEQUENCE "seq_tracked_item_goal_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_tracked_item_goal_id" OWNER TO postgres;

CREATE SEQUENCE "seq_tracked_item_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_tracked_item_id" OWNER TO postgres;

CREATE SEQUENCE "seq_goal_track_card_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_goal_track_card_id" OWNER TO postgres;

CREATE SEQUENCE "seq_tracked_item_to_card_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_tracked_item_to_card_id" OWNER TO postgres;

CREATE SEQUENCE "seq_goal_management_audit_log_id" INCREMENT 50  START 10000  MINVALUE 1  MAXVALUE 1000000000 CACHE 1;
ALTER SEQUENCE "seq_goal_management_audit_log_id" OWNER TO postgres;