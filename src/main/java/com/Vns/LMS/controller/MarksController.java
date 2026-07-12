package com.Vns.LMS.controller;

import com.Vns.LMS.dto.MarksRequest;
import com.Vns.LMS.dto.MarksResponse;
import com.Vns.LMS.service.MarksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarksController {
    private final MarksService marksService;

    public MarksController(MarksService marksService){
        this.marksService=marksService;
    }
    @PostMapping
    public ResponseEntity<MarksResponse> createMarks(@RequestBody MarksRequest request) {
        return new ResponseEntity<>(marksService.createMarks(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MarksResponse>> getAllMarks() {
        return ResponseEntity.ok(marksService.getAllMarks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarksResponse> getMarksById(@PathVariable Long id) {
        return ResponseEntity.ok(marksService.getMarksById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarksResponse> updateMarks(@PathVariable Long id,
                                                     @RequestBody MarksRequest request) {
        return ResponseEntity.ok(marksService.updateMarks(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMarks(@PathVariable Long id) {
        marksService.deleteMarks(id);
        return ResponseEntity.ok("Marks deleted successfully");
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MarksResponse>> getMarksByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                marksService.getMarksByStudent(studentId));
    }
}
