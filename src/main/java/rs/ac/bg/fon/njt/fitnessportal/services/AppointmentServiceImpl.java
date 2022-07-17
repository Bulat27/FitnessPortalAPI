package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentWithMemberGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentWithTrainingGetDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Appointment;
import rs.ac.bg.fon.njt.fitnessportal.entities.Member;
import rs.ac.bg.fon.njt.fitnessportal.entities.Training;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.*;
import rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers.AppointmentMapper;
import rs.ac.bg.fon.njt.fitnessportal.repositories.AppointmentRepository;
import rs.ac.bg.fon.njt.fitnessportal.repositories.MemberRepository;
import rs.ac.bg.fon.njt.fitnessportal.repositories.TrainingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private MemberRepository memberRepository;
    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;
    private TrainingRepository trainingRepository;


    @Override
    public List<AppointmentWithTrainingGetDto> getByMember(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new UserNotFoundException(memberEmail));

        List<Appointment> appointments = new ArrayList<>(member.getAppointments());

        return appointmentMapper.appointmentsToAppointmentWithTrainingGetDtos(appointments);
    }

    @Override
    public List<AppointmentWithMemberGetDto> getByTraining(Integer trainingID, String coachEmail) {
        Training training = trainingRepository.findById(trainingID).orElseThrow(() -> new TrainingNotFoundException(trainingID));

        if(!training.getCoach().getEmail().equals(coachEmail)) throw new InvalidUserException();

        List<Appointment> appointments = new ArrayList<>(training.getAppointments());

        return appointmentMapper.appointmentsToAppointmentWithMemberGetDtos(appointments);
    }

    @Override
    @Transactional
    public void scheduleAppointment(Integer trainingID, String userEmail) {
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));

        Training training = trainingRepository.findById(trainingID).orElseThrow(() -> new TrainingNotFoundException(trainingID));

        if(training.getRemainingSpots() == 0)
            throw new InvalidNumberOfSpotsException("There are no remaining spots on this training, they are all already taken");

        if(alreadyScheduledOnThatDate(training, member)) throw new AlreadyScheduledException();

        training.setRemainingSpots(training.getRemainingSpots() - 1);

        Appointment appointment = new Appointment(member, training);

        appointmentRepository.save(appointment);
    }



//    @Override
//    @Transactional
//    public void scheduleAppointment(Integer trainingID, String userEmail) {
//        Member member = memberRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));
//
//        Training training = trainingRepository.findById(trainingID).orElseThrow(() -> new TrainingNotFoundException(trainingID));
//
//        if(training.getRemainingSpots() == 0)
//            throw new InvalidNumberOfSpotsException("There are no remaining spots on this training, they are all already taken");
//
//        if(alreadyScheduledOnThatDate(training, member)) throw new AlreadyScheduledException();
//
//        training.setRemainingSpots(training.getRemainingSpots() - 1);
//
//        member.addTraining(training);
//    }


    private boolean alreadyScheduledOnThatDate(Training training, Member member) {
//        for (Training t: member.getTrainings()) {
//            if(t.getDate().equals(training.getDate())) return true;
//        }

        for(Appointment appointment: member.getAppointments()){
            if(appointment.getTraining().getDate().equals(training.getDate())) return true;
        }
        return false;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Autowired
    public void setAppointmentMapper(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }
}

