package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private static final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private static ITeacherService teacherService;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherService = new TeacherServiceImpl(teacherDAO);
        DBHelper.eraseData();
    }

    @BeforeEach
    public void setup() throws TeacherDAOException {
        createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertTeacher() throws TeacherDAOException {
        TeacherInsertDTO insertDTO = new TeacherInsertDTO("Άγγελος","Ιωάννου");
        teacherService.insertTeacher(insertDTO);

        List<Teacher> teachers = teacherService.getTeachersByLastname("Ιωάννου");
        assertEquals(1, teachers.size());
    }

    @Test
    public void updateTeacher() throws TeacherDAOException, TeacherNotFoundException {
        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(1, "Γεώργιος", "Μάρκου");
        teacherService.updateTeacher(updateDTO);

        List<Teacher> teachers = teacherService.getTeachersByLastname("Μάρκου");
        assertEquals(1, teachers.size());

    }

    @Test
    public void deleteTeacherPositive() throws TeacherDAOException, TeacherNotFoundException {

        teacherService.deleteTeacher(1);
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.getTeacherById(1);
        });
    }

    @Test
    public void deleteTeacherNegative() {
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.deleteTeacher(10);
        });
    }

    @Test
    void getTeacherByIdPositive() throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher = teacherService.getTeacherById(1);
        assertEquals("Markou", teacher.getLastname());
    }

    @Test
    void getTeacherByIdNegative() {
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.getTeacherById(15);
        });
    }

    @Test
    void getTeacherByLastname() throws TeacherDAOException {
        List<Teacher> teachers = teacherService.getTeachersByLastname("Markou");
        assertEquals(1, teachers.size());
    }

    @Test
    void getTeacherByLastnameNegative() throws TeacherDAOException {
        List<Teacher> teachers = teacherService.getTeachersByLastname("Κότος");
        assertEquals(0, teachers.size());
    }

    public static void createDummyData() throws TeacherDAOException {
        Teacher teacher = new Teacher(null, "Georgios", "Markou");
        teacherDAO.insert(teacher);

        teacher = new Teacher(null, "Maria", "Papaioannou");
        teacherDAO.insert(teacher);
    }
}