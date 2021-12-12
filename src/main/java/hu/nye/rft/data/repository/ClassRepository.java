package hu.nye.rft.data.repository;

import hu.nye.rft.data.domain.ClassEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntryEntity, Long> {
}
