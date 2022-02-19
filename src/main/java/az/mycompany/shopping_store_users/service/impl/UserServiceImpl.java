package az.mycompany.shopping_store_users.service.impl;

import az.mycompany.shopping_store_users.dto.UserDto;
import az.mycompany.shopping_store_users.dto.enums.Status;
import az.mycompany.shopping_store_users.entity.UserEntity;
import az.mycompany.shopping_store_users.exception.EmailAlreadyExistsException;
import az.mycompany.shopping_store_users.exception.UserNameAlreadyExistsException;
import az.mycompany.shopping_store_users.exception.UserNotFoundException;
import az.mycompany.shopping_store_users.repository.UserRepository;
import az.mycompany.shopping_store_users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @PostConstruct
    public void initUser(){
        UserEntity entity = UserEntity.builder()
                .email("Baxtiyar@gmail.com")
                .name("Baxtiyar")
                .enable(true)
                .password("Baxtiyar")
                .status(Status.USER)
                .username("Baxtiyar")
                .surname("Mammadyarov")
                .build();
       repository.save(entity);
         entity = UserEntity.builder()
                .email("Baxtiyar1@gmail.com")
                .name("Baxtiyar11")
                .enable(true)
                .password("Baxtiyar11")
                .status(Status.USER)
                .username("Baxtiyar1")
                .surname("Mammadyarov1")
                .build();
        repository.save(entity);

    }

    @Override
    public UserDto add(UserDto dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new UserNameAlreadyExistsException("username already exist");
        } else if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("email already exist");
        }
        UserEntity entity = UserEntity.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .enable(true)
                .password(dto.getPassword())
                .status(Status.USER)
                .username(dto.getUsername())
                .surname(dto.getSurname())
                .build();
        entity = repository.save(entity);
        return entityToDto(entity);
    }



    @Override
    public void delete(UserDto dto) {

    }

    @Override
    public List<UserDto> get() {
        return repository.findAllByEnable(true).stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getByUsername(String username) {
        UserEntity entity =repository
                .findByUsername(username).orElseThrow(() -> new UserNotFoundException("user not found"));
        return entityToDto(entity);
    }

    @Override
    public UserDto getByEmail(String email) {
        UserEntity userEntity=repository.findByEmail(email);
        return entityToDto(userEntity);
    }

    private UserDto entityToDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .enable(entity.getEnable())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .username(entity.getUsername())
                .surname(entity.getSurname())
                .build();
    }
}
