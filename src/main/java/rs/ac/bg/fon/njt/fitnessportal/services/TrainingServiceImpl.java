package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingPostDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Coach;
import rs.ac.bg.fon.njt.fitnessportal.entities.Training;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.InvalidTrainingTimeException;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.UserNotFoundException;
import rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers.TrainingMapper;
import rs.ac.bg.fon.njt.fitnessportal.repositories.CoachRepository;
import rs.ac.bg.fon.njt.fitnessportal.repositories.TrainingRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private CoachRepository coachRepository;
    private TrainingRepository trainingRepository;
    private TrainingMapper trainingMapper;

    @Override
    @Transactional
    public TrainingGetDto create(TrainingPostDto trainingPostDto, String userEmail) {
        validateTrainingDateTime(trainingPostDto);

        Training training = trainingMapper.trainingPostDtoToTraining(trainingPostDto);
        training.setRemainingSpots(trainingPostDto.getMaxSpots());

        Coach coach = coachRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));
        training.setCoach(coach);

        return trainingMapper.trainingToTrainingGetDto(trainingRepository.save(training));
    }

    private void validateTrainingDateTime(TrainingPostDto trainingPostDto){
        String message = "";

        if(trainingPostDto.getEndTime().isBefore(trainingPostDto.getStartTime()))
            message += "Start time must be before the end time\n";

        if(trainingPostDto.getDate().isBefore(LocalDate.now()))
            message += "Training date must be after the current date\n";

        if(isTakenInterval(trainingPostDto))
            message += "The training in this interval is already scheduled";

        if(!message.isEmpty()) throw new InvalidTrainingTimeException(message);
    }

    private boolean isTakenInterval(TrainingPostDto trainingPostDto){
        List<Training> trainings = trainingRepository.findAllByDate(trainingPostDto.getDate());

        for (Training t: trainings) {
            if(isInInterval(t.getStartTime(), t.getEndTime(), trainingPostDto.getStartTime())
                    || isInInterval(t.getStartTime(), t.getEndTime(), trainingPostDto.getEndTime()))
                return true;
        }
        return false;
    }

    private boolean isInInterval(LocalTime lowerLimit, LocalTime upperLimit, LocalTime time){
        return time.isAfter(lowerLimit) && time.isBefore(upperLimit);
    }

    @Autowired
    public void setCoachRepository(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Autowired
    public void setTrainingMapper(TrainingMapper trainingMapper) {
        this.trainingMapper = trainingMapper;
    }
}
