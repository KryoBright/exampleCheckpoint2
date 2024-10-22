package com.hits.backend2.backend.domain.mappers;

import com.hits.backend2.crutch.model.EmployeeInfoResponse;
import com.hits.backend2.integrations.model.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeDtoMapper {
    EmployeeDtoMapper INSTANCE = Mappers.getMapper(EmployeeDtoMapper.class);

    EmployeeInfoResponse toEmployeeInfoResponse(EmployeeDto employeeDto);
}
