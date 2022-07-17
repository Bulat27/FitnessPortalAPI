package rs.ac.bg.fon.njt.fitnessportal.dtos.appointment;

import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;

public class AppointmentWithMemberGetDto {

    private Integer id;
    private Boolean attended;
    private UserGetDto member;

    public AppointmentWithMemberGetDto() {
    }

    public AppointmentWithMemberGetDto(Integer id, Boolean attended, UserGetDto member) {
        this.id = id;
        this.attended = attended;
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public UserGetDto getMember() {
        return member;
    }

    public void setMember(UserGetDto member) {
        this.member = member;
    }
}
