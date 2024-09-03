package dw.into.controller;

import dw.into.dto.ReplyDto;
import dw.into.model.Reply;
import dw.into.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/save")
    public ResponseEntity<Reply> saveReply(@RequestBody Reply reply) {
        return new ResponseEntity<>(replyService.saveReply(reply),
                HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Reply>> getAllReply() {
        return new ResponseEntity<>(replyService.getAllReply(),
                HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<ReplyDto>> getReplyAllByDto() {
        return new ResponseEntity<>(replyService.getReplyAllByDto(),
                HttpStatus.OK);
    }
}
