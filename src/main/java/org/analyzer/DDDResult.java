package org.analyzer;

import org.utils.ResultStatus;

public class DDDResult {
    private String violationName;
    private int lineNumber;
    private String description;
    private ResultStatus status;

    public DDDResult() {
    }

    public DDDResult(String violationName, int lineNumber, String description, ResultStatus status) {
        this.violationName = violationName;
        this.lineNumber = lineNumber;
        this.description = description;
        this.status = status;
    }

    public String getViolationName() {
        return violationName;
    }

    public void setViolationName(String violationName) {
        this.violationName = violationName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}
