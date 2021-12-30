package com.challenge.application.dao;

import com.challenge.application.domain.Product;
import com.challenge.application.domain.ProductDto;
import com.challenge.application.exceptions.DaoException;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Repository
public class TsqlJsonDao implements JsonDao{

    private final DataSource db;

    @Autowired
    public TsqlJsonDao(DataSource db) {
        this.db = requireNonNull(db);
    }



    @Override
    public List<Product> loadJsonData(String name, int amount, double price) throws DaoException {
        try {
           return new JdbcSession(db)
                    .sql("INSERT INTO ITEMS VALUES (?, ?, ?)")
                    .prepare(stmt -> {
                        stmt.setString(1, name);
                        stmt.setInt(2, amount);
                        stmt.setDouble(3, price);
                    })
                   .select(new ListOutcome<>(rset -> Product.builder()
                           .name(rset.getString("NAME"))
                           .amount(rset.getInt("AMOUNT"))
                           .price(rset.getDouble("PRICE"))
                           .build()));
        } catch (SQLException e) {
            String msg = "Error outpiting new data";
            throw new DaoException(msg, e);
        }
    }
}
