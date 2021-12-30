package com.challenge.application.service;

import com.challenge.application.domain.Product;
import com.challenge.application.exceptions.DaoException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JsonService {

    List<Product> loadJsonData(String name, int amount, double price) throws DaoException;
}
