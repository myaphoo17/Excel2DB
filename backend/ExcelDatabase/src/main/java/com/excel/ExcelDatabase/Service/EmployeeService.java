package com.excel.ExcelDatabase.Service;

import com.excel.ExcelDatabase.Entity.Employee;
import com.excel.ExcelDatabase.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public void uploadEmployeeData(MultipartFile file) throws IOException {
        List<Employee> newEmployeeData = getEmployeeDataFromExcel(file.getInputStream());
        List<Employee> existingEmployeeData = employeeRepository.findAll();

        for (Employee newUser : newEmployeeData) {
            if ("99-09999".equals(newUser.getStaff_ID())) {
                continue;
            }
            boolean found = false;

            for (Employee existingUser : existingEmployeeData) {
                if (existingUser.getStaff_ID().equals(newUser.getStaff_ID())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                employeeRepository.save(newUser);
            }
        }
    }

    public List<Employee> getEmployeeDataFromExcel(InputStream inputStream) {
        List<Employee> user_data = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheet("Employee_Data");
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                Employee user = new Employee();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int cellIndex = cell.getColumnIndex();
                    switch (cellIndex) {
                        case 0:
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                user.setSr((long) cell.getNumericCellValue());
                            }
                            break;
                        case 1:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setDivision(cell.getStringCellValue());
                            }
                            break;
                        case 2:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setStaff_ID(cell.getStringCellValue());
                            }
                            break;
                        case 3:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setName(cell.getStringCellValue());
                            }
                            break;
                        case 4:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setDoorLogNo(cell.getStringCellValue());
                            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                user.setDoorLogNo(String.valueOf((long) cell.getNumericCellValue()));
                            }
                            break;
                        case 5:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setDepartment(cell.getStringCellValue());
                            }
                            break;
                        case 6:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setTeam(cell.getStringCellValue());
                            }
                            break;
                        case 7:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setEmail(cell.getStringCellValue());
                            }
                            break;
                        case 8:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setStatus(cell.getStringCellValue());
                            }
                            break;
                        case 9:
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                user.setRole(cell.getStringCellValue());
                            }
                            break;
                        default:
                            break;
                    }
                }
                user_data.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user_data;
    }
}
