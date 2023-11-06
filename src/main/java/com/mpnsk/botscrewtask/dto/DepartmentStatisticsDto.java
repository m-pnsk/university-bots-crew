package com.mpnsk.botscrewtask.dto;

import com.mpnsk.botscrewtask.model.Lector;
import lombok.Data;

@Data
public class DepartmentStatisticsDto {
    Lector.Degree degree;
    int count;

    public DepartmentStatisticsDto() {
    }

    public DepartmentStatisticsDto(Lector.Degree degree, int count) {
        this.degree = degree;
        this.count = count;
    }

    @Override
    public String toString() {
        return "\n" + degree + ": " + count;
    }
}
