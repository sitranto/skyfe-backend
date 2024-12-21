package com.skyfe.controller

import com.skyfe.domain.dto.ChatDto
import com.skyfe.service.ChatService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
class ChatController(private val chatService: ChatService) {
    @GetMapping
    fun getChat(@RequestHeader(HttpHeaders.AUTHORIZATION) token: String) =
        chatService.getAllChats(token)

    @PostMapping("/create")
    fun createChat(@RequestBody payload: ChatDto) =
        chatService.createChat(payload.authorId, payload.receiverId)

    @DeleteMapping("/delete")
    fun deleteChat(@RequestBody payload: ChatDto) =
        chatService
}
