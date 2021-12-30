package com.challenge.application.controller;

import com.challenge.application.domain.ProductDto;
import com.challenge.application.exceptions.DaoException;
import com.challenge.application.service.VendingMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendingMachineController {
    private static final Logger LOG = LoggerFactory.getLogger(VendingMachineController.class);

    private final VendingMachineService vendingMachineService;

    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping(path = "/api/vendingMachine/itemsList")
    public ResponseEntity<List<ProductDto>> getItemsList() {
        List<ProductDto> productDto;
        try {
           productDto = vendingMachineService.getItemsList();
        } catch (DaoException e) {
            LOG.warn(e.getMessage());
            LOG.debug(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping(value = "/api/vendingMachine/fillMachine")
    public ResponseEntity<?> insertItems(@RequestBody ProductDto product) {
        try {
            vendingMachineService.insertItems(product.getName(), product.getAmount(), product.getPrice());
        } catch (DaoException e) {
            LOG.warn(e.getMessage());
            LOG.debug(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/api/vendingMachine/getById/{slot}")
    public ResponseEntity<ProductDto> getItemBySlot(@PathVariable("terminalSlot") String terminalSlot) {
        ProductDto product;
        try {
          product = vendingMachineService.getItemBySlot(terminalSlot);
        } catch (DaoException e) {
            LOG.warn(e.getMessage());
            LOG.debug(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOG.debug(String.format("Product from terminalSlot [%s] was successfully bought", terminalSlot));
        return ResponseEntity.ok().body(product);
    }
}
