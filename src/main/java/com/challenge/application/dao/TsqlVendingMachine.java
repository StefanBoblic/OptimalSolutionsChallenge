package com.challenge.application.dao;

import com.challenge.application.domain.ProductDto;
import com.challenge.application.exceptions.DaoException;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Repository
public class TsqlVendingMachine implements VendingMachineDao {
    private final DataSource db;

    @Autowired
    public TsqlVendingMachine(DataSource db) {
        this.db = requireNonNull(db);
    }

    @Override
    public void insertItems(String name, int amount, double price) throws DaoException {
        try {
            new JdbcSession(db)
                    .sql("EXEC uspAddItemsToMachine ?, ?, ?")
                    .prepare(stmt -> {
                        stmt.setString(1, name);
                        stmt.setInt(2, amount);
                        stmt.setDouble(3, price);
                    })
                    .select(Outcome.NOT_EMPTY);
        } catch (SQLException e) {
            String msg = "Error inserting data in machine";
            throw new DaoException(msg, e);
        }
    }

    @Override
    public List<ProductDto> getItemsList() throws DaoException {
        try {
            return new JdbcSession(db)
                    .sql("SELECT ProductId, Name, Amount, Price FROM Items")
                    .select(new ListOutcome<>(rset -> ProductDto.newBuilder()
                            .productId(rset.getLong("PRODUCTID"))
                            .name(rset.getString("NAME"))
                            .amount(rset.getInt("AMOUNT"))
                            .price(rset.getDouble("PRICE"))
                            .build()));
        } catch (SQLException e) {
            String msg = "Error outputting product list";
            throw new DaoException(msg, e);
        }
    }

    @Override
    public ProductDto getItemBySlot(String terminalSlot) throws DaoException {
        try {
            return new JdbcSession(db)
                    .sql("EXEC uspGetProductByTerminalSlot ?")
                    .prepare(stmt -> {
                        stmt.setString(1, terminalSlot);
                    })
                    .select((rset, stmt) -> {
                        rset.next();
                        return ProductDto.newBuilder()
                                .name(rset.getString("NAME"))
                                .amount(rset.getInt("AMOUNT"))
                                .price(rset.getDouble("PRICE"))
                                .build();
                    });
        } catch (SQLException e) {
            String msg = "Error getting product info by name";
            throw new DaoException(msg, e);
        }
    }
}