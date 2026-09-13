package net.guides.springboot.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstructorSummaryDTO {
    private String firstName;
    private String email;
    private String hobby;
}
