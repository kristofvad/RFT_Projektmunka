package hu.nye.rft.Teacher;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;

/**
 * Represents a CREATE request and contains necessary data about the user.
 */
@Builder
@Data
public class CreateTeacherRequest {


    @NotBlank(message = "Name is mandatory")
    private String studentName;
    @NotBlank(message = "Email is mandatory")
    private String emailAddress;
    @NotBlank(message = "ClassName is mandatory")
    private String className;


}
