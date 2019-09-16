//package org.apdoer.manager.controller;
//
//import org.apdoer.manager.constants.ManagerConstant;
//import org.apdoer.manager.model.pojo.VerificationCodePo;
//import org.apdoer.manager.model.vo.EmailVo;
//import org.apdoer.manager.service.EmailService;
//import org.apdoer.manager.service.VerificationCodeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author apdoer
// * @date 2018-12-26
// */
//@RestController
//@RequestMapping("api")
//public class VerificationCodeController {
//
//    @Autowired
//    private VerificationCodeService verificationCodeService;
//
//    @Autowired
//    @Qualifier("jwtUserDetailsService")
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping(value = "/code/resetEmail")
//    public ResponseEntity resetEmail(@RequestBody VerificationCodePo code) throws Exception {
//        code.setScenes(ManagerConstant.RESET_MAIL);
//        EmailVo emailVo = verificationCodeService.sendEmail(code);
//        emailService.send(emailVo,emailService.find());
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/code/email/resetPass")
//    public ResponseEntity resetPass(@RequestParam String email) throws Exception {
//        VerificationCodePo code = new VerificationCodePo();
//        code.setType("email");
//        code.setValue(email);
//        code.setScenes(ManagerConstant.RESET_MAIL);
//        EmailVo emailVo = verificationCodeService.sendEmail(code);
//        emailService.send(emailVo,emailService.find());
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/code/validated")
//    public ResponseEntity validated(VerificationCodePo code){
//        verificationCodeService.validated(code);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}
