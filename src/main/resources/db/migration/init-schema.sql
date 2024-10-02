CREATE TABLE categories
(
    id              BIGINT NOT NULL,
    create_at       datetime NULL,
    last_updated_at datetime NULL,
    state           SMALLINT NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE products
(
    id                BIGINT NOT NULL,
    create_at         datetime NULL,
    last_updated_at   datetime NULL,
    state             SMALLINT NULL,
    title             VARCHAR(255) NULL,
    `description`     VARCHAR(255) NULL,
    image_url         VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id       BIGINT NULL,
    is_prime_specific BIT(1) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);