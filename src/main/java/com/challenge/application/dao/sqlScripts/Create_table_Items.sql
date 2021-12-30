CREATE TABLE IF NOT EXISTS Items
(
    ProductId BIGINT NOT NULL IDENTITY(1,1) primary key,
    Name      VARCHAR(255),
    Amount    INT,
    Price     FLOAT,

);
