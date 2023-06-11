package com.smallworld.data.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;

import java.util.List;

public class TransactionMapper {

    public static List<Transaction> convertJsonToPojo(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> transactions = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));
        return transactions;
    }
}
