package rs.ac.bg.fon.njt.fitnessportal.dtos;

import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;

public class RolePutDto {

    private ApplicationUserRole name;

    public ApplicationUserRole getName() {
        return name;
    }

    public void setName(ApplicationUserRole name) {
        this.name = name;
    }
}
