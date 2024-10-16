package fr.fitHub.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.fitHub.entity.Member;

@Repository // Annotation obligatoire qui indique à Spring que c'est un repository
public interface MemberRepository extends CrudRepository<Member,Long>{

    // Spring Data va fournir les méthodes pour dialoguer avec la base de données

}
