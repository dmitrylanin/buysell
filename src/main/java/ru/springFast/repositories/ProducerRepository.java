package ru.springFast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springFast.models.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
   boolean existsByName(String name);

   Producer findByName(String name);
}
