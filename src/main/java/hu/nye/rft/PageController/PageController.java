package hu.nye.rft.PageController;

import hu.nye.rft.Student.StudentServiceInterface;
import hu.nye.rft.Teacher.TeacherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class PageController {

    private static final String STUDENT_PAGE_MAPPING = "/studentPage/{id}";
   // private static final String ROOT_MAPPING = "/";
    private static final String STUDENT_PAGE_NAME = "StudentPage";
    private static final String STUDENT_LIST_PAGE_NAME = "StudentListPage";
    private static final String STUDENTS_MODEL_KEY = "students";
    private static final String STUDENT_MODEL_KEY = "student";

    private StudentServiceInterface studentService;

    @Autowired
    public PageController(StudentServiceInterface userService) {
        this.studentService = studentService;
    }


//    @GetMapping(ROOT_MAPPING)
//    public String homePage(Model model) {
//        model.addAttribute(STUDENTS_MODEL_KEY, studentService.getAllStudent());
//        return STUDENT_LIST_PAGE_NAME;
//    }


    @GetMapping(STUDENT_PAGE_MAPPING)
    public String studentPage(@PathVariable Long id, Model model){
        model.addAttribute(STUDENT_MODEL_KEY, studentService.getStudentById(id));
        return STUDENT_PAGE_NAME;
    }

    private static final String TEACHER_PAGE_MAPPING = "/teacherPage/{teacherId}";
   // private static final String ROOT_MAPPING = "/";
    private static final String TEACHER_PAGE_NAME = "TeacherPage";
    private static final String TEACHER_LIST_PAGE_NAME = "TeacherListPage";
    private static final String TEACHERS_MODEL_KEY = "teachers";
    private static final String TEACHER_MODEL_KEY = "teacher";

    private TeacherServiceInterface teacherService;

    @Autowired
    public PageController(TeacherServiceInterface userService) {
        this.teacherService = teacherService;
    }


//    @GetMapping(ROOT_MAPPING)
//    public String Page(Model model) {
//        model.addAttribute(TEACHERS_MODEL_KEY, teacherService.getAllTeacher());
//        return TEACHER_LIST_PAGE_NAME;
//    }


    @GetMapping(TEACHER_PAGE_MAPPING)
    public String teacherPage(@PathVariable Long teacherId, Model model){
        model.addAttribute(TEACHER_MODEL_KEY, teacherService.getTeacherById(teacherId));
        return TEACHER_PAGE_NAME;
    }

}
