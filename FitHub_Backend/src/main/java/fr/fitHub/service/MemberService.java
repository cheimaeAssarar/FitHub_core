package fr.fitHub.service;

import fr.fitHub.dto.MemberRequestDto;
import fr.fitHub.dto.MemberResponseDto;
import fr.fitHub.entity.Member;
import fr.fitHub.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional //garantir que toutes les opérations se déroulent dans une transaction cohérente
public class MemberService {

    private final MemberRepository memberRepository;

    // Injection du repository par le constructeur
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member memberToCreate = buildMemberFromRequest(memberRequestDto);

        Member createdMember = this.create(memberToCreate);

        return convertToResponseDto(createdMember);
    }

    /**
     *
     * @param memberRequestDto
     * @return
     */
    private Member buildMemberFromRequest(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .firstName(memberRequestDto.firstName())
                .email(memberRequestDto.email())
                .age(memberRequestDto.age())
                .build();
    }

    /**
     * 
     * @param createdMember
     * @return
     */
    private MemberResponseDto convertToResponseDto(Member createdMember) {
        return new MemberResponseDto(createdMember.getId(), createdMember.getFirstName(), createdMember.getEmail(), createdMember.getAge());
    }

    public Member create(Member member) {
        return this.memberRepository.save(member);
    }

    public MemberResponseDto getMemberById(Long id) {
        Member member = this.memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return convertToResponseDto(member);
    }

    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member member = this.memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        member.setFirstName(memberRequestDto.firstName());
        member.setEmail(memberRequestDto.email());
        member.setAge(memberRequestDto.age());
        return convertToResponseDto(this.memberRepository.save(member));
    }

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("Member not found");
        }
        memberRepository.deleteById(id);
    }

    public List<MemberResponseDto> getAllMembers() {
// Récupération de tous les membres depuis la base de données
        List<Member> members = (List<Member>) memberRepository.findAll();

        // Conversion des entités Member en DTOs MemberResponseDto
        return members.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList()); // Conversion en liste
    }
}
