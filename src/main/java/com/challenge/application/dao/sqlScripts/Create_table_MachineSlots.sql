CREATE TABLE IF NOT EXISTS MachineSlots
(
    ProductId    BIGINT NOT NULL IDENTITY(1,1) primary key,
    TerminalSlot VARCHAR(255)
);