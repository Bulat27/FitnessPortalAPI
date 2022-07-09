package rs.ac.bg.fon.njt.fitnessportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.fitnessportal.entities.Training;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    List<Training> findAllByDate(LocalDate date);
}
