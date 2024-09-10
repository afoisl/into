package dw.into.service;

import dw.into.model.*;
import dw.into.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private  PurchasedMockTicketRepository purchasedMockTicketRepository;

    public Mock getMockById(int mockId) {
        return mockRepository.findById(mockId)
                .orElseThrow(() -> new RuntimeException("Mock not found with id: " + mockId));
    }

    public PurchasedMockTicket saveTicket(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        PurchasedMockTicket ticket = new PurchasedMockTicket();
        ticket.setUser(user);

        return purchasedMockTicketRepository.save(ticket);
    }

    public List<PurchasedMockTicket> getTicketsByUserId(User user) {
        return purchasedMockTicketRepository.findByUser(user);
    }

    public List<MockQuestion> getMockQuestions(int mockId) {
        // mockId를 이용해 Mock 객체를 가져옴
        Mock mock = mockRepository.findById(mockId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mock ID"));

        // Mock 객체에 연결된 문제 리스트 반환
        return mockQuestionRepository.findByMock(mock);
    }

    @Transactional
    public void saveUserAnswers(User user, Mock mock, String answers) {
        MockResult mockResult = new MockResult();
        mockResult.setUser(user);
        mockResult.setMock(mock);
        mockResult.setUserAnswers(answers);
        mockResultRepository.save(mockResult);
    }

    @Transactional
    public int calculateScore(User user, Mock mock) {
        Optional<MockResult> optionalMockResult = mockResultRepository.findByUserAndMock(user, mock);
        if (optionalMockResult.isPresent()) {
            MockResult mockResult = optionalMockResult.get();
            List<MockQuestion> questions = mockQuestionRepository.findByMock(mock);
            int score = 0;

            String userAnswersJson = mockResult.getUserAnswers();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode userAnswersNode;

            try {
                userAnswersNode = objectMapper.readTree(userAnswersJson);
            } catch (Exception e) {
                throw new RuntimeException("사용자 답안 JSON 파싱 실패", e);
            }

            for (MockQuestion question : questions) {
                String correctAnswer = question.getCorrectAnswer();
                String questionId = String.valueOf(question.getId());

                if (userAnswersNode.has(questionId)) {
                    String userAnswer = userAnswersNode.get(questionId).asText();
                    if (correctAnswer.equals(userAnswer)) {
                        score += 90;
                    }
                }
            }

            MockScore mockScore = new MockScore();
            mockScore.setScore(score);
            mockScore.setUser(user);
            mockScore.setMock(mock);
            mockScoreRepository.save(mockScore);

            return score;
        }
        return 0;
    }

    @Transactional
    public void saveGrade(String userId, Mock mock, int score) {
        Optional<MockGrade> optionalGrade = mockGradeRepository.findByHighLimitGreaterThanEqualAndLowLimitLessThanEqual(score, score);
        if (optionalGrade.isPresent()) {
            MockGrade mockGrade = optionalGrade.get();

            // userId로 기존 User 조회
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

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

    public List<MockScore>getMockScoreByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mockScoreRepository.findByUser(user);
    }
}