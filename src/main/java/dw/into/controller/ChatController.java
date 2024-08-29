package dw.into.controller;

import dw.into.model.ChatMessage;
import dw.into.model.ChatUser;
import dw.into.model.StudyRoom;
import dw.into.service.ChatService;
import org.apache.catalina.util.CharsetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.sendMessage/{roomId}")
    @SendTo("/topic/public/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable("roomId") Integer roomId, ChatMessage chatMessage) {
        StudyRoom studyRoom = chatService.findStudyRoomById(roomId);
        System.out.println("Received message in room " + studyRoom + ": " + chatMessage);

        switch (chatMessage.getType()) {
            case JOIN:
                chatService.saveChatUser(chatMessage.getSender(), studyRoom);
                logger.info("User {} joined room {}", chatMessage.getSender(), roomId);
                break;
            case LEAVE:
                chatService.removeChatUser(chatMessage.getSender(), studyRoom);
                logger.info("User {} left room {}", chatMessage.getSender(), roomId);
                break;
            case CHAT:
                ChatMessage savedMessage = chatService.saveMessage(studyRoom, chatMessage);
                logger.info("Chat message saved to database: {}", savedMessage);
                return savedMessage;
            default:
                logger.warn("Unknown message type: {}", chatMessage.getType());
                break;
        }

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("User added: {}", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @GetMapping("/messages/{roomId}")
    public List<ChatMessage> getMessagesByRoomId(@PathVariable int roomId) {
        return chatService.findMessagesByRoomId(roomId);
    }

    @GetMapping("/user/{studyRoom}")
    public List<ChatUser> getChatUserByRoomId(@PathVariable StudyRoom studyRoom) {
        return chatService.getChatUserByRoomId(studyRoom);
    }
}