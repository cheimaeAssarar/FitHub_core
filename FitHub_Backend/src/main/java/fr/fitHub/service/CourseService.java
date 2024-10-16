package fr.fitHub.service;

import fr.fitHub.dto.CourseRequestDto;
import fr.fitHub.dto.CourseResponseDto;
import fr.fitHub.entity.Course;
import fr.fitHub.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final  CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(CourseRequestDto courseDto) {
        Course course = new Course();
        course.setNom(courseDto.nom());
        course.setCoach(courseDto.coach());
        course.setSchedule(courseDto.schedule());
        return courseRepository.save(course);
    }
    public CourseResponseDto findCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("course not found"));
        return convertToResponseDto(course);
    }

    public List<CourseResponseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseDto) {
        Course course = this.courseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Course not found"));
        course.setNom(courseDto.nom());
        course.setCoach(courseDto.coach());
        course.setSchedule(courseDto.schedule());
        return convertToResponseDto(this.courseRepository.save(course));

    }

    private CourseResponseDto convertToResponseDto(Course updatedMember) {
        return new CourseResponseDto(updatedMember.getId(), updatedMember.getNom(), updatedMember.getCoach(), updatedMember.getSchedule());
    }

}
