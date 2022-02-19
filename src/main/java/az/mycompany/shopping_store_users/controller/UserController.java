package az.mycompany.shopping_store_users.controller;

import az.mycompany.shopping_store_users.dto.UserDto;
import az.mycompany.shopping_store_users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> get() {
        return ResponseEntity.ok(service.get());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto>getByUsername(@PathVariable("username") String username ){
          log.info("getByUsername method started  "+username);
        return ResponseEntity.ok(service.getByUsername(username));
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDto>getByEmail(@PathVariable("email") String email ){
       return ResponseEntity.ok(service.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto>add(@RequestBody UserDto dto){
        return ResponseEntity.ok(service.add(dto));
    }

}
