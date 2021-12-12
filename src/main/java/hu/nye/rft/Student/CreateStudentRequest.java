package hu.nye.rft.Student;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;

/**
 * Represents a CREATE request and contains necessary data about the user.
 */
@Builder
@Data
public class CreateStudentRequest {


    private Long studentId;
    private Long classId;


}
