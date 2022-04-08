package tech.quban.qubantech.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.quban.qubantech.models.Complaint;
import tech.quban.qubantech.repository.ComplaintRepository;

@RestController
@RequestMapping("/complaints")
public class ComplaintsController {
    @Autowired
    private final ComplaintRepository complaintRepository;

    public ComplaintsController(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @GetMapping
    @Operation(description = "Returns all complaints.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(complaintRepository.findAll());
    }

    @GetMapping("/user/{userId}")
    @Operation(description = "Returns complaints by specified userId.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<?> getUserComplaints(@PathVariable String userId) {
        return ResponseEntity.ok(complaintRepository.findAllByUserId(userId));
    }

    @Operation(description = "Returns complaints by specified institutionId.")
    @ApiResponse(responseCode = "200")
    @GetMapping("/institution/{institutionId}")
    ResponseEntity<?> getInstitutionComplaints(@PathVariable Long institutionId) {
        return ResponseEntity.ok(complaintRepository.findAllByInstitutionId(institutionId));
    }

    @Operation(description = "Returns OK if complaint successfully added.")
    @ApiResponse(responseCode = "200")
    @PostMapping("/")
    ResponseEntity<?> addComplaint(@RequestBody Complaint complaint) {
        complaintRepository.save(complaint);
        return ResponseEntity.ok().build();
    }
}
