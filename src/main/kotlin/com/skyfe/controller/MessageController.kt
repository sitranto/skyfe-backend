package com.skyfe.controller

import com.skyfe.domain.model.Message
import com.skyfe.service.MessageService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
class MessageController(private val messageService: MessageService) {
    @PostMapping("/send")
    fun sendMessage(@RequestBody payload: Message) =
        messageService.sendMessage(payload)
}