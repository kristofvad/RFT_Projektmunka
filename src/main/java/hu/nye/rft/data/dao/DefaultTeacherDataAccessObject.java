package hu.nye.rft.data.dao;

import hu.nye.rft.problem.EmailAlreadyInUseException;
import hu.nye.rft.data.domain.TeacherEntity;
import hu.nye.rft.data.repository.TeacherRepository;

import hu.nye.rft.problem.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultTeacherDataAccessObject implements TeacherDataAccessObjectInterface{

    private TeacherRepository teacherRepository;

    @Autowired
    public DefaultTeacherDataAccessObject(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherEntity getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + teacherId));
    }

    @Override
    public Collection<TeacherEntity> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public void addTeacher(TeacherEntity entity) {
        try{
            teacherRepository.save(entity);
        }catch (DataIntegrityViolationException e){
            throw new EmailAlreadyInUseException("Email address: "+ entity.getEmailAddress() + " already in use.");
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }

    @Override
    public void deleteTeacherById(Long teacherId) {
        try{
            teacherRepository.deleteById(teacherId);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User not found with id: " + teacherId);
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }

}
