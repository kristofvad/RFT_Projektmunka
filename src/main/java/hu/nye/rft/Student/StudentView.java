package hu.nye.rft.Student;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * Web layer representation of a user.
 */
@Data
@Builder

public class StudentView implements Comparable<StudentView>{

    private Long studentId;
    private String studentName;
    private String emailAdress;
    private Long classId;

    @Override
    public int compareTo(StudentView k) {
        return studentName.compareTo(k.studentName);
    }


}
