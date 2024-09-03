package dw.into.service;

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

    public QnA saveQna(QnA qnA) {
        return qnaRepository.save(qnA);
    }

    public void deleteQna(Long id) {
        qnaRepository.deleteById(id);
    }
}
