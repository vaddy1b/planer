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

    public ResponseEntity<Object> mapValidationServise(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            if (!CollectionUtils.isEmpty(bindingResult.getAllErrors())) {

                for (ObjectError errorObject : bindingResult.getAllErrors()) {
                    errorMap.put(errorObject.getCode(), errorObject.getDefaultMessage());

                }

                for (FieldError field : bindingResult.getFieldErrors()) {
                    errorMap.put(field.getCode(), field.getDefaultMessage());
                }

                return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
            }
        }

        return null;

    }

}
