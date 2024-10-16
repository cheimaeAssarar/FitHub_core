package fr.fitHub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.fitHub.dto.MemberRequestDto;
import fr.fitHub.dto.MemberResponseDto;
import fr.fitHub.service.MemberService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // Injection du service par le constructeur
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping(path="/api/members/create")
    public ResponseEntity<MemberResponseDto> create(@RequestBody MemberRequestDto memberRequestDto){

        log.info("Received request to create a new member with first name: " + memberRequestDto.firstName());

        try {
            MemberResponseDto memberToReturn = memberService.createMember(memberRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(memberToReturn);
        } catch (Exception e) {
            log.error("Error occurred while creating member", e);
            throw e;
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id){
        log.info("Received request to get member with id: " + id);
        try{
            MemberResponseDto memberToReturn = memberService.getMemberById(id);
            return ResponseEntity.status(HttpStatus.OK).body(memberToReturn);
        }catch (Exception e){
            log.error("Error occurred while getting member", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> update(@PathVariable Long id,@RequestBody MemberRequestDto memberRequestDto){
        log.info("Received request to update a member with first name: " + memberRequestDto.firstName());
        try{
            MemberResponseDto memberToReturn = memberService.updateMember(id, memberRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(memberToReturn);
        } catch (Exception e) {
          log.error("Error occurred while updating member", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        log.info("Received request to delete a member with id: " + id);
        try {
            memberService.deleteMember(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error occurred while deleting member with id: " + id, e);  // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public ResponseEntity<List<MemberResponseDto>> getAllMembers(){
        log.info("Received request to get members");
        List<MemberResponseDto> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}


