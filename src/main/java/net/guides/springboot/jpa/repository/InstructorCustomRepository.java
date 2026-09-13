package net.guides.springboot.jpa.repository;

import net.guides.springboot.jpa.dto.InstructorSummaryDTO;

import java.util.List;

public interface InstructorCustomRepository {
    List<InstructorSummaryDTO> findInstructorSummariesByHobby(String hobby);
}
