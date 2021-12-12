package hu.nye.rft.transformer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.rft.Teacher.CreateTeacherRequest;
import hu.nye.rft.Teacher.TeacherView;
import hu.nye.rft.data.domain.TeacherEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherTransformer {

    @Autowired
    public TeacherTransformer() {
    }

    public List<TeacherView> transform(Collection<TeacherEntity> collection) {
        List<TeacherView> result = null;
        if (collection != null) {
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    public TeacherView transform(TeacherEntity entity) {
        TeacherView result = null;
        if (entity != null) {
            result = TeacherView.builder()
                    .teacherId(entity.getTeacherId())
                    .teacherName(entity.getTeacherName())
                    .build();
        }
        return result;
    }

    public TeacherEntity transform(CreateTeacherRequest request) {
        TeacherEntity result = null;
        if (request != null) {
            result = new TeacherEntity();
            result.setEmailAddress(request.getEmailAddress());
            result.setTeacherName(request.getTeacherName());
        }
        return result;
    }
}
