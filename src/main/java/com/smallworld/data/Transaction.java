package com.smallworld.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    @JsonProperty("mtn")
    private int mtn;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("senderFullName")
    private String senderFullName;
    @JsonProperty("senderAge")
    private int senderAge;
    @JsonProperty("beneficiaryFullName")
    private String beneficiaryFullName;
    @JsonProperty("beneficiaryAge")
    private int beneficiaryAge;
    @JsonProperty("issueId")
    private int issueId;
    @JsonProperty("issueSolved")
    private boolean issueSolved;
    @JsonProperty("issueMessage")
    private String issueMessage;

    public int getMtn() {
        return mtn;
    }

    public void setMtn(int mtn) {
        this.mtn = mtn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public int getSenderAge() {
        return senderAge;
    }

    public void setSenderAge(int senderAge) {
        this.senderAge = senderAge;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public int getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public void setBeneficiaryAge(int beneficiaryAge) {
        this.beneficiaryAge = beneficiaryAge;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public boolean isIssueSolved() {
        return issueSolved;
    }

    public void setIssueSolved(boolean issueSolved) {
        this.issueSolved = issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }
}
