package ru.edu.controller;


//AuthResult будет хранить результат авторизации
public class AuthResult {

    private String status;

    public AuthResult(String status) {
        this.status = status;
    }



    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AuthResult{" +
                "status='" + status + '\'' +
                '}';
    }
}
