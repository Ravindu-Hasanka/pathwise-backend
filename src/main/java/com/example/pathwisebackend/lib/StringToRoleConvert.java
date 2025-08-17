package com.example.pathwisebackend.lib;

import com.example.pathwisebackend.Enum.UserRoles;

public class StringToRoleConvert {

    public static UserRoles fromString(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }

        switch (role.trim().toUpperCase()) {
            case "JOB_SEEKER":
            case "JOBSEEKER":
                return UserRoles.JOB_SEEKER;
            case "COACH":
                return UserRoles.COACH;
            case "ADMIN":
                return UserRoles.ADMIN;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
