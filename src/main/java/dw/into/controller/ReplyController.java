package dw.into.controller;

import dw.into.dto.ReplyDto;
import dw.into.model.QnA;
import dw.into.model.Reply;
import dw.into.model.User;
import dw.into.service.ReplyService;
import dw.into.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    private final ReplyService replyService;
    private final UserService userService;

    public ReplyController(ReplyService replyService, UserService userService) {
        this.replyService = replyService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReplyDto> saveReply(@RequestBody ReplyDto replyDto, @RequestHeader("Authorization") String token) {
        // JWT 토큰에서 사용자 정보 추출
        User user = userService.getUserFromToken(token.replace("Bearer ", ""));

        Reply reply = new Reply();
        reply.setUser(user);

        // QnA 객체 생성 및 설정
        QnA qna = new QnA();
        qna.setQnAId(replyDto.getQnAId());
        reply.setQnA(qna);

        reply.setReplyText(replyDto.getText());
        reply.setReplyTime(replyDto.getReplyTime());

        Reply savedReply = replyService.saveReply(reply);

        ReplyDto savedReplyDto = new ReplyDto();
        savedReplyDto.setReplyId(savedReply.getReplyId());
        savedReplyDto.setUserId(savedReply.getUser().getUserId());  // User 객체에서 ID만 추출
        savedReplyDto.setQnAId(savedReply.getQnA().getQnAId());  // QnA 객체에서 ID만 추출
        savedReplyDto.setText(savedReply.getReplyText());
        savedReplyDto.setReplyTime(savedReply.getReplyTime());

        return new ResponseEntity<>(savedReplyDto, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ReplyDto>> getAllReply() {
        List<Reply> replies = replyService.getAllReply();
        List<ReplyDto> replyDtos = replies.stream()
                .map(reply -> new ReplyDto().toReplyDtoFromReply(reply))
                .collect(Collectors.toList());
        return new ResponseEntity<>(replyDtos, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<ReplyDto>> getReplyAllByDto() {
        return new ResponseEntity<>(replyService.getReplyAllByDto(),
                HttpStatus.OK);
    }
}