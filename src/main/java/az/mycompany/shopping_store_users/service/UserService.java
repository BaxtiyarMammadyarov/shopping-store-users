package az.mycompany.shopping_store_users.service;

import az.mycompany.shopping_store_users.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    
    UserDto add(UserDto dto);
    
    void delete(UserDto dto);

    List<UserDto> get();

    UserDto getByUsername(String username);
}
