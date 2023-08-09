package com.example.TicketManagement.service;


import com.example.TicketManagement.dto.request.AttachmentRequestDTO;
import com.example.TicketManagement.entity.Attachment;
import com.example.TicketManagement.entity.Message;
import com.example.TicketManagement.exception.IdNotFoundException;
import com.example.TicketManagement.repository.AttachementRepository;
import com.example.TicketManagement.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final MessageRepository messageRepository;
    private final AttachementRepository attachementRepository;

    public String addAttachment(Long messageId, AttachmentRequestDTO attachmentRequestDTO) {

        Message message = messageRepository.findById(messageId).orElseThrow(() -> new IdNotFoundException("ID not found"));

        Attachment attachment = Attachment.builder()
                .message(message)
                .fileName(attachmentRequestDTO.getFilename())
                .build();

        attachementRepository.save(attachment);

        return "Attachment inserted";
    }
}
