package ru.babich.planer.controller;

import jakarta.validation.Valid;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.babich.planer.enity.User;
import ru.babich.planer.enity.WorkingPlace;
import ru.babich.planer.enity.dto.UserDTO;
import ru.babich.planer.enity.dto.WorkingPlaceDTO;
import ru.babich.planer.exception.NotFoundWorkingPlacesOfUser;
import ru.babich.planer.facade.UserFacade;
import ru.babich.planer.facade.WorkingPlaceFacade;
import ru.babich.planer.service.TaskService;
import ru.babich.planer.service.UserService;
import ru.babich.planer.service.WorkingPlaceServise;
import ru.babich.planer.validations.ResponseErrorValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("${PERSONAL_PAGE_PATH}")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private WorkingPlaceServise workingPlaceServise;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal){

        User user = userService.getUser(principal);

        UserDTO userDTO = userFacade.userToDto(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/user-{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String id){

        User user = userService.getCurrentUserById(Long.parseLong(id));

        UserDTO userDTO = userFacade.userToDto(user);

        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @PostMapping("/updt-{userId}")
    public ResponseEntity<Object> updateUser(@Valid @PathVariable("userId") @RequestBody UserDTO userDto,
                                                                  BindingResult bindingResult,
                                                                  Principal principal){

        ResponseEntity <Object> errors = responseErrorValidator.mapValidationServise(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.updateUser(userDto,principal);

        UserDTO updatedUser = userFacade.userToDto(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }

    @GetMapping("/workplaces")
    public ResponseEntity<List<WorkingPlaceDTO>> getAllWorkingPlacesOfUser(@PathVariable("userId") Long id)
    {
        try {
            List<WorkingPlace> workingPlaces = workingPlaceServise.getWorkingPlacesByUser(id);
            WorkingPlaceFacade wpFacade = new WorkingPlaceFacade();
            List<WorkingPlaceDTO> listOfWorkingPlaces = new ArrayList<>();

            if (workingPlaces.isEmpty()) {
                throw new NotFoundWorkingPlacesOfUser("We cant find any information about your assigned working places," +
                        "direct to your superior");
            }
            //добавление в список дто объектов раб мест
            for (WorkingPlace i : workingPlaces) {
                listOfWorkingPlaces.add(wpFacade.workingPlaceToDTO(i));
            }

            return new ResponseEntity<>(listOfWorkingPlaces,HttpStatus.OK);

        } catch (NotFoundWorkingPlacesOfUser e) {
            throw new RuntimeException(e);
        }
    }


}
