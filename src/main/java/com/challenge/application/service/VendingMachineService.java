package com.challenge.application.service;

import com.challenge.application.domain.ProductDto;
import com.challenge.application.exceptions.DaoException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendingMachineService {
    /**
     * Gets item list from vending machine
     *
     * @return - List of items in vending machine
     * @throws DaoException - on any db error
     */
    List<ProductDto> getItemsList() throws DaoException;

    /**
     * Update item list in vending machine
     *
     * @param name      - filter by item name
     * @param amount    - filter by amount
     * @param price     - filter by price
     * @throws DaoException - on any db error
     */
    void insertItems(String name, int amount, double price) throws DaoException;

    /**
     * Gets item by slot, for ex. 1A = coke
     *
     * @param terminalSlot - slot of item in machine
     * @return - item by slot info
     * @throws DaoException          - on any db error
     */
    ProductDto getItemBySlot(String terminalSlot) throws DaoException;
}
