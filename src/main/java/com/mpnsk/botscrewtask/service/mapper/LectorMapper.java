package com.mpnsk.botscrewtask.service.mapper;

import com.mpnsk.botscrewtask.dto.LectorDto;
import com.mpnsk.botscrewtask.model.Lector;
import com.mpnsk.botscrewtask.service.LectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LectorMapper {
    private final LectorService lectorService;
    public LectorDto objectToDto(Lector lector) {
        LectorDto lectorDto = new LectorDto();
        lectorDto.setFullName(String.format("%s %s", lector.getName(), lector.getSurName()));
        lectorDto.setDegree(lector.getDegree());
        lectorDto.setSalary(lectorService.getLectorSalary(lector));
        lectorDto.setDepartments(lectorService.getDepartments(lector));
        return lectorDto;
    }
}
