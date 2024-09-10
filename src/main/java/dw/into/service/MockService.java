package dw.into.service;

import dw.into.model.*;
import dw.into.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MockService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockRepository mockRepository;

    @Autowired
    private MockTicketRepository mockTicketRepository;

    @Autowired
    private MockQuestionRepository mockQuestionRepository;

    @Autowired
    private MockResultRepository mockResultRepository;

    @Autowired
    private MockScoreRepository mockScoreRepository;

    @Autowired
    private MockGradeRepository mockGradeRepository;


    public Mock getMockById(int mockId) {
        return mockRepository.findById(mockId)
                .orElseThrow(() -> new RuntimeException("Mock not found with id: " + mockId));
    }

    @Transactional
    public boolean useTicket(User user, Mock mock) {
        Optional<MockTicket> optionalTicket = mockTicketRepository.findByUserAndMockAndIsUsedFalse(user, mock);
        if (optionalTicket.isPresent()) {
            MockTicket ticket = optionalTicket.get();
            ticket.setUsed(true);
            mockTicketRepository.save(ticket);
            return true;
        }
        return false;
    }

    public List<MockQuestion> getMockQuestions(int mockId) {
        // mockId를 이용해 Mock 객체를 가져옴
        Mock mock = mockRepository.findById(mockId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mock ID"));

        // Mock 객체에 연결된 문제 리스트 반환
        return mockQuestionRepository.findByMock(mock);
    }

    @Transactional
    public void saveUserAnswers(User user, Mock mock, Map<Long, String> answers) {
        MockResult mockResult = new MockResult();
        mockResult.setUser(user);
        mockResult.setMock(mock);
        mockResult.setUserAnswers(answers);  // Map으로 된 사용자의 답변을 설정합니다.
        mockResultRepository.save(mockResult);
    }

    @Transactional
    public int calculateScore(User user, Mock mock) {
        Optional<MockResult> optionalMockResult = mockResultRepository.findByUserAndMock(user, mock);
        if (optionalMockResult.isPresent()) {
            MockResult mockResult = optionalMockResult.get();
            List<MockQuestion> questions = mockQuestionRepository.findByMock(mock);
            int score = 0;
            for (MockQuestion question : questions) {
                String correctAnswer = question.getCorrectAnswer();
                String userAnswer = mockResult.getUserAnswers().get(question.getId());
                if (correctAnswer.equals(userAnswer)) {
                    score += 10; // 각 정답당 점수 부여 (예: 10점)
                }
            }

            MockScore mockScore = new MockScore();
            mockScore.setScore(score);
            mockScore.setUser(user);
            mockScore.setMock(mock);
            mockScoreRepository.save(mockScore);

            return score;
        }
        return 0; // MockResult가 없을 경우 0점 반환
    }

    @Transactional
    public void saveGrade(User user, Mock mock, int score) {
        Optional<MockGrade> optionalGrade = mockGradeRepository.findByHighLimitGreaterThanEqualAndLowLimitLessThanEqual(score, score);
        if (optionalGrade.isPresent()) {
            MockGrade mockGrade = optionalGrade.get();
            mockGrade.setUser(user);
            mockGrade.setMock(mock);
            mockGradeRepository.save(mockGrade);

            // 사용자 정보 업데이트
            user.setGrade(mockGrade.getMockGradeName());
            userRepository.save(user);
        }
    }

    public List<MockResult> getUserResults(User user) {
        return mockResultRepository.findByUser(user);
    }
}