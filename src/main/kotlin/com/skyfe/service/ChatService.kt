package com.skyfe.service

import com.skyfe.domain.model.Chat
import com.skyfe.domain.model.User
import com.skyfe.domain.model.UserChat
import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.ChatRepository
import com.skyfe.repository.UserChatRepository
import com.skyfe.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val userChatRepository: UserChatRepository,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
    ) {
    fun createChat(authorId: Int, receiverId: Int): Chat {
        val chat = chatRepository.save(Chat())
        val author = userRepository.findById(authorId).get()
            ?: throw DataNotFoundException(HttpStatus.NOT_FOUND, "Author with id $authorId not found")
        val receiver = userRepository.findById(receiverId).get()
            ?: throw DataNotFoundException(HttpStatus.NOT_FOUND, "Receiver with id $receiverId not found")

        userChatRepository.save(UserChat(author, chat))
        userChatRepository.save(UserChat(receiver, chat))

        return chat
    }

    fun deleteChat(chatId: Int): String {
        val chat = chatRepository.findById(chatId)

        chatRepository.deleteById(chatId)

        return "Deleted chat $chatId"
    }

}