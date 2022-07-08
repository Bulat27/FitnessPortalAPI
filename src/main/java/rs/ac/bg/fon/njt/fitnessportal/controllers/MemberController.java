package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPostDto;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.services.MemberService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/members")
public class MemberController {

    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<UserGetDto> signUp(@RequestBody @Valid UserPostDto userPostDto){
        return ResponseEntity.ok(memberService.create(userPostDto, List.of(ApplicationUserRole.USER)));
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
