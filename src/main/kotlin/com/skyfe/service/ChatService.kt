package com.skyfe.service

import com.skyfe.domain.dto.ResponseChatDto
import com.skyfe.domain.model.Chat
import com.skyfe.domain.model.User
import com.skyfe.domain.model.UserChat
import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.ChatRepository
import com.skyfe.repository.UserChatRepository
import com.skyfe.repository.UserRepository
import org.springframework.data.domain.Example
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val userChatRepository: UserChatRepository,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository,
    private val jwtService: JwtService
    ) {
    fun getAllChats(token: String): List<ResponseChatDto> {
        val number = jwtService.extractNumber(token.substringAfter("Bearer ")).toString()
        val userChats = userRepository.findByNumber(number).userChats
        val chats: MutableList<ResponseChatDto> = mutableListOf()
        for(userChat in userChats) {
            val lastMessageContent = if(userChat.chat.messages.isEmpty())
                "Пока нет сообщений"
            else
                userChat.chat.messages.last().content

            val partnerId = if(userChat.chat.userChats.first().user.id == userRepository.findByNumber(number).id)
                userChat.chat.userChats.last().user.id
            else
                userChat.chat.userChats.first().user.id

            val partner = userRepository.findById(partnerId!!.toInt()).get()

            chats.add(ResponseChatDto(
                userChat.chat.id,
                lastMessageContent,
                partner.firstName + " " + partner.lastName
            ))
        }
        return chats
    }
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