package hu.nye.rft.data.dao;


import hu.nye.rft.data.domain.StudentEntity;
import hu.nye.rft.data.domain.TeacherEntity;

import java.util.Collection;


public interface StudentDataAccessObjectInterface {

    StudentEntity getStudentById(Long studentId);


    Collection<StudentEntity> getAllStudent();


    void addStudent(StudentEntity entity);


    void deleteStudentById(Long studentId);
}
