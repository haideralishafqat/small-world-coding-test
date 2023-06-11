package com.smallworld;

import com.smallworld.data.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TransactionDataFetcherTest {
    @Test
    public void testGetTotalTransactionAmount() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();
        double totalAmount = transactionDataFetcher.getTotalTransactionAmount();

        double expectedTotalAmount = 1473.97;
        Assert.assertEquals(expectedTotalAmount, totalAmount,0.0);
    }

    @Test
    public void testGetTotalTransactionAmountSentBy() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        String senderFullName = "Grace Burgess";
        double totalAmount = transactionDataFetcher.getTotalTransactionAmountSentBy(senderFullName);

        double expectedTotalAmount = 666.0;
        Assert.assertEquals(expectedTotalAmount, totalAmount, 0.0);
    }

    @Test
    public void testGetMaxTransactionAmount() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        double maxAmount = transactionDataFetcher.getMaxTransactionAmount();

        double expectedMaxAmount = 666.0;
        Assert.assertEquals(expectedMaxAmount, maxAmount, 0.0);
    }

    @Test
    public void testCountUniqueClients() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        long uniqueClients = transactionDataFetcher.countUniqueClients();

        long expectedUniqueClients = 14;
        Assert.assertEquals(expectedUniqueClients, uniqueClients);
    }

    @Test
    public void testHasOpenComplianceIssues(){
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        String clientFullName = "Michael Gray";
        boolean hasOpenComplianceIssue = transactionDataFetcher.hasOpenComplianceIssues(clientFullName);

        Assert.assertTrue(hasOpenComplianceIssue);
    }

    @Test
    public void testHasNonOpenComplianceIssues(){
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        String clientFullName = "Oswald Mosley";
        boolean hasNonOpenComplianceIssue = transactionDataFetcher.hasOpenComplianceIssues(clientFullName);

        Assert.assertFalse(hasNonOpenComplianceIssue);
    }

    @Test
    public void testGetTransactionsByBeneficiaryName() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        Map<String, Transaction> transactionsByBeneficiaryName = transactionDataFetcher.getTransactionsByBeneficiaryName();

        Assert.assertEquals(8, transactionsByBeneficiaryName.size());
        Assert.assertTrue(transactionsByBeneficiaryName.containsKey("Oswald Mosley"));
        Assert.assertFalse(transactionsByBeneficiaryName.containsKey("Haider"));
    }

    @Test
    public void testGetUnsolvedIssueIds() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        Set<Integer> unsolvedIssueIds = transactionDataFetcher.getUnsolvedIssueIds();

        Assert.assertEquals(5, unsolvedIssueIds.size());
        Assert.assertTrue(unsolvedIssueIds.contains(54));
        Assert.assertFalse(unsolvedIssueIds.contains(2));
    }

    @Test
    public void testGetAllSolvedIssueMessages() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        List<String> solvedIssueMessages = transactionDataFetcher.getAllSolvedIssueMessages();

        Assert.assertEquals(8, solvedIssueMessages.size());
        Assert.assertTrue(solvedIssueMessages.contains("Never gonna give you up"));
        Assert.assertFalse(solvedIssueMessages.contains("Something's fishy"));
    }

    @Test
    public void testGetTop3TransactionsByAmount(){
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        List<Transaction> top3Transaction = transactionDataFetcher.getTop3TransactionsByAmount();

        Assert.assertEquals(3, top3Transaction.size());
        Assert.assertEquals(666.0,top3Transaction.get(0).getAmount(),0.0);
        Assert.assertEquals(215.17,top3Transaction.get(1).getAmount(),0.0);
        Assert.assertEquals(154.15,top3Transaction.get(2).getAmount(),0.0);
    }

    @Test
    public void testGetTopSender() {
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        Optional<String> topSender = transactionDataFetcher.getTopSender();

        // Assert the expected result
        Assert.assertTrue(topSender.isPresent());
        Assert.assertEquals("Grace Burgess", topSender.get());
    }
}
