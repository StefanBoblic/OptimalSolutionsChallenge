package com.challenge.application.dao;


import com.challenge.application.domain.Product;
import com.challenge.application.exceptions.DaoException;

import java.util.List;

public interface JsonDao {
    List<Product> loadJsonData(String name, int amount, double price) throws DaoException;
}
