package ru.babich.planer.facade;

import org.springframework.stereotype.Component;
import ru.babich.planer.enity.WorkingPlace;
import ru.babich.planer.enity.dto.WorkingPlaceDTO;

@Component
public class WorkingPlaceFacade {


    public WorkingPlaceDTO workingPlaceToDTO (WorkingPlace workingPlace)
    {
        WorkingPlaceDTO workingPlaceDTO = new WorkingPlaceDTO();

        workingPlaceDTO.setId(workingPlace.getId());
        workingPlaceDTO.setWorkingPlaceName(workingPlace.getWorkingPlaceName());
        workingPlaceDTO.setTaskList(workingPlace.getTaskList());

        return workingPlaceDTO;
    }
}
