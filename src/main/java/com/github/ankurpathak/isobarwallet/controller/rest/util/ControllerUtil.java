package com.github.ankurpathak.isobarwallet.controller.rest.util;

import com.github.ankurpathak.isobarwallet.exception.ValidationException;
import org.springframework.validation.BindingResult;

import java.util.List;

public class ControllerUtil {

    public static void processValidation(BindingResult result){
        if(result.hasErrors())
            throw new ValidationException(List.of(result));
    }
}
