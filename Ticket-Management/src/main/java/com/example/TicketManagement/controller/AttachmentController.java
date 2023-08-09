package com.example.TicketManagement.controller;

import com.example.TicketManagement.dto.request.AttachmentRequestDTO;
import com.example.TicketManagement.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attachments")
@RequiredArgsConstructor
public class AttachmentController {


    private final AttachmentService attachmentService;

    @PostMapping("/{messageId}")
    public String addAttachment(@PathVariable Long messageId, @RequestBody AttachmentRequestDTO attachmentRequestDTO) {
        return attachmentService.addAttachment(messageId, attachmentRequestDTO);
    }

}