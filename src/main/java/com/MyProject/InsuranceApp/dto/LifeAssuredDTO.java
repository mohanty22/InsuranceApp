package com.MyProject.InsuranceApp.dto;
import jakarta.validation.constraints.*;


public class LifeAssuredDTO {
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]{1,30}$", message = "Name must only contain letters and be max 30 characters")
    private String name;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(M|F|O)$", message = "Gender must be M, F, or O")
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LifeAssuredDTO() {
    }

    public LifeAssuredDTO(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "LifeAssured{" +

                " name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
