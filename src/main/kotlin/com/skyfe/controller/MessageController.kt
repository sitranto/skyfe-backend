package com.skyfe.controller

import com.skyfe.domain.dto.MessageDto
import com.skyfe.domain.model.Message
import com.skyfe.service.MessageService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
class MessageController(private val messageService: MessageService) {
    @GetMapping
    fun getAllMessages(
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String,
        @RequestHeader("chatId") chatId: Long
    ) =
        messageService.getAllMessages(token, chatId)
    @PostMapping("/send")
    fun sendMessage(
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String,
        @RequestHeader("chatId") chatId: Long,
        @RequestBody payload: MessageDto
    ) =
        messageService.sendMessage(token, chatId, payload)

   /* @DeleteMapping("/delete/{id}")
    fun deleteMessage(@PathVariable("id") id: Int) =
        messageService.deleteMessage(id)*/
}