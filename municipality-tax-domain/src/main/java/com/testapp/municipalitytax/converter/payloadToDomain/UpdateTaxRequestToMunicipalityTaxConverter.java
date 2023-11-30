package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.web.payload.UpdateTaxRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UpdateTaxRequestToMunicipalityTaxConverter
    implements Converter<UpdateTaxRequest, MunicipalityTax> {

  @Override
  public MunicipalityTax convert(UpdateTaxRequest source) {
    return new MunicipalityTax(
            null,
            null,
            source.tax(),
            LocalDate.parse(source.startDate(), DateTimeFormatter.ofPattern("yyyy.MM.dd")),
            Schedule.valueOf(toUpperCase(source.schedule()))
    );
  }

  private final String toUpperCase(String source){ //TODO вынести в отдельный класс
    StringBuilder res = new StringBuilder();
    for (int i =0; i < source.length(); ++i){
      res.append(Character.toUpperCase(source.charAt(i)));
    }
    return res.toString();
  }
}
