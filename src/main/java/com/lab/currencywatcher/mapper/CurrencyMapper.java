package com.lab.currencywatcher.mapper;

import com.lab.currencywatcher.dto.CurrencyDto;
import com.lab.currencywatcher.model.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {

    CurrencyDto toDto(Currency currency);

    Currency toEntity(CurrencyDto dto);
}
