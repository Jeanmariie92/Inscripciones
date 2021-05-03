package com.jeanReb.appSpring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeanReb.appSpring.entity.Teacher;


@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}
