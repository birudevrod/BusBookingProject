package com.BusBooking.util;

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
public class ExcelExporter  {

    // ... (other methods)

    @Override
    public InputStreamResource getUserAsExcel() {
        List<UserDTO> userDTOs = userRepository.findAll().stream().map(this::userToDto).collect(Collectors.toList());

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Users");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("First Name");
            headerRow.createCell(2).setCellValue("Last Name");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Phone Number");

            // Populate data rows
            int rowNum = 1;
            for (UserDTO userDTO : userDTOs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(userDTO.getId());
                row.createCell(1).setCellValue(userDTO.getFirstName());
                row.createCell(2).setCellValue(userDTO.getLastName());
                row.createCell(3).setCellValue(userDTO.getEmail());
                row.createCell(4).setCellValue(userDTO.getPhoneNumber());
            }

            workbook.write(out);

            ByteArrayInputStream excelInputStream = new ByteArrayInputStream(out.toByteArray());
            return new InputStreamResource(excelInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate Excel file", e);
        }
    }
