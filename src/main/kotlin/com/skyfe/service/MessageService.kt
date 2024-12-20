package com.skyfe.service

import com.skyfe.domain.dto.MessageDto
import com.skyfe.domain.model.Message
import com.skyfe.domain.model.User
import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.ChatRepository
import com.skyfe.repository.MessageRepository
import com.skyfe.repository.UserRepository
import org.hibernate.type.descriptor.jdbc.TimestampWithTimeZoneJdbcType
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
        for (userChat in getUser(token).userChats) {
            if(userChat.chat.id == chatId) {
                return userChat.chat.messages
            }
        }
        throw DataNotFoundException(HttpStatus.FORBIDDEN, "No access to chat")
    }

    fun sendMessage(token: String, chatId: Long, message: MessageDto) {
        messageRepository.save(Message(
            message.content,
            TimestampWithTimeZoneJdbcType(),
            getUser(token),
            chatRepository.findById(chatId.toInt()).get(),
            false,
            ""
        ))
    }

    private fun getUser(token: String): User {
        val number = jwtService.extractNumber(token.substringAfter("Bearer ")).toString()
        return userRepository.findByNumber(number)
    }
}