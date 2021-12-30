CREATE TABLE IF NOT EXISTS PurchaseHistory
(
    ProductName VARCHAR(255),
    Amount     BIGINT,
    MoneySpent FLOAT,
    WhenBought datetime2,
    STATE VARCHAR(255)
);