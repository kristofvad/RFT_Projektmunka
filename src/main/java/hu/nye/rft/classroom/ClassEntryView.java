package hu.nye.rft.classroom;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClassEntryView {

    private Long id;
    private String name;
    private Long teacherId;
    private String classroom;

}
