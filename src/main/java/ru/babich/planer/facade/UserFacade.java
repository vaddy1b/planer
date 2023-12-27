package ru.babich.planer.facade;

import org.springframework.stereotype.Component;
import ru.babich.planer.enity.User;
import ru.babich.planer.enity.dto.UserDTO;

@Component
public class UserFacade {


    public UserDTO userToDto (User user){

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setRole(user.getRole());
        userDTO.setWorkingPlaceList(user.getWorkingPlaces());

        return userDTO;
    }

}
