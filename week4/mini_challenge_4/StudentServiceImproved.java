package com.example.studentapp.service;

import com.example.studentapp.dto.StudentDTO;
import com.example.studentapp.entity.Student;
import com.example.studentapp.exception.StudentNotFoundException;
import com.example.studentapp.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImproved {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Create a new student with validation
     */
    public StudentDTO createStudent(StudentDTO studentDTO) {
        validateStudentDTO(studentDTO);
        Student student = modelMapper.map(studentDTO, Student.class);
        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentDTO.class);
    }

    /**
     * Get all students
     */
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get a student by ID
     */
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    /**
     * Update student details with validation
     */
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        validateStudentDTO(studentDTO);
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));

        existing.setName(studentDTO.getName());
        existing.setEmail(studentDTO.getEmail());
        existing.setAge(studentDTO.getAge());

        Student updated = studentRepository.save(existing);
        return modelMapper.map(updated, StudentDTO.class);
    }

    /**
     * Delete a student by ID
     */
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    // --- Helper methods ---

    private void validateStudentDTO(StudentDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("StudentDTO cannot be null");
        }
        if (!StringUtils.hasText(dto.getName())) {
            throw new IllegalArgumentException("Student name is required");
        }
        if (!StringUtils.hasText(dto.getEmail()) || !dto.getEmail().contains("@")) {
            throw new IllegalArgumentException("Valid email is required");
        }
        if (dto.getAge() == null || dto.getAge() < 0) {
            throw new IllegalArgumentException("Valid age is required");
        }
    }
} 