package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ChatMessageId;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    private MessageType type;
    private String content;
    private String sender;

    @ManyToOne
    @JoinColumn
    private StudyRoom studyRoom;

    @PrePersist
    protected void onCreate() {
        sentAt = LocalDateTime.now();
    }

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "ChatMessageId=" + ChatMessageId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", roomId='" + studyRoom + '\'' +
                '}';
    }
}