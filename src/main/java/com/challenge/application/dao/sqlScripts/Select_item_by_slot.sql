CREATE OR ALTER PROCEDURE uspGetProductInfoByTerminalSlot
@pTerminalSlot varchar(20)
AS
BEGIN
    SELECT items.Name,
           items.Amount,
           items.Price
    FROM Items as items
             JOIN MachineSlots as machineslots
                  ON items.ProductId = machineslots.ProductId
    WHERE machineslots.TerminalSlot = @pTerminalSlot
    UPDATE Items
    SET Amount = Amount - 1
    WHERE ProductId = (SELECT ProductId FROM MachineSlots WHERE machineslots.ProductId = Items.ProductId)
    UPDATE PurchaseHistory
    SET Amount = Amount + 1,
        ProductName = (SELECT Name FROM Items WHERE moneySpent = Items.Price),
        MoneySpent = (SELECT Price FROM Items WHERE productName = Items.Name),
        whenBought = current_timestamp,
        State = 'SUCCESSFUL'
END
go

