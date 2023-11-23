package com.cg.springboot.controller;

import com.cg.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"Reddy","Balaji");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("Custom-header","Reddy").body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Reddy","Balaji"));
        students.add(new Student(2,"Madhu","C"));
        students.add(new Student(3,"Bharat","G"));
        students.add(new Student(4,"thippe","swamy"));
        return ResponseEntity.ok(students);

    }
    //http://localhost:8080/students/1/Reddy/Balaji
    @GetMapping("{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable String firstName,
                                       @PathVariable String lastName){
        Student student = new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/students/query?id=1&firstName=Reddy&lastName=Balaji
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestParam(@RequestParam int id,
                                       @RequestParam String firstName,
                                       @RequestParam String lastName){
        Student student = new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
}
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("student deleted successfully!");
    }
}