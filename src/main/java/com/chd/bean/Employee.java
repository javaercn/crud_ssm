package com.chd.bean;

public class Employee {
    private Integer emplId;

    private String emplName;

    private String gender;

    public Employee() {
    }

    private String email;

    public Employee(Integer emplId, String emplName, String gender, String email, Integer dId) {
        this.emplId = emplId;
        this.emplName = emplName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }

    private Integer dId;

    //希望查询员工的同时，部门信息也是查询好的
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getEmplId() {
        return emplId;
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName == null ? null : emplName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emplId=" + emplId +
                ", emplName='" + emplName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dId=" + dId +
                ", department=" + department +
                '}';
    }
}