package com.edutech.edutech_casosemestral.repository;

import com.edutech.edutech_casosemestral.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByRut(String rut);
}
