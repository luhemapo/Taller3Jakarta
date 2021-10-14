package com.example.taller3jakarta;

public class User {
    String email;
    String userName;
    String password;
    String role;
    String petName;

    public User(String email, String userName, String password, String role, String petName){
        this.email=email;
        this.userName=userName;
        this.password=password;
        this.role=role;
        this.petName=petName;
    }

    public String toString(){
        return "Email: "+email+" UserName: "+userName+" Password: "+password+" Role: "+role+" PetName: "+petName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}
