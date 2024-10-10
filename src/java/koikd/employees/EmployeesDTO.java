/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.employees;

import java.io.Serializable;

/**
 *
 * @author Minhngo
 */
public class EmployeesDTO implements Serializable {

    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Common properties of employees
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    private int employeeID;
    private String email;
    private String password;
    private String role;
    private String lastName;
    private String firstName;
    private String address;
    private boolean status;

    /**
     * Default Constructor
     */
    public EmployeesDTO() {
    }

    /**
     * Parameterized Constructor
     */
    public EmployeesDTO(int employeeID, String email, String password, String role, String lastName, String firstName, String address, boolean status) {
        this.employeeID = employeeID;
        this.email = email;
        this.password = password;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.status = status;
    }

    public EmployeesDTO(int employeeID, String email, String role, String lastName, String firstName, String address, boolean status) {
        this.employeeID = employeeID;
        this.email = email;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.status = status;
    }
    
    public EmployeesDTO(String email, String password, String role, String lastName, String firstName, String address, boolean status) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.status = status;
    }

    /**
     * Getter / Setter* @return
     */
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeesDTO{" + "employeeID=" + employeeID + ", email=" + email + ", password=" + password + ", role=" + role + ", lastName=" + lastName + ", firstName=" + firstName + ", address=" + address + ", status=" + status + '}';
    }
    
}
