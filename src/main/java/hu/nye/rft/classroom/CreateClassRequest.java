package hu.nye.rft.classroom;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Builder
@Data
public class CreateClassRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private Long teacherId;
    private String classroom;


}
