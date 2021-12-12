package hu.nye.rft.Teacher;

import hu.nye.rft.Student.CreateStudentRequest;
import hu.nye.rft.Student.StudentView;

import java.util.List;

public interface TeacherServiceInterface{


    TeacherView getTeacherById(Long teacherId);

    List<TeacherView> getAllTeacher();

    void addTeacher(CreateTeacherRequest request);

    void deleteTeacherById(Long teacherId);
}
