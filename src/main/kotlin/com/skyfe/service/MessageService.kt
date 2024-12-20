package com.skyfe.service

import com.skyfe.domain.model.Message
import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.ChatRepository
import com.skyfe.repository.MessageRepository
import com.skyfe.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val messageRepository: MessageRepository,
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository
    ) {
    fun getAllMessages(token: String, chatId: Long): List<Message> {
        val number = jwtService.extractNumber(token.substringAfter("Bearer ")).toString()
        val user = userRepository.findByNumber(number)
        for (i in user.userChats) {
            if (i.chat.id == chatId) {
                return chatRepository.findById(chatId.toInt()).get().messages
            }
        }
        throw DataNotFoundException(HttpStatus.FORBIDDEN, "No access to chat")
    }

    fun sendMessage(message: Message): Message =
        messageRepository.save(message)

}