package one.digitalinnovation.personapi.services;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.mapper.PhoneMapper;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Phone;
import one.digitalinnovation.personapi.exception.PhoneNotFoundException;
import one.digitalinnovation.personapi.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneService {

    private final PhoneRepository phoneRepository;

    private final PhoneMapper phoneMapper;

    private MessageResponseDTO createMessageResponse(String s, Long id2){
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

    public MessageResponseDTO create(PhoneDTO phoneDTO) {

        Phone phone = phoneMapper.toModel(phoneDTO);
        Phone savedPhone = phoneRepository.save(phone);

        MessageResponseDTO messageResponse = createMessageResponse("Phone successfully created with ID", savedPhone.getId());

        return messageResponse;
    }

    public PhoneDTO fyndById(Long id) throws PhoneNotFoundException{
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));

        return phoneMapper.toDTO(phone);
    }

    public List<PhoneDTO> listAll(){
        List<Phone> phone = phoneRepository.findAll();
        return phone.stream()
                .map(phoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO update(Long id, PhoneDTO phoneDTO) throws PhoneNotFoundException{
        phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));

        Phone updatePhone = phoneMapper.toModel(phoneDTO);
        Phone savedPhone = phoneRepository.save(updatePhone);

        MessageResponseDTO messageResponse = createMessageResponse("Phone seccessfully update with ID ", savedPhone.getId());

        return messageResponse;
    }

    public void delete(Long id) throws PhoneNotFoundException {
        phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));
        phoneRepository.deleteById(id);
    }

}
