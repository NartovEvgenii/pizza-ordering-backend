CREATE SEQUENCE IF NOT EXISTS address_id_address_seq START 1;

CREATE TABLE IF NOT EXISTS public.address
(
    id_address bigint NOT NULL DEFAULT nextval('address_id_address_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id_address),
    CONSTRAINT uk_djsxpygkfpmu6otj9yxi4e3fs UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS public.cooking_plan
(
    id_cooking_plan SERIAL NOT NULL,
    base_preparation_time interval NOT NULL,
    oven_time interval NOT NULL,
    packing_time interval NOT NULL,
    CONSTRAINT cooking_plan_pkey PRIMARY KEY (id_cooking_plan)
);

CREATE TABLE IF NOT EXISTS public.pizza
(
    id_pizza SERIAL NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    image_path character varying(255) COLLATE pg_catalog."default",
    price double precision NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fk_cooking_plan bigint,
    CONSTRAINT pizza_pkey PRIMARY KEY (id_pizza),
    CONSTRAINT uk_41lohxyifiupny7tm23i5mlx0 UNIQUE (title),
    CONSTRAINT fk4orf29v3ic249eixt5nlumej3 FOREIGN KEY (fk_cooking_plan)
        REFERENCES public.cooking_plan (id_cooking_plan) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.order_state
(
    id_order_state SERIAL NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    identifier character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_state_pkey PRIMARY KEY (id_order_state),
    CONSTRAINT uk_3rmbx4jg4canq7lc35dm8x1ai UNIQUE (title),
    CONSTRAINT uk_lc7w59mt2i33j5jq5ojg5sckw UNIQUE (identifier)
);

CREATE TABLE IF NOT EXISTS public.payment_type
(
    id_payment_type SERIAL NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    identifier character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT payment_type_pkey PRIMARY KEY (id_payment_type),
    CONSTRAINT uk_bdy39454a77alrbmmoruhnp9b UNIQUE (title),
    CONSTRAINT uk_lc7w59mt2i33j5jq5ojg5sckg UNIQUE (identifier)
);

CREATE TABLE IF NOT EXISTS public.mobile_user
(
    id_user SERIAL NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default",
    fk_general_address bigint,
    CONSTRAINT mobile_user_pkey PRIMARY KEY (id_user),
    CONSTRAINT uk_qw0xxin36txphdb3po4md3lmx UNIQUE (email),
    CONSTRAINT fkpse7gtwo6v5pm4d1etueltfhl FOREIGN KEY (fk_general_address)
        REFERENCES public.address (id_address) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS user_order_id_order_seq START 1;

CREATE TABLE IF NOT EXISTS public.user_order
(
    id_order bigint NOT NULL DEFAULT nextval('user_order_id_order_seq'::regclass),
    full_price double precision NOT NULL,
    fk_address bigint,
    fk_user bigint,
    fk_order_state bigint,
    fk_payment_type bigint,
    CONSTRAINT user_order_pkey PRIMARY KEY (id_order),
    CONSTRAINT fkapd8cccj3poqb6hio8h4x5cpc FOREIGN KEY (fk_address)
        REFERENCES public.address (id_address) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkbes4vrr7lt40q54ggy5ydtagp FOREIGN KEY (fk_user)
        REFERENCES public.mobile_user (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkgtg6gixibhmi9t9srgg79wmh FOREIGN KEY (fk_payment_type)
        REFERENCES public.payment_type (id_payment_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkkpj1oukb3dadjk0s8f6wgwx3q FOREIGN KEY (fk_order_state)
        REFERENCES public.order_state (id_order_state) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS order_item_id_order_item_seq START 1;

CREATE TABLE IF NOT EXISTS public.order_item
(
    id_order_item bigint NOT NULL DEFAULT nextval('order_item_id_order_item_seq'::regclass),
    count_items integer,
    price double precision,
    fk_order bigint,
    fk_pizza bigint,
    CONSTRAINT order_item_pkey PRIMARY KEY (id_order_item),
    CONSTRAINT fki2q0ni9dk1odvprfx73chi6d8 FOREIGN KEY (fk_order)
        REFERENCES public.user_order (id_order) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkt50ygw54xtsy6hftguldnkwj7 FOREIGN KEY (fk_pizza)
        REFERENCES public.pizza (id_pizza) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS or_hist_id_or_hist_seq START 1;

CREATE TABLE IF NOT EXISTS public.user_order_history
(
    id_order_history bigint NOT NULL DEFAULT nextval('or_hist_id_or_hist_seq'::regclass),
    change_time timestamp without time zone,
    fk_order_state bigint,
    fk_user_order bigint,
    CONSTRAINT user_order_history_pkey PRIMARY KEY (id_order_history),
    CONSTRAINT fk5a2r8601ujrikwwdkwqb20aec FOREIGN KEY (fk_user_order)
        REFERENCES public.user_order (id_order) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkqts7jog0ofdrxobn5jg7t39v2 FOREIGN KEY (fk_order_state)
        REFERENCES public.order_state (id_order_state) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.user_has_address
(
    id_user bigint NOT NULL,
    id_address bigint NOT NULL,
    CONSTRAINT fk9d2brcvvm11q74kv6rca5d3xo FOREIGN KEY (id_user)
        REFERENCES public.mobile_user (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkm5ri2p7uxe4qobtymj437kv1a FOREIGN KEY (id_address)
        REFERENCES public.address (id_address) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
