package hu.nye.rft.data.repository;

import hu.nye.rft.data.domain.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

}
