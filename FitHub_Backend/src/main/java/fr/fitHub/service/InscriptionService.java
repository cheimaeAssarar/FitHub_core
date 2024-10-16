package fr.fitHub.service;

import fr.fitHub.entity.Course;
import fr.fitHub.entity.Inscription;
import fr.fitHub.entity.Member;
import fr.fitHub.repository.CourseRepository;
import fr.fitHub.repository.InscriptionRepository;
import fr.fitHub.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.LocalTime.now;

@Service
public class InscriptionService {
    private final InscriptionRepository inscriptionRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
   public InscriptionService(InscriptionRepository inscriptionRepository, InscriptionRepository inscriptionRepository1, CourseRepository courseRepository, MemberRepository memberRepository){
       this.inscriptionRepository = inscriptionRepository1;
       this.courseRepository = courseRepository;
       this.memberRepository = memberRepository;
   }

    public Inscription inscriptionMember(Long memberId, Long courseId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        Course course= courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course not found"));

        Inscription inscription = new Inscription();
        inscription.setMember(member);
        inscription.setCourse(course);
        inscription.setDateInscription(LocalDate.now());

        return inscriptionRepository.save(inscription);
    }
}
