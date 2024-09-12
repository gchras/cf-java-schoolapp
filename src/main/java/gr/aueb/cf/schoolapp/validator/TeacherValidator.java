package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.BaseDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator<T> {

    /**
     * No instances of this class should be available. (Utility class)
     */
    private TeacherValidator() {

    }

    public static <T extends BaseDTO> Map<String, String> validate(T dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "Firstname should be between 2 and 32 characters");

            // errors.put("όνομα", "Το Όνομα πρέπει να περιέχει απο 2 έως 32 χαρακτήρες");
        }

        if (dto.getLastname().length() < 2 || dto.getLastname().length() > 32) {
            errors.put("lastname", "Lastname should be between 2 and 32 characters");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "Firstname should not include whitespaces");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "Lastname should not include whitespaces");
        }

        // logic validation

        return errors;


    }
}
