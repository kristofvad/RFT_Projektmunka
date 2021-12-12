package hu.nye.rft.UserServiceTest;

import hu.nye.rft.Student.DefaultStudentService;
import hu.nye.rft.Teacher.CreateTeacherRequest;
import hu.nye.rft.Teacher.DefaultTeacherService;
import hu.nye.rft.Teacher.TeacherView;

import hu.nye.rft.data.dao.TeacherDataAccessObjectInterface;
import hu.nye.rft.data.domain.ClassEntryEntity;
import hu.nye.rft.data.domain.TeacherEntity;

import hu.nye.rft.problem.UserNotFoundException;

import hu.nye.rft.transformer.TeacherTransformer;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

/**
 * Test call of {@link DefaultStudentService}.
 */
public class TeacherServiceTest {


    private static final Long ENTRY_ID = 98L;

    private static final Long USER_ID = 458L;
    private static final Long OTHER_USER_ID = 8596L;

    private static final String USER_NAME = "Test User A";
    private static final String OTHER_USER_NAME = "Test User B";

    private static final String EMAIL_ADDRESS = "test_user@mail.com";

    @Mock
    private TeacherDataAccessObjectInterface teacherDataAccessObject;
    @Mock
    private TeacherTransformer transformer;

    @InjectMocks
    private DefaultTeacherService underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        teacherDataAccessObject = null;
        transformer = null;
        underTest = null;
    }

    @Test
    public void testGetUserByIdShouldTransformAndReturnUserWhenCalledWithValidId() {
        //given
        TeacherEntity entity = createTeacherEntity(USER_ID, USER_NAME);
        TeacherView expected = createTeacherView(USER_ID, USER_NAME);
        given(teacherDataAccessObject.getTeacherById(USER_ID)).willReturn(entity);
        given(transformer.transform(entity)).willReturn(expected);

        //when
        TeacherView actual = underTest.getTeacherById(USER_ID);

        //then
        assertEquals(actual, expected);
        then(teacherDataAccessObject).should().getTeacherById(USER_ID);
        then(transformer).should().transform(entity);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserByIdShouldThrowUserNotFoundExceptionWhenCalledWithInvalidId() {
        //given
        TeacherEntity entity = createTeacherEntity(USER_ID, USER_NAME);
        given(teacherDataAccessObject.getTeacherById(USER_ID)).willReturn(entity);
        given(transformer.transform(entity)).willThrow(UserNotFoundException.class);

        //when
        underTest.getTeacherById(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnAListOfUser() {
        //given
        List<TeacherView> unsortedUserList = List.of(
                createTeacherView(USER_ID, USER_NAME),
                createTeacherView(OTHER_USER_ID, OTHER_USER_NAME)
        );
        List<TeacherEntity> userEntities = List.of(
                createTeacherEntity(USER_ID, USER_NAME),
                createTeacherEntity(OTHER_USER_ID, OTHER_USER_NAME)
        );
        given(teacherDataAccessObject.getAllTeacher()).willReturn(userEntities);
        given(transformer.transform(userEntities)).willReturn(unsortedUserList);
        List<TeacherView> expected = List.of(
                createTeacherView(USER_ID, USER_NAME),
                createTeacherView(OTHER_USER_ID, OTHER_USER_NAME)
        );

        //when
        List<TeacherView> actual = underTest.getAllTeacher();

        //then
        assertEquals(actual, expected);
        then(teacherDataAccessObject).should().getAllTeacher();
        then(transformer).should().transform(userEntities);
    }

    @Test
    public void testAddUserShouldDelegateToTransformerAndUserAccessObject() {
        //given
        CreateTeacherRequest request = createCreateTeacherRequest(USER_NAME, EMAIL_ADDRESS);
        TeacherEntity userEntity = createTeacherEntity(USER_ID, USER_NAME);
        given(transformer.transform(request)).willReturn(userEntity);

        //when
        underTest.addTeacher(request);

        //then
        then(teacherDataAccessObject).should().addTeacher(userEntity);
    }

    private TeacherEntity createTeacherEntity(Long teacherId, String teachername) {
        TeacherEntity user = new TeacherEntity();
        user.setTeacherId(teacherId);
        user.setTeacherName(teachername);
        return user;
    }


    private ClassEntryEntity createClassEntryEntity() {
        ClassEntryEntity entry = new ClassEntryEntity();
        entry.setId(ENTRY_ID);
        return entry;
    }

    private CreateTeacherRequest createCreateTeacherRequest(String teacherName, String emailAddress){
        return CreateTeacherRequest.builder()
                .teacherName(teacherName)
                .emailAddress(emailAddress)
                .build();
    }

    private TeacherView createTeacherView(Long userId, String username) {
        return TeacherView.builder()
                .teacherId(userId)
                .teacherName(username)
                .build();
    }

}

