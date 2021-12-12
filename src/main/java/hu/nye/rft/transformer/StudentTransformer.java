package hu.nye.rft.transformer;


import hu.nye.rft.Student.CreateStudentRequest;
import hu.nye.rft.Student.StudentView;
import hu.nye.rft.data.domain.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentTransformer {

    public List<StudentView> transformToView(Collection<StudentEntity> entries){
        List<StudentView> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToView)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<StudentEntity> transformToEntity(Collection<StudentView> entries){
        List<StudentEntity> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToEntity)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public StudentView transformToView(StudentEntity entity){
        StudentView result = null;
        if(entity != null){
            result = StudentView.builder()
                    .studentId(entity.getStudentId())
                    .classId(entity.getClassId())
                    .build();
        }
        return result;
    }

    public StudentEntity transformToEntity(StudentView view){
        StudentEntity result = null;
        if(view != null){
            result = new StudentEntity();
            result.setStudentId(view.getStudentId());
            result.setClassId(view.getClassId());
        }
        return result;
    }

    public StudentEntity transform(CreateStudentRequest request){
        StudentEntity result = null;
        if(request != null){
            result = new StudentEntity();
            result.setStudentId(request.getStudentId());
            result.setClassId(request.getClassId());
        }
        return result;
    }
}
