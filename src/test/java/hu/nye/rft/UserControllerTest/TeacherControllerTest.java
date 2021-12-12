package hu.nye.rft.UserControllerTest;

import hu.nye.rft.problem.EmailAlreadyInUseException;
import hu.nye.rft.Teacher.CreateTeacherRequest;
import hu.nye.rft.Teacher.TeacherController;
import hu.nye.rft.Teacher.TeacherServiceInterface;
import hu.nye.rft.Teacher.TeacherView;
import hu.nye.rft.problem.UserNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.*;

public class TeacherControllerTest {

    private static final Long USER_ID = 458L;
    private static final Long OTHER_USER_ID = 8596L;
    private static final String USER_NAME = "Test User";
    private static final String OTHER_USER_NAME = "Other Test User";
    private static final String EMAIL_ADDRESS = "test_user@mail.com";

    @InjectMocks
    private TeacherController underTest;

    @Mock
    private TeacherServiceInterface userService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        userService = null;
        underTest = null;
    }

    @Test
    public void testGetUserShouldReturnUserViewWhenCalledWithExistingId() {
        //given
        TeacherView expected = createUser(USER_ID, USER_NAME);
        given(userService.getTeacherById(USER_ID)).willReturn(expected);

        //when
        TeacherView actual = underTest.getTeacher(USER_ID);

        //then
        assertEquals(actual, expected);
        then(userService).should().getTeacherById(USER_ID);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserShouldReturnUserUserNotFoundExceptionWhenCalledWithNonExistingId() {
        //given
        given(userService.getTeacherById(USER_ID)).willThrow(UserNotFoundException.class);

        //when
        underTest.getTeacher(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnAListOfUser() {
        //given
        List<TeacherView> expected = List.of(
                createUser(USER_ID, USER_NAME),
                createUser(OTHER_USER_ID, OTHER_USER_NAME)
        );
        given(userService.getAllTeacher()).willReturn(expected);

        //when
        List<TeacherView> actual = underTest.getAllTeacher();

        //then
        assertEquals(actual, expected);
        then(userService).should().getAllTeacher();
    }

    @Test
    public void testAddUserShouldDelegateToUserServiceAndNotThrowExceptionWhenCalledWithValidUser() {
        //given
        CreateTeacherRequest request = createCreateUserRequest(USER_NAME, EMAIL_ADDRESS);

        //when
        underTest.addTeacher(request);

        //then
        then(userService).should().addTeacher(request);
    }

    @Test(expectedExceptions = EmailAlreadyInUseException.class)
    public void testAddUserShouldThrowEmailAlreadyInUseExceptionWhenCalledWithExistingEmail() {
        //given
        CreateTeacherRequest request = createCreateUserRequest(USER_NAME, EMAIL_ADDRESS);
        willThrow(EmailAlreadyInUseException.class).given(userService).addTeacher(request);

        //when
        underTest.addTeacher(request);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testAddUserShouldThrowUserAlreadyExistExceptionWhenCalledWithInValidUser() {
        //given
        CreateTeacherRequest request = createCreateUserRequest(USER_NAME, EMAIL_ADDRESS);
        willThrow(RuntimeException.class).given(userService).addTeacher(request);

        //when
        underTest.addTeacher(request);

        //then exception thrown
    }

    @Test
    public void testDeleteUserShouldDelegateToUserService() {
        //given
        //when
        underTest.deleteTeacher(USER_ID);

        //then
        then(userService).should().deleteTeacherById(USER_ID);
    }

    private CreateTeacherRequest createCreateUserRequest(String username, String emailAddress){
        return CreateTeacherRequest.builder()
                .teacherName(username)
                .emailAddress(emailAddress)
                .build();
    }

    private TeacherView createUser(Long userId, String username) {
        return TeacherView.builder()
                .teacherId(userId)
                .teacherName(username)
                .build();
    }

}
