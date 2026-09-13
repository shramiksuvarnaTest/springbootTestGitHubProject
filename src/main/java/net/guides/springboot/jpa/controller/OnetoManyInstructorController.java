package net.guides.springboot.jpa.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import net.guides.springboot.jpa.dto.InstructorSummaryDTO;
import net.guides.springboot.jpa.model.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.guides.springboot.jpa.model.Instructor;
import net.guides.springboot.jpa.repository.InstructorRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class OnetoManyInstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

//just testing something to demonstrate git actions
    @GetMapping("/instructors")
    public List < Instructor > getInstructors() {
        return instructorRepository.findAll();
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity < Instructor > getInstructorById(
        @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
        Instructor user = ((Optional<Instructor>)instructorRepository.findById(instructorId)).get();
            //.orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/instructors")
    public Instructor createUser(@Valid @RequestBody Instructor instructor) {
        System.out.println("Create User::");
        addInstructorDetails(instructor);
        return instructorRepository.save(instructor);
    }

    @PutMapping("/instructors/{id}")
    public ResponseEntity < Instructor > updateUser(
        @PathVariable(value = "id") Long instructorId,
        @Valid @RequestBody Instructor userDetails) throws ResourceNotFoundException {
        Instructor user = ((Optional<Instructor>)instructorRepository.findById(instructorId)).get();
            //.orElseThrow(() - > new ResourceNotFoundException("Instructor not found :: " + instructorId));
        user.setEmail(userDetails.getEmail());
        final Instructor updatedUser = instructorRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/instructors/{id}")
    public Map < String, Boolean > deleteUser(
        @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
        Instructor instructor = ((Optional<Instructor>)instructorRepository.findById(instructorId)).get();
            //.orElseThrow(() - > new ResourceNotFoundException("Instructor not found :: " + instructorId));

        instructorRepository.delete(instructor);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public void addInstructorDetails(Instructor instructor) {
        if (instructor != null) {
            for (InstructorDetail detail : instructor.getInstructorDetail()) {
                detail.setInstructor(instructor); // Tell the child who its parent is
            }
        }
    }


    // Endpoint testing Criteria API + Projection
    @GetMapping("/summary")
    public List<InstructorSummaryDTO> getSummariesByHobby(@RequestParam String hobby) {
        return instructorRepository.findInstructorSummariesByHobby(hobby);
    }
}
