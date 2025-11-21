package com.Transaction.transaction.service.serviceImpl;


import com.Transaction.transaction.entity.Role;
import com.Transaction.transaction.entity.Users;
import com.Transaction.transaction.enums.ResetPassword;
import com.Transaction.transaction.exception.ResourceNotFoundException;
import com.Transaction.transaction.exception.UserAlreadyExistsException;
import com.Transaction.transaction.model.ChangePasswordRequest;
import com.Transaction.transaction.model.User;
import com.Transaction.transaction.payloads.UserDto;
import com.Transaction.transaction.repository.UserRepo;
import com.Transaction.transaction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final OtpGeneratorService otpGeneratorService;


    @Override
    public UserDto createUser(UserDto userDto) {

        Users user = this.dtoToUser(userDto);
        if (!userRepo.existsByEmail(user.getEmail())) {
            user.setRole(Role.SUPER_ADMIN);
            user.setEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Users user1 = this.userRepo.save(user);
            return userToDto(user1);
        } else {
            throw new UserAlreadyExistsException("User Already Registered !");
        }
    }

    @Override
    public void deleteUser(Integer id) {
        Users user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.userRepo.delete(user);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        Users user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setId(userDto.getId1());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        Users user1 = this.userRepo.save(user);
        return userToDto(user1);
    }

    @Override
    public UserDto getUserById(Integer id) {
        Users user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<Users> users = this.userRepo.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void changePassword(ChangePasswordRequest req) {

        Users user = this.userRepo.findByEmail(req.getUsername())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!Objects.equals(req.getNewPassword(), req.getConfirmPassword())) {
            throw new BadCredentialsException("Passwords do not match");
        }

        if (req.getResetPassword() == ResetPassword.CHANGE_PASSWORD) {

            if (!passwordEncoder.matches(req.getOldPassword(), user.getPassword())) {
                throw new BadCredentialsException("Old password is incorrect");
            }

            user.setPassword(passwordEncoder.encode(req.getNewPassword()));
            userRepo.save(user);
            return;
        }

        if (req.getResetPassword() == ResetPassword.RESET_PASSWORD) {
            if (!Objects.equals(user.getOtp(), req.getOtp())) {
                throw new BadCredentialsException("Invalid OTP");
            }

            user.setPassword(passwordEncoder.encode(req.getNewPassword()));
            userRepo.save(user);
            return;
        }

        throw new BadCredentialsException("Invalid password reset mode");
    }


    @Override
    public void sentOtp(User userJson) {


        String otp = this.otpGeneratorService.generateOTP();
        this.emailService.sendEmailForOtp(
                userJson.getUsername(),
                "Otp",
                "Please use this otp to change your password"+  ""  + otp
        );

        this.userRepo.findByEmail(userJson.getUsername()).ifPresent(existingUser -> {
            existingUser.setOtp(otp);
            this.userRepo.save(existingUser);
        });
    }

    public Users dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, Users.class);
    }

    private UserDto userToDto(Users user) {
        return this.modelMapper.map(user, UserDto.class);
    }

}
