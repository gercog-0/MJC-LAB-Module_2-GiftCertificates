package com.epam.esm.validator;

public class TagValidator {

    private static final String ID_REGEX = "^\\d{1,19}$";
    private static final String NAME_REGEX = "^[a-zA-Z0-9]{5,45}$";

    private TagValidator() {
    }

    public static boolean isNameCorrect(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public static boolean isIdCorrect(String id) {
        return id != null && id.matches(ID_REGEX);
    }

}
