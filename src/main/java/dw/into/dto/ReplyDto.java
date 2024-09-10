package dw.into.dto;

import dw.into.model.QnA;
import dw.into.model.Reply;
import dw.into.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyDto {
    private long replyId;
    private String userId;  // User 객체 대신 ID만 사용
    private Long qnAId;   // QnA 객체 대신 ID만 사용
    private String text;
    private LocalDate replyTime;

    public ReplyDto toReplyDtoFromReply(Reply reply) {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setReplyId(reply.getReplyId());
        replyDto.setUserId(reply.getUser().getUserId());  // User 객체에서 ID 추출
        replyDto.setQnAId(reply.getQnA().getQnAId());     // QnA 객체에서 ID 추출
        replyDto.setText(reply.getReplyText());
        replyDto.setReplyTime(reply.getReplyTime());
        return replyDto;
    }
}
