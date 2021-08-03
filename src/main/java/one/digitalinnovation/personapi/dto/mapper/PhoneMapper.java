package one.digitalinnovation.personapi.dto.mapper;

import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.entities.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper((PhoneMapper.class));

    Phone toModel(PhoneDTO personDTO);

    PhoneDTO toDTO(Phone person);
}
