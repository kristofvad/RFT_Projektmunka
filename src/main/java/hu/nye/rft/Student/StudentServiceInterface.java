package hu.nye.rft.Student;

import java.util.List;

public interface StudentServiceInterface{


    StudentView getStudentById(Long id);

    List<StudentView> getAllStudent();

    void addStudent(CreateStudentRequest request);

    void deleteStudentById(Long id);
}