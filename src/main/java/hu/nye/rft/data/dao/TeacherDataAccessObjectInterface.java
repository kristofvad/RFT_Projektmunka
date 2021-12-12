package hu.nye.rft.data.dao;

import hu.nye.rft.data.domain.StudentEntity;
import hu.nye.rft.data.domain.TeacherEntity;

import java.util.Collection;

public interface TeacherDataAccessObjectInterface {


    TeacherEntity getTeacherById(Long teacherId);

    Collection<TeacherEntity> getAllTeacher();

    void addTeacher(TeacherEntity entity);

    void deleteTeacherById(Long teacherId);

}
