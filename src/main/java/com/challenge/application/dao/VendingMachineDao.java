package com.challenge.application.dao;

import com.challenge.application.domain.ProductDto;
import com.challenge.application.exceptions.DaoException;

import java.util.List;

public interface VendingMachineDao {
    void insertItems(String name, int amount, double price) throws DaoException;

    List<ProductDto> getItemsList() throws DaoException;

    ProductDto getItemBySlot(String terminalSlot) throws DaoException;
}
