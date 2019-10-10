package com.github.ankurpathak.isobarwallet.exception;

import org.springframework.validation.BindingResult;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<BindingResult> bindingResults;
    public ValidationException(List<BindingResult> bindingResults) {
        this.bindingResults = bindingResults;

    }

    public List<BindingResult> getBindingResults() {
        return bindingResults;
    }




}
