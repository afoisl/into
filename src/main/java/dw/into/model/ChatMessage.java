package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Entity
@Table(name = "chat_messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ChatMessageId;

    private ChatMessage.MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    public String toString() {
        return "ChatMessage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
