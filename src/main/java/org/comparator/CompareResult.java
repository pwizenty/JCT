package org.comparator;

import org.utils.ResultStatus;

public class CompareResult {
    private String modelName;
    private String jolieName;
    private CompareType type;
    private ResultStatus status;
    private String message;
    private int lineNumber;

    public CompareResult() {
    }

    public CompareResult(String modelName, String jolieName, CompareType type, ResultStatus status, String message,
        int lineNumber) {
        this.modelName = modelName;
        this.jolieName = jolieName;
        this.type = type;
        this.status = status;
        this.message = message;
        this.lineNumber = lineNumber;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getJolieName() {
        return jolieName;
    }

    public void setJolieName(String jolieName) {
        this.jolieName = jolieName;
    }

    public CompareType getType() {
        return type;
    }

    public void setType(CompareType type) {
        this.type = type;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
