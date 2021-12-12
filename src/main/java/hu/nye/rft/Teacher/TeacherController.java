package hu.nye.rft.Teacher;

import hu.nye.rft.Student.CreateStudentRequest;
import hu.nye.rft.Student.StudentServiceInterface;
import hu.nye.rft.Student.StudentView;
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
public class TeacherController {


    private static final String GET_TEACHER_MAPPING = "/teacher/{teacherId}";
    private static final String GET_ALL_TEACHER_MAPPING = "/teacher/teachers";
    private static final String ADD_TEACHER = "/teacher/add";
    private static final String DELETE_TEACHER_MAPPING = "/teacher/delete/{teacherId}";


    private TeacherServiceInterface teacherService;


    @Autowired
    public TeacherController(TeacherServiceInterface teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping(path = GET_TEACHER_MAPPING)
    public TeacherView getTeacher(@PathVariable @NotNull Long teacherId){
        return teacherService.getTeacherById(teacherId);
    }


    @GetMapping(path = GET_ALL_TEACHER_MAPPING)
    public List<TeacherView> getAllTeacher(){
        return teacherService.getAllTeacher();
    }



    @PostMapping(path = ADD_TEACHER)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@Valid @RequestBody CreateTeacherRequest request){
        teacherService.addTeacher(request);
    }


    @DeleteMapping(DELETE_TEACHER_MAPPING)
    public void deleteTeacher(@PathVariable @NotNull Long teacherId){
        teacherService.deleteTeacherById(teacherId);
    }
}
