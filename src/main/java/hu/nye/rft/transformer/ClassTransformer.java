package hu.nye.rft.transformer;

import hu.nye.rft.classroom.ClassEntryView;
import hu.nye.rft.classroom.CreateClassRequest;
import hu.nye.rft.data.domain.ClassEntryEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClassTransformer {

    public List<ClassEntryView> transformToView(Collection<ClassEntryEntity> entries){
        List<ClassEntryView> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToView)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<ClassEntryEntity> transformToEntity(Collection<ClassEntryView> entries){
        List<ClassEntryEntity> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToEntity)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public ClassEntryView transformToView(ClassEntryEntity entity){
        ClassEntryView result = null;
        if(entity != null){
            result = ClassEntryView.builder()
                    .name(entity.getName())
                    .teacherId(entity.getTeacherId())
                    .classroom(entity.getClassroom())
                    .build();
        }
        return result;
    }

    public ClassEntryEntity transformToEntity(ClassEntryView view){
        ClassEntryEntity result = null;
        if(view != null){
            result = new ClassEntryEntity();
            result.setName(view.getName());
            result.setTeacherId(view.getTeacherId());
            result.setClassroom(view.getClassroom());
        }
        return result;
    }

    public ClassEntryEntity transform(CreateClassRequest request){
        ClassEntryEntity result = null;
        if(request != null){
            result = new ClassEntryEntity();
            result.setName(request.getName());
            result.setTeacherId(request.getTeacherId());
            result.setClassroom(request.getClassroom());
        }
        return result;
    }


}
