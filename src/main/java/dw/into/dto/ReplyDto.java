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
    private User userId;
    private QnA qnAId;
    private String text;
    private LocalDate replyTime;

    public ReplyDto toReplyDtoFromReply(Reply reply) {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setReplyId(reply.getReplyId());
        replyDto.setUserId(reply.getUser());
        replyDto.setQnAId(reply.getQnA());
        replyDto.setText(reply.getReplyText());
        replyDto.setReplyTime(reply.getReplyTime());
        return replyDto;
    }
}
