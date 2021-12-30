package com.challenge.application.service;

import com.challenge.application.dao.JsonDao;
import com.challenge.application.domain.Product;
import com.challenge.application.exceptions.DaoException;
import com.challenge.application.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonServiceImpl implements JsonService {

    private final JsonDao jsonDao;

    @Autowired
    public JsonServiceImpl(JsonDao jsonDao) {
        this.jsonDao = jsonDao;
    }

    @Override
    public List<Product> loadJsonData(String name, int amount, double price) throws DaoException {
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.deserializeFile();
        return jsonDao.loadJsonData(name, amount, price);
    }
}
