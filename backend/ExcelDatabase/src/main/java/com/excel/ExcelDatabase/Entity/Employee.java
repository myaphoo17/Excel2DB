package com.excel.ExcelDatabase.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sr;
    private String division;
    private String staff_ID;
    private String name;
    private String doorLogNo;
    private String department;
    private String team;
    private String email;
    private String status;
    private String role;

    public Long getSr() {
        return sr;
    }

    public void setSr(Long sr) {
        this.sr = sr;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getStaff_ID() {
        return staff_ID;
    }

    public void setStaff_ID(String staff_ID) {
        this.staff_ID = staff_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoorLogNo() {
        return doorLogNo;
    }

    public void setDoorLogNo(String doorLogNo) {
        this.doorLogNo = doorLogNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
