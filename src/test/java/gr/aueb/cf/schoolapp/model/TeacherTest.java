package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void defaultConstructorGettersAndSetters() {
        Teacher teacher = new Teacher();

        teacher.setId(1);
        assertEquals(1, teacher.getId());

        teacher.setFirstname("Georgios");
        assertEquals("Georgios", teacher.getFirstname());

        teacher.setLastname("Makris");
        assertEquals("Makris", teacher.getLastname());
    }

    @Test
    void overloadedConstructorAndToString(){
        Teacher teacher = new Teacher(1, "Maria", "Papagiannis");
        assertEquals(1, teacher.getId());
        assertEquals("Maria", teacher.getFirstname());
        assertEquals("Papagiannis", teacher.getLastname());

        String expected = "id=1, firstname=Maria, lastname=Papagiannis";
        assertEquals(expected, teacher.toString());
    }
}