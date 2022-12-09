package pl.project.computer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComputerRepo extends JpaRepository<Computer, Integer> {

    List<Computer> findByNameContainingIgnoreCase(String name);

    List<Computer> findByDate(LocalDate date);

}
