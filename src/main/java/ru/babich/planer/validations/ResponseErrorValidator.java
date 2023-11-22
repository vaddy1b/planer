package ru.babich.planer.validations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseErrorValidator {

    public ResponseEntity<Object> mapValidationServise(BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            Map<String, String> mapErrors = new HashMap<>();

            if(!CollectionUtils.isEmpty(bindingResult.getAllErrors())){
                for (ObjectError error : bindingResult.getAllErrors()){
                    mapErrors.put(error.getCode(),error.getDefaultMessage());
                }
            }

            for(FieldError fieldError : bindingResult.getFieldErrors()){
                mapErrors.put(fieldError.getCode(),fieldError.getDefaultMessage());
            }

            return new ResponseEntity<>(mapErrors,HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
