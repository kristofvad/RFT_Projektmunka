package hu.nye.rft.data.dao;

import hu.nye.rft.data.repository.StudentRepository;
import hu.nye.rft.data.domain.StudentEntity;
import hu.nye.rft.data.repository.StudentRepository;
import hu.nye.rft.problem.EmailAlreadyInUseException;
import hu.nye.rft.problem.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultStudentDataAccessObject implements StudentDataAccessObjectInterface{

    private StudentRepository studentRepository;

    @Autowired
    public DefaultStudentDataAccessObject(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + studentId));
    }

       @Override
        public Collection<StudentEntity> getAllStudent() {
            return studentRepository.findAll();
       }

       @Override
        public void addStudent(StudentEntity entity) {
            try{
                studentRepository.save(entity);
            }catch (DataIntegrityViolationException e){
                throw new EmailAlreadyInUseException("Email adress: " + entity.getEmailAdress() + "already in use");
            }catch (Exception e) {
                throw new RuntimeException("OOpps, someting went wrong.");
            }
       }

       @Override
        public void deleteStudentById(Long studentId) {
            try{
                studentRepository.deleteById(studentId);
            }catch (EmptyResultDataAccessException e){
                throw new UserNotFoundException("User not found with id: " + studentId);
            }catch (Exception e){
                throw new RuntimeException("OOpps, something went wrong.");
            }
       }

}
