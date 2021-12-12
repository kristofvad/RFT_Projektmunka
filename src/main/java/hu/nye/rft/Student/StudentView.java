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

    private Long id;
    private String studentName;
    private String emailAdress;

    @Override
    public int compareTo(StudentView k) {
        return studentName.compareTo(k.studentName);
    }


}
