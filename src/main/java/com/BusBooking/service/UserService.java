package com.BusBooking.service;

import com.BusBooking.payload.UserDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public UserDTO createUser(UserDTO userDTO);
    Page<UserDTO> getUsers(Pageable pageable);
    void deleteUser(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    InputStreamResource getUserAsExcel();


}
