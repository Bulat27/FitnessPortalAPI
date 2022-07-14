package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachPostDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Coach;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.EmailExistsException;
import rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers.CoachMapper;
import rs.ac.bg.fon.njt.fitnessportal.repositories.CoachRepository;
import rs.ac.bg.fon.njt.fitnessportal.repositories.UserRepository;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.services.utility.UserConfigurer;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    private CoachRepository coachRepository;
    private CoachMapper coachMapper;
    private UserRepository userRepository;
    private UserConfigurer userConfigurer;

    @Override
    public List<CoachGetDto> get() {
        return coachMapper.coachesToCoachGetDtos(coachRepository.findAll());
    }

    @Override
    @Transactional
    public CoachGetDto create(CoachPostDto coachPostDto, List<ApplicationUserRole> roleTypes) {
        if(userRepository.existsByEmail(coachPostDto.getEmail())) throw new EmailExistsException(coachPostDto.getEmail());

        Coach coach = coachMapper.coachPostDtoToCoach(coachPostDto);
        userConfigurer.addRoles(coach, roleTypes);
        userConfigurer.encodePassword(coach);
        coach.setEnabled(true);

        coachRepository.save(coach);
        return coachMapper.coachToCoachGetDto(coach);
    }

    @Autowired
    public void setCoachRepository(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
    @Autowired
    public void setCoachMapper(CoachMapper coachMapper) {
        this.coachMapper = coachMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserConfigurer(UserConfigurer userConfigurer) {
        this.userConfigurer = userConfigurer;
    }
}
