package com.smallworld;

import com.smallworld.data.Transaction;
import com.smallworld.data.mapper.TransactionMapper;
import com.smallworld.data.reader.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TransactionDataFetcher {

    private static final Logger logger = Logger.getLogger(TransactionDataFetcher.class.getName());

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            double totalAmount = lstTransaction.stream()
                    .filter(transaction -> (transaction.isIssueSolved() || Objects.isNull(transaction.getIssueId())))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            logger.info("Total amount of tranaction is "+totalAmount);
            return totalAmount;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return 0.00;
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            double totalAmount = lstTransaction.stream()
                    .filter(transaction -> transaction.getSenderFullName().equals(senderFullName) && (transaction.isIssueSolved() || Objects.isNull(transaction.getIssueId())))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            logger.info("Total amount of tranaction "+totalAmount +" sent by "+senderFullName);
            return totalAmount;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return 0.00;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            double highestAmount = lstTransaction.stream()
                    .filter(transaction -> (transaction.isIssueSolved() || Objects.isNull(transaction.getIssueId())))
                    .mapToDouble(Transaction::getAmount)
                    .max()
                    .orElse(0.0);
            logger.info("Highest Tranaction amount is "+highestAmount);
            return highestAmount;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return 0.00;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            long uniqueClientsCount = lstTransaction.stream()
                    .flatMap(transaction -> List.of(transaction.getSenderFullName(), transaction.getBeneficiaryFullName()).stream())
                    .distinct()
                    .count();
            logger.info("Count of unique client "+uniqueClientsCount);
            return uniqueClientsCount;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return 0;
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            boolean hasUnsolvedIssue = lstTransaction.stream()
                    .filter(transaction -> transaction.getSenderFullName().equals(clientFullName)
                            || transaction.getBeneficiaryFullName().equals(clientFullName))
                    .anyMatch(transaction -> !transaction.isIssueSolved());
            if(hasUnsolvedIssue){
                logger.info("Sender/Receiver "+clientFullName + "has unsolved issue");
            }else{
                logger.info("Sender/Receiver "+clientFullName + "has all solved issue");
            }
            return hasUnsolvedIssue;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return true;
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String,Transaction> getTransactionsByBeneficiaryName() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            Map<String,Transaction> mapTransaction = lstTransaction.stream()
                    .filter(transaction -> (transaction.isIssueSolved() || Objects.isNull(transaction.getIssueId())))
                    .collect(Collectors.toMap(Transaction::getBeneficiaryFullName, Function.identity()));
            logger.info("transactions indexed by beneficiary name size is "+mapTransaction.size());
            return mapTransaction;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return null;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            Set<Integer> lstUnsolvedIssueIds = lstTransaction.stream()
                    .filter(transaction -> !transaction.isIssueSolved())
                    .map(Transaction::getIssueId)
                    .collect(Collectors.toSet());
            logger.info("the identifiers of all open compliance issues size is "+lstUnsolvedIssueIds.size());
            return lstUnsolvedIssueIds;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return null;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            List<String> lstSolvedIssueMessages = lstTransaction.stream()
                    .filter(transaction -> transaction.isIssueSolved() && Objects.nonNull(transaction.getIssueId()))
                    .map(Transaction::getIssueMessage)
                    .collect(Collectors.toList());
            logger.info("the list of all solved issue messages size is "+lstSolvedIssueMessages.size());
            return lstSolvedIssueMessages;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return null;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            List<Transaction> top3Transaction = lstTransaction.stream()
                    .filter(transaction -> (transaction.isIssueSolved() || Objects.isNull(transaction.getIssueId())))
                    .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            logger.info("the 3 transactions with the highest amount sorted by amount descending successfully");
            return top3Transaction;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return null;
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        try{
            List<Transaction> lstTransaction = TransactionMapper.convertJsonToPojo(FileReader.retrieveJson());
            Optional<String> topSender = lstTransaction.stream()
                    .filter(transaction -> (transaction.isIssueSolved() || Objects.isNull(transaction.getIssueId())))
                    .max(Comparator.comparingDouble(Transaction::getAmount))
                    .map(Transaction::getSenderFullName);
            logger.info("the senderFullName of the sender with the most total sent amount "+topSender.get());
            return topSender;
        }catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return Optional.empty();
    }
}
