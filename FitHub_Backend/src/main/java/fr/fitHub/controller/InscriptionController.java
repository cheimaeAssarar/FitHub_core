package fr.fitHub.controller;

import fr.fitHub.dto.InscriptionResponseDto;
import fr.fitHub.entity.Inscription;
import fr.fitHub.service.InscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Inscriptions")
public class InscriptionController {
    private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping("/member/{memberId}/course/{courseId}")
    public ResponseEntity<InscriptionResponseDto> memberInscription(@PathVariable Long memberId, @PathVariable Long courseId) {
        Inscription inscription = inscriptionService.inscriptionMember(memberId, courseId);
        InscriptionResponseDto inscriptionResponseDto= new InscriptionResponseDto(inscription.getId(),inscription.getMember().getFirstName(),inscription.getCourse().getNom());
    return ResponseEntity.ok(inscriptionResponseDto);
    }

}
