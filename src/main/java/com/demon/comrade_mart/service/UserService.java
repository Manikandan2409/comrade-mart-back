package  com.demon.comrade_mart.service;

import com.demon.comrade_mart.dto.DispatchDTO;
import com.demon.comrade_mart.dto.SignUpRequestDTO;
import com.demon.comrade_mart.entity.Users;
import com.demon.comrade_mart.repository.UserRepository;
import com.demon.comrade_mart.utils.APIResponse;
import com.demon.comrade_mart.utils.Constant;
import com.demon.comrade_mart.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = new APIResponse();
    /// Validate

    /// Entity remaining value

    /// dto -> entity

        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        System.out.println("Current datetime :"+ currentTimestamp);
        Users entity = new Users();
        entity.setUsername(signUpRequestDTO.getUsername());
        entity.setEmail(signUpRequestDTO.getEmail());
        entity.setLoginCount(1);
        entity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        entity.setLoginAt(currentTimestamp);
        entity.setPassword(signUpRequestDTO.getPassword());
        entity.setUsertype(Constant.USER_TYPE.NORMAL);

        // store entity
        userRepo.save(entity);
        apiResponse.setData("Login Successful"+ currentTimestamp);

        return  apiResponse;
    }


    public APIResponse login(String username, String password) {

        APIResponse res = new APIResponse();

        //validation

        //verify user with given username and pass

        Users user  =userRepo.findByUsernameAndPassword(username,password);
        //res
        if (user == null){
            res.setData("login failed");
        }else {
            user.setLoginCount(user.getLoginCount()+1);
            user.setLoginAt(Timestamp.from(Instant.now()));
            userRepo.save(user);
            System.out.println("SignupService.login"+user);
            String token = JwtUtils.generateJwt(user);
           Map<String , Object> data = new HashMap<>();
            data.put("accessToken", token);
           // data.put("user",user);
        res.setData(data);
        }

        return  res;

    }

    public DispatchDTO findDispatchAddressById(Long id){
        DispatchDTO dto = new DispatchDTO();
       Optional<Users> user = userRepo.findById(id);
       if (user.isPresent()){

          dto= user.get().getDispatchAddress();
       }
       return  dto;
    }
    public List<DispatchDTO> findAllUsers(){
        List<Users> tableUsers = userRepo.findByUsertype(Constant.USER_TYPE.NORMAL);
        List<DispatchDTO> dispatchusers=null;
       // if (tableUsers.isPresent())
            dispatchusers= tableUsers.stream()
                    .map(Users::getDispatchAddress)
                    .collect(Collectors.toList());

        return  dispatchusers;
    }




}
