package com.excel.ExcelDatabase.Controller;

import com.excel.ExcelDatabase.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadUsersData(@RequestParam("file") MultipartFile file) throws IOException {
        if (employeeService.isValidExcelFile(file)) {
            employeeService.uploadEmployeeData(file);
            return ResponseEntity.ok(Map.of("Message", "Employee data uploaded and saved to database successfully"));
        } else {
            return ResponseEntity.badRequest().body("Invalid file format. Please upload an Excel file.");
        }
    }
}
