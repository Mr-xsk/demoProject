package com.xsk.test.util;

public class Result {

    private String caseNo;

    private Integer claimId;

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    public Result() {
    }

    public Result(String caseNo, Integer claimId) {
        this.caseNo = caseNo;
        this.claimId = claimId;
    }
}
