package tech.quban.qubantech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.quban.qubantech.models.Complaint;
import tech.quban.qubantech.repository.ComplaintRepository;
import tech.quban.qubantech.service.MQService;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final ComplaintRepository complaintRepository;
    private final MQService mqService;

    @GetMapping
    ResponseEntity<?> getEntity() {
        return ResponseEntity.ok(complaintRepository.findAll());
    }

    @GetMapping("/add")
    ResponseEntity<?> addEntity() {
        Complaint complaint = new Complaint();
        complaint.setId(UUID.randomUUID().toString());
        complaint.setUserId(UUID.randomUUID().toString());
        complaint.setKeywords(new String[]{"a", "b", "c"});
        complaint.setResponse("its response");
        complaint.setScore(14.88);
        complaint.setTheme("my theme");
        complaint.setInstitutionId(42L);
        complaint.setText("very long text very long text very long text very long text ");
        complaint.setStatus("applied");
        complaint.setDateSent(new Date());
        complaint.setDateResponded(new Date());
        complaintRepository.save(complaint);
        return ResponseEntity.ok(complaintRepository.findAll());
    }

    @GetMapping("/send")
    ResponseEntity<?> sendMsg() {
        mqService.sender(UUID.randomUUID().toString());
        return ResponseEntity.ok().build();
    }
}
