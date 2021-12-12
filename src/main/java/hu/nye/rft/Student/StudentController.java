package hu.nye.rft.Student;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * REST Controller that handles User related operations.
 */
@RestController
public class StudentController {


    private static final String GET_STUDENT_MAPPING = "/student/{id}";
    private static final String GET_ALL_STUDENT_MAPPING = "/student/students";
    private static final String ADD_STUDENT = "/student/add";
    private static final String DELETE_STUDENT_MAPPING = "/student/delete/{id}";


    private StudentServiceInterface studentService;


    @Autowired
    public StudentController(StudentServiceInterface studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = GET_STUDENT_MAPPING)
    public StudentView getStudent(@PathVariable @NotNull Long id){
        return studentService.getStudentById(id);
    }


    @GetMapping(path = GET_ALL_STUDENT_MAPPING)
    public List<StudentView> getAllStudent(){
        return studentService.getAllStudent();
    }



    @PostMapping(path = ADD_STUDENT)
    @ResponseStatus(HttpStatus.CREATED)
    public void setAddStudent(@Valid @RequestBody CreateStudentRequest request){
        studentService.addStudent(request);
    }


    @DeleteMapping(DELETE_STUDENT_MAPPING)
    public void deleteStudent(@PathVariable @NotNull Long id){
        studentService.deleteStudentById(id);
    }
}
