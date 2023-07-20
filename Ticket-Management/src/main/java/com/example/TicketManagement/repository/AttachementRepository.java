package com.example.TicketManagement.repository;

import com.example.TicketManagement.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AttachementRepository extends JpaRepository<Attachment, Long> {
}
