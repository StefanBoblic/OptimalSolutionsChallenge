package com.challenge.application.service;

import com.challenge.application.dao.VendingMachineDao;
import com.challenge.application.domain.ProductDto;
import com.challenge.application.exceptions.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private final VendingMachineDao vendingMachineDao;

    @Autowired
    public VendingMachineServiceImpl(VendingMachineDao vendingMachineDao) {
        this.vendingMachineDao = vendingMachineDao;
    }

    @Override
    public List<ProductDto> getItemsList() throws DaoException {
        return vendingMachineDao.getItemsList();
    }

    @Override
    public void insertItems(String name, int amount, double price) throws DaoException {
        vendingMachineDao.insertItems(name, amount, price);
    }

    @Override
    public ProductDto getItemBySlot(String terminalSlot) throws DaoException {
        return vendingMachineDao.getItemBySlot(terminalSlot);
    }

}
