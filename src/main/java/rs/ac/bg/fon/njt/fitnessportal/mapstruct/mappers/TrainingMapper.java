package rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingPostDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Training;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TrainingMapper {
    TrainingGetDto trainingToTrainingGetDto(Training training);
    Training trainingPostDtoToTraining(TrainingPostDto trainingPostDto);
    List<TrainingGetDto> trainingsToTrainingGetDtos(List<Training> trainings);
}
