package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOTest {

    private static ITeacherDAO teacherDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
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
    void persistAndGetTeacher() throws TeacherDAOException {
        Teacher teacher = new Teacher(null, "Ioanna", "Anagnostakis");

        teacherDAO.insert(teacher);
        List<Teacher> teachers = teacherDAO.getByLastname("Anag");
        assertEquals(1, teachers.size());
    }

    @Test
    void updateTeacher() throws TeacherDAOException {
        Teacher teacher = new Teacher(2, "MariaUpdated", "PapaioannouUpdated" );

        teacherDAO.update(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname("PapaioannouUpdated");
        assertEquals(1, teachers.size());
    }

    @Test
    void  deleteTeacher() throws TeacherDAOException {
        teacherDAO.delete(1);

        Teacher teacher = teacherDAO.getById(1);
        assertNull(teacher);
    }

    @Test
    void getTeacherByIdPositive() throws TeacherDAOException {
        Teacher teacher = teacherDAO.getById(1);
        assertEquals("Markou", teacher.getLastname());
    }

    @Test
    void getTeacherByIdNegative() throws TeacherDAOException {
        Teacher teacher = teacherDAO.getById(5);
        assertNull(teacher);
    }

    @Test
    void getTeacherByLastname() throws TeacherDAOException {
        List<Teacher> teachers = teacherDAO.getByLastname("Mark");
        assertEquals(1, teachers.size());
    }

    public static void createDummyData() throws TeacherDAOException {
        Teacher teacher = new Teacher(null, "Georgios", "Markou");
        teacherDAO.insert(teacher);

        teacher = new Teacher(null, "Maria", "Papaioannou");
        teacherDAO.insert(teacher);
    }

}