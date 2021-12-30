package com.challenge.application.controller;

import com.challenge.application.domain.Product;
import com.challenge.application.exceptions.DaoException;
import com.challenge.application.service.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonController {
    private static final Logger LOG = LoggerFactory.getLogger(VendingMachineController.class);

    private final JsonService jsonService;

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping(path = "/api/jsonFile/getItemList")
    public ResponseEntity<List<Product>> getJsonItemsList(
            @RequestParam(value = "name", required = false, defaultValue = "0") String name,
            @RequestParam(value = "amount", required = false, defaultValue = "0") int amount,
            @RequestParam(value = "price", required = false, defaultValue = "0") double price
    ) {
        List<Product> product;
        try {
            product = jsonService.loadJsonData(name, amount, price);
        } catch (DaoException e) {
            LOG.warn(e.getMessage());
            LOG.debug(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().body(product);
    }
}
