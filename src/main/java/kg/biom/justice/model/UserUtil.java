package kg.biom.justice.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

    public static String getInitials(String firstName, String lastName, String email) {
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            return getInitialsFromEmail(email);
        }

        char firstInitial = (firstName != null && !firstName.isBlank())
                ? Character.toUpperCase(firstName.trim().charAt(0))
                : ' ';

        char lastInitial = (lastName != null && !lastName.isBlank())
                ? Character.toUpperCase(lastName.trim().charAt(0))
                : ' ';

        return String.format("%c%c", firstInitial, lastInitial).trim();
    }

    private static String getInitialsFromEmail(String email) {
        String[] parts = email.split("@")[0].split("\\.");
        StringBuilder initials = new StringBuilder();

        for (String part : parts)
            if (!part.isBlank()) initials.append(Character.toUpperCase(part.charAt(0)));

        return initials.toString();
    }
}
