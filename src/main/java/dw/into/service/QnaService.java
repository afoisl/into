package dw.into.service;

import dw.into.model.Notice;
import dw.into.model.QnA;
import dw.into.repository.QnaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {
    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }

    public List<QnA> getAllQnas() {
        return qnaRepository.findAll();
    }




    public String saveQna(QnA qnA) {
        QnA temp = qnaRepository.save(qnA);
        if (temp.getQnAId() > 0) {
            return "Success";
        }else {
            throw new RuntimeException("qna error");
        }
    }

    public void deleteQna(Long id) {
        qnaRepository.deleteById(id);
    }
}
