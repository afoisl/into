package dw.into.service;

import dw.into.model.QnA;
import dw.into.repository.QnaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QnaService {
    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }

    public Optional<QnA> getQnAById(Long qnaId) {
        return Optional.ofNullable(qnaRepository.findById(qnaId)
                .orElseThrow(() -> new RuntimeException("QnA not found with ID: " + qnaId)));
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
