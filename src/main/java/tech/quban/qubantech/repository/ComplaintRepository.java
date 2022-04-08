package tech.quban.qubantech.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tech.quban.qubantech.models.Complaint;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends PagingAndSortingRepository<Complaint, String> {
    Optional<Complaint> findComplaintById(String id);
    List<Complaint> findAllByUserId(String userId);
    List<Complaint> findAllByInstitutionId(Long institutionId);
}
