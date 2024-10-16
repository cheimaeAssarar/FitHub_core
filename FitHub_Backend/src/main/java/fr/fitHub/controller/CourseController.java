package fr.fitHub.controller;

import fr.fitHub.dto.CourseRequestDto;
import fr.fitHub.dto.CourseResponseDto;
import fr.fitHub.entity.Course;
import fr.fitHub.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> addCourse(@RequestBody CourseRequestDto courseRequestDto) {
        Course newCourse = courseService.addCourse(courseRequestDto);
        CourseResponseDto courseResponseDto = new CourseResponseDto(newCourse.getId(), newCourse.getNom(), newCourse.getCoach(), newCourse.getSchedule());
        return ResponseEntity.ok(courseResponseDto);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long courseId) {
        try {
            CourseResponseDto courseToReturn = courseService.findCourseById(courseId);
            return ResponseEntity.ok(courseToReturn);
        } catch (Exception e) {
            log.error("Error occurred while getting course",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto) {

        try{
            CourseResponseDto courseToReturn =courseService.updateCourse(id, courseRequestDto);
            return ResponseEntity.ok(courseToReturn);
        }catch (Exception e) {
            log.error("Error occurred while updating course",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDto> deleteCourse(@PathVariable Long id) {

    return null;
    }
}
