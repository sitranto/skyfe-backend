package com.skyfe.service

import com.skyfe.domain.model.Message
import com.skyfe.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageRepository: MessageRepository) {

    fun sendMessage(message: Message): Message =
        messageRepository.save(message)

}