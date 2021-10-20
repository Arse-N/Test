package com.example.testing.model;

import lombok.*;
import org.aspectj.lang.annotation.control.CodeGenerationHint;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 12)
    private String name;

    private Integer age;

    private String phone;


}
