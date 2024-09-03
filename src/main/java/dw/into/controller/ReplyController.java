package dw.into.controller;

import dw.into.dto.ReplyDto;
import dw.into.model.Reply;
import dw.into.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReplyDto> saveReply(@RequestBody ReplyDto replyDto) {
        Reply reply = new Reply();
        reply.setUser(replyDto.getUserId()); // User 객체
        reply.setQnA(replyDto.getQnAId()); // QnA 객체
        reply.setReplyText(replyDto.getText());
        reply.setReplyTime(replyDto.getReplyTime());

        Reply savedReply = replyService.saveReply(reply);

        ReplyDto savedReplyDto = new ReplyDto();
        savedReplyDto.setReplyId(savedReply.getReplyId());
        savedReplyDto.setUserId(savedReply.getUser());
        savedReplyDto.setQnAId(savedReply.getQnA());
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
