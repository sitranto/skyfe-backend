package com.skyfe.controller

import com.skyfe.domain.dto.ChatDto
import com.skyfe.service.ChatService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
class ChatController(private val chatService: ChatService) {
    @PostMapping("/create")
    fun createChat(@RequestBody payload: ChatDto) =
        chatService.createChat(payload.authorId, payload.receiverId)
}
