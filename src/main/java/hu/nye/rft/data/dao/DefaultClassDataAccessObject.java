package hu.nye.rft.data.dao;

import hu.nye.rft.problem.SubjectNotFoundException;
import hu.nye.rft.problem.UserNotFoundException;
import hu.nye.rft.data.domain.ClassEntryEntity;
import hu.nye.rft.data.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Collection;

public class DefaultClassDataAccessObject implements ClassDataAccessObjectInterface{

    private ClassRepository classRepository;

    @Autowired
    public DefaultClassDataAccessObject(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ClassEntryEntity getClassById(Long classId) {
        return classRepository.findById(classId).orElseThrow(() -> new SubjectNotFoundException("Subject not found with id: " + classId));
    }

    @Override
    public Collection<ClassEntryEntity> getAllClass() {
        return classRepository.findAll();
    }

    @Override
    public void addClass(ClassEntryEntity entity) {
        try{
            classRepository.save(entity);
        }catch (DataIntegrityViolationException e){

        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }

    @Override
    public void deleteClassById(Long id) {
        try{
            classRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new SubjectNotFoundException("Subject not found with id: " + id);
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }

}
