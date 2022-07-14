package rs.ac.bg.fon.njt.fitnessportal.services;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPostDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Member;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.AccountVerificationFailedException;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.EmailExistsException;
import rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers.UserMapper;
import rs.ac.bg.fon.njt.fitnessportal.repositories.MemberRepository;
import rs.ac.bg.fon.njt.fitnessportal.repositories.UserRepository;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.services.utility.UserConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private MemberRepository memberRepository;
    private UserConfigurer userConfigurer;
    private JavaMailSender javaMailSender;


    @Override
    @Transactional
    public UserGetDto create(UserPostDto userPostDto, List<ApplicationUserRole> roleTypes, String siteURL) throws MessagingException, UnsupportedEncodingException {
        if(userRepository.existsByEmail(userPostDto.getEmail())) throw new EmailExistsException(userPostDto.getEmail());

        Member member = userMapper.userPostDtoToMember(userPostDto);
        userConfigurer.addRoles(member, roleTypes);
        userConfigurer.encodePassword(member);
        
        String randomCode = RandomString.make(64);
        member.setVerificationCode(randomCode);
        member.setEnabled(false);

        memberRepository.save(member);
        
        sendVerificationEmail(member, siteURL);
        
        return userMapper.userToUserGetDto(member);
    }

    private void sendVerificationEmail(Member user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "bulaat27@gmail.com";
        String senderName = "Fitness Portal";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
        String verifyURL = siteURL + "/api/v1/members/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        javaMailSender.send(message);
    }

    public void verify(String verificationCode){
        Member member = memberRepository.findByVerificationCode(verificationCode);

        if(member == null || member.getEnabled() == true) throw new AccountVerificationFailedException();

        member.setVerificationCode(null);
        member.setEnabled(true);
        memberRepository.save(member);
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setUserConfigurer(UserConfigurer userConfigurer) {
        this.userConfigurer = userConfigurer;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
