//package io.monkey.configuration;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class SecurityConfiguration {
//    private final PasswordEncoder passwordEncoder;
//
//    public SecurityConfiguration() {
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }
//
//    public String encodePassword(String passwordValue){
//        return passwordEncoder.encode(passwordValue);
//    }
//}
