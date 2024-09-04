package dw.into.controller;

import dw.into.model.Notice;
import dw.into.model.QnA;
import dw.into.service.QnaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qna")
public class QnAController {
    private final QnaService qnaService;

    public QnAController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @GetMapping
    public List<QnA> getAllQnas() {
        return qnaService.getAllQnas();
    }

    @PostMapping
    public String createQna(@RequestBody QnA qnA) {
        return qnaService.saveQna(qnA);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        qnaService.deleteQna(id);
        return ResponseEntity.ok().build();
    }
}
