package hu.nye.rft.Teacher;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * Web layer representation of a user.
 */
@Data
@Builder

public class TeacherView implements Comparable<TeacherView>{

    private Long teacherId;
    private String teacherName;
    private String emailAdress;
//    private Long classId;
//    private String className;

    @Override
    public int compareTo(TeacherView k) {
        return teacherName.compareTo(k.teacherName);
    }


}