package hu.nye.rft.data.dao;

import hu.nye.rft.data.domain.ClassEntryEntity;

import java.util.Collection;

public interface ClassDataAccessObjectInterface {

    ClassEntryEntity getClassById(Long classId);

    Collection<ClassEntryEntity> getAllClass();

    void addClass(ClassEntryEntity entity);

    void deleteClassById(Long classId);

}
