package hu.nye.rft.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultStudentService implements StudentServiceInterface{


    @Override
    public StudentView getStudentById(Long id) {
        return null;
    }

    @Override
    public List<StudentView> getAllStudent() {
        return null;
    }

    @Override
    public void addStudent(CreateStudentRequest request) {

    }

    @Override
    public void deleteStudentById(Long id) {

    }

    @Autowired
    public DefaultStudentService() {
    }
}
