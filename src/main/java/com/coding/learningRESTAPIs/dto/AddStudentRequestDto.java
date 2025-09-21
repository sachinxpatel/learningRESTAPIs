package com.coding.learningRESTAPIs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDto {

        @NotBlank(message = "Name is required")
        @Size(min = 3,max = 30,message = " Name is Required min 3 and max 30")
        private String Name;
        @Email
        @NotBlank(message = "Email is Required")
        private String email;
    }

