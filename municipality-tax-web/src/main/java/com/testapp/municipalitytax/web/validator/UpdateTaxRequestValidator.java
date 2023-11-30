package com.testapp.municipalitytax.web.validator;

import com.testapp.municipalitytax.web.TaxesService;
import com.testapp.municipalitytax.web.payload.AddTaxRequest;
import com.testapp.municipalitytax.web.payload.UpdateTaxRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UpdateTaxRequestValidator implements Validator {

    private final TaxesService taxesService;

    public UpdateTaxRequestValidator(TaxesService taxesService) {
        this.taxesService = taxesService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return supports(UpdateTaxRequestValidator.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateTaxRequest updateTaxRequest = (UpdateTaxRequest) target;
        String pattern = "\\d{4}\\.\\d{2}\\.\\d{2}";
        Pattern datePattern = Pattern.compile(pattern);
        Matcher matcher = datePattern.matcher(updateTaxRequest.startDate());
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid date format");
        }
        if (!set.contains(updateTaxRequest.schedule())) {
            throw new RuntimeException("Invaild schedule format");
        }
    }

    private HashSet<String> set = new HashSet<>();

    @PostConstruct
    public void init(){
        set.add("yearly");
        set.add("monthly"); //простите, времени не было сдлеать нормально
        set.add("weekly");
        set.add("daily");
    }
}
