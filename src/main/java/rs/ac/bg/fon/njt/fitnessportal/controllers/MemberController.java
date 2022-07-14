package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPostDto;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.services.MemberService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/api/v1/members")
public class MemberController {

    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<UserGetDto> signUp(@RequestBody @Valid UserPostDto userPostDto, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(memberService.create(userPostDto, List.of(ApplicationUserRole.USER), getSiteURL(request)));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@Param("code") String code){
        memberService.verify(code);

        return ResponseEntity.ok().body("Successful verification!");
    }


    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        System.out.println(siteURL);
        System.out.println(request.getServletPath());
        return siteURL.replace(request.getServletPath(), "");
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
