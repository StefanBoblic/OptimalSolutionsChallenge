CREATE OR ALTER PROCEDURE uspAddItemsToMachine
    @pName VARCHAR(255),
    @pAmount INT,
    @pPrice FLOAT
AS
BEGIN
INSERT INTO Items
(Name, Amount, Price)
    OUTPUT inserted.ProductId
VALUES
    (@pName, @pAmount, @pPrice)
    END
go

