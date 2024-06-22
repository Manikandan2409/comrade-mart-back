package  com.demon.comrade_mart.controller;
import com.demon.comrade_mart.dto.DispatchDTO;
import com.demon.comrade_mart.dto.LoginRequestDTO;
import com.demon.comrade_mart.dto.SignUpRequestDTO;
import com.demon.comrade_mart.service.UserService;
import com.demon.comrade_mart.utils.APIResponse;
import com.demon.comrade_mart.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {



    @Autowired
    UserService userService;




    @PostMapping("/user/signup")
    public  ResponseEntity<APIResponse> loginUser(@RequestBody SignUpRequestDTO signUpRequestDTO){
      APIResponse apiResponse = userService.signUp(signUpRequestDTO);

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }


    @GetMapping("/hello")
    public  void  helloworld(){
        System.out.println("Helo World ");
    }

    @PostMapping("/user/login")
    public ResponseEntity<APIResponse> loginUser(@RequestBody LoginRequestDTO loginDTO) {
        System.out.println("hello world");
        System.out.println("user = " + loginDTO);
        //Optional<Users> foundUser = loginService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        APIResponse apiResponse = userService.login(loginDTO.getUsername(),loginDTO.getPassword());
    return  ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/user/dispatch-location")
    public DispatchDTO getAddressById(@RequestHeader("Authorisation") String token){
        Long id = JwtUtils.extractUserIdFromToken(token);
        System.out.println("UserController.getAddressById"+id);
        DispatchDTO dto = userService.findDispatchAddressById(id);
        System.out.println(dto);
        return  dto;
    }
    @GetMapping("/users/get-allUsers")
    public List<DispatchDTO> getAllUserDetails(){
        return  userService.findAllUsers();
    }


}
