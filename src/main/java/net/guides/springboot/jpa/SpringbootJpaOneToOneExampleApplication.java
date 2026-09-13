package net.guides.springboot.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.guides.springboot.jpa.model.Instructor;
import net.guides.springboot.jpa.model.InstructorDetail;
import net.guides.springboot.jpa.repository.InstructorRepository;

@SpringBootApplication
public class SpringbootJpaOneToOneExampleApplication implements CommandLineRunner{

	public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaOneToOneExampleApplication.class, args);
    }

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public void run(String...args) throws Exception {

    	//for one to one mapping
    	//instrcutorDetail inside instructor should have one to one annotation
    	
        //Create 1st object
    	/*Instructor instructor = new Instructor("Roger", "Waters", "rogerwaters@gmail.com");

        InstructorDetail instructorDetail = new InstructorDetail("Suprabha", "RockClimbing");


        //Create 2nd object
        Instructor instructor2 = new Instructor("David", "Gilmore", "davidgilmore@gmail.com");

        InstructorDetail instructorDetail2 = new InstructorDetail("Suprabha", "RockClimbing");

        
        // associate the objects
        instructor.setInstructorDetail(instructorDetail);
        instructor2.setInstructorDetail(instructorDetail2);

        instructorRepository.save(instructor);
        instructorRepository.save(instructor2);*/
    	
    	
    	//for One to Many mapping
    	
    	Instructor instructor = new Instructor("Roger", "Waters", "rogerwaters@gmail.com");

        InstructorDetail instructorDetail1 = new InstructorDetail("Suprabha", "RockClimbing");
        instructorDetail1.setInstructor(instructor);
        InstructorDetail instructorDetail2 = new InstructorDetail("Singing", "Guitars");
        instructorDetail2.setInstructor(instructor);
        
        List<InstructorDetail> instDetailList= new ArrayList<>();
        instDetailList.add(instructorDetail1);
        instDetailList.add(instructorDetail2);
        instructor.setInstructorDetail(instDetailList);

        instructorRepository.save(instructor);

    	
    	
    }


}
