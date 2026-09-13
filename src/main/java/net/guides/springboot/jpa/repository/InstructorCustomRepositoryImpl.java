package net.guides.springboot.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import net.guides.springboot.jpa.dto.InstructorSummaryDTO;
import net.guides.springboot.jpa.model.Instructor;
import net.guides.springboot.jpa.model.InstructorDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructorCustomRepositoryImpl implements InstructorCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<InstructorSummaryDTO> findInstructorSummariesByHobby(String hobby) {
        // 1. Initialize CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InstructorSummaryDTO> query = cb.createQuery(InstructorSummaryDTO.class);

        // 2. Define the Root (The FROM clause)
        Root<Instructor> instructorRoot = query.from(Instructor.class);

        // 3. Perform a JOIN between Instructor and InstructorDetail tables
        Join<Instructor, InstructorDetail> detailJoin = instructorRoot.join("instructorDetail", JoinType.INNER);

        // 4. Implement Projection (Select ONLY specified fields into our DTO constructor)
        query.select(cb.construct(
                InstructorSummaryDTO.class,
                instructorRoot.get("firstName"),
                instructorRoot.get("email"),
                detailJoin.get("hobby")
        ));

        // 5. Add Where Conditions (The WHERE clause)
        Predicate hobbyPredicate = cb.equal(detailJoin.get("hobby"), hobby);
        query.where(hobbyPredicate);

        // 6. Execute the query using EntityManager
        return entityManager.createQuery(query).getResultList();
    }
}
