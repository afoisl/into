package dw.into.service;

import dw.into.dto.ReplyDto;
import dw.into.model.Reply;
import dw.into.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public Reply saveReply(Reply reply) {
        reply.setReplyTime(LocalDate.now());
        return replyRepository.save(reply);
    }

    public List<Reply> getAllReply(){
        return replyRepository.findAll();
    }

    public List<ReplyDto> getReplyAllByDto() {
        List<Reply> replyList = replyRepository.findAll();
        List<ReplyDto> replyDtoList = new ArrayList<>();
        for (int i = 0; i < replyList.size(); i++) {
            ReplyDto replyDto = new ReplyDto();
            replyDtoList.add(replyDto.toReplyDtoFromReply(replyList.get(i)));
        }
        return replyDtoList;
    }

}
