package hu.nye.rft.Teacher;


import hu.nye.rft.Student.CreateStudentRequest;
import hu.nye.rft.Student.StudentServiceInterface;
import hu.nye.rft.Student.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeacherService implements TeacherServiceInterface {


    @Override
    public TeacherView getTeacherById(Long teacherId) {
        return null;
    }

    @Override
    public List<TeacherView> getAllTeacher() {
        return null;
    }

    @Override
    public void addTeacher(CreateTeacherRequest request) {

    }

    @Override
    public void deleteTeacherById(Long teacherId) {

    }

    @Autowired
    public DefaultTeacherService() {
    }
}
