package com.BusBooking.service.impl;

import com.BusBooking.entities.User;
import com.BusBooking.payload.UserDTO;
import com.BusBooking.repository.UserRepository;
import com.BusBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String uploadDirectory = "src/main/resources/static/user_profile_image/";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDTO> userDTOs = userPage.stream().map(this::userToDto).collect(Collectors.toList());
        return new PageImpl<>(userDTOs, pageable, userPage.getTotalElements());
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);

    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirtName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setUpdatedAt(new Date());

        MultipartFile profileImage = userDTO.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = saveProfileImage(profileImage);
            user.setProfilePicture(fileName);
        }
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);

    }

    @Override
    public InputStreamResource getUserAsExcel() {

        List<UserDTO> userDTOs = userRepository.findAll();


        ByteArrayInputStream excelInputStream = ExcelExporter.exportUserToExcel(userDTOs);



        return new InputStreamResource(excelInputStream);
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

// Encode the password

        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        MultipartFile profileImage = userDTO.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = saveProfileImage(profileImage);
            user.setProfilePicture(fileName);
        }

        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    private String saveProfileImage(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String baseFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            String uniqueFileName = baseFileName + "_" + System.currentTimeMillis() + fileExtension;
            Path path = Paths.get(uploadDirectory + uniqueFileName);
            Files.write(path, bytes);

            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save profile image", e);
        }
    }

    private User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirtName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    private UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirtName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setProfilePicture(user.getProfilePicture());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }


}