package tech.quban.qubantech.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.quban.qubantech.models.Complaint;
import tech.quban.qubantech.repository.ComplaintRepository;

import java.util.Optional;

@RestController
@RequestMapping("/complaints")
public class ComplaintsController {
    @Autowired
    private final ComplaintRepository complaintRepository;

    public ComplaintsController(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @GetMapping
    @Cacheable(value="Complaints")
    @Operation(description = "Returns all complaints.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<Iterable<Complaint>> getAll() {
        return ResponseEntity.ok(complaintRepository.findAll());
    }

    @GetMapping("/user/{userId}")
    @Cacheable(value = "UserComplaints", key="#userId")
    @Operation(description = "Returns complaints by specified userId.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<Iterable<Complaint>> getUserComplaints(@PathVariable String userId) {
        return ResponseEntity.ok(complaintRepository.findAllByUserId(userId));
    }

    @GetMapping("/institution/{institutionId}")
    @Cacheable(value = "InstitutionComplaints", key="#institutionId")
    @Operation(description = "Returns complaints by specified institutionId.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<Iterable<Complaint>> getInstitutionComplaints(@PathVariable Long institutionId) {
        return ResponseEntity.ok(complaintRepository.findAllByInstitutionId(institutionId));
    }

    @GetMapping("/{id}")
    @CachePut(value = "Complaint", key="#id")
    @Operation(description = "Returns complaint by specified complaint id.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<Optional<Complaint>> getComplaint(@PathVariable String id){
        return ResponseEntity.ok(complaintRepository.findComplaintById(id));
    }

    @PostMapping
    @Cacheable(value="Complaints")
    @Operation(description = "Returns OK if complaint successfully added.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<?> addComplaint(@RequestBody Complaint complaint) {
        complaintRepository.save(complaint);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/{id}")
    @CachePut(value="Complaint", key="#id")
    @Operation(description = "Edit complaint with specified id.")
    @ApiResponse(responseCode = "200")
    ResponseEntity<?> editComplaint(@RequestBody Complaint complaint, @PathVariable String id) {
        complaintRepository.save(complaint);
        return ResponseEntity.ok().build();
    }


}
