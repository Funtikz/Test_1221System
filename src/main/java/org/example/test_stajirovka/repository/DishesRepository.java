package org.example.test_stajirovka.repository;

import org.example.test_stajirovka.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long> {
}
