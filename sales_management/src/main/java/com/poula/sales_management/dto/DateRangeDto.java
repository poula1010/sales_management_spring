package com.poula.sales_management.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class DateRangeDto {
    private LocalDateTime from;
    private LocalDateTime to;
}
