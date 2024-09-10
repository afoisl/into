package dw.into.controller;

import dw.into.model.*;
import dw.into.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mock")
public class MockController {

    @Autowired
    private MockService mockService;

    // 1. 티켓을 사용하여 시험에 입장
    @PostMapping("/enter/{mockId}")
    public ResponseEntity<String> enterMock(@PathVariable int mockId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        // mockId를 사용해 Mock 객체를 가져옴
        Mock mock = mockService.getMockById(mockId);

        if (mockService.useTicket(user, mock)) {
            return ResponseEntity.ok("티켓을 사용하여 시험에 입장했습니다.");
        } else {
            return ResponseEntity.badRequest().body("사용 가능한 티켓이 없습니다.");
        }
    }

    // 2. 특정 시험의 문제 목록 가져오기
    @GetMapping("/{mockId}/questions")
    public ResponseEntity<List<MockQuestion>> getMockQuestions(@PathVariable int mockId) {
        List<MockQuestion> questions = mockService.getMockQuestions(mockId);
        return ResponseEntity.ok(questions);
    }

    // 3. 사용자의 답안을 저장
    @PostMapping("/{mockId}/submit-answers")
    public ResponseEntity<String> submitAnswers(@PathVariable int mockId, @RequestBody Map<Long, String> answers, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Mock mock = mockService.getMockById(mockId);

        mockService.saveUserAnswers(user, mock, answers);
        return ResponseEntity.ok("답안을 저장했습니다.");
    }
    // 4. 점수를 계산하여 저장
    @PostMapping("/{mockId}/calculate-score")
    public ResponseEntity<Integer> calculateScore(@PathVariable int mockId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Mock mock = mockService.getMockById(mockId);

        int score = mockService.calculateScore(user, mock);
        return ResponseEntity.ok(score);
    }

    // 5. 점수를 기반으로 등급을 저장
    @PostMapping("/{mockId}/save-grade")
    public ResponseEntity<String> saveGrade(@PathVariable int mockId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Mock mock = mockService.getMockById(mockId);

        int score = mockService.calculateScore(user, mock);  // 점수를 계산
        mockService.saveGrade(user, mock, score);  // 점수를 기반으로 등급을 저장

        return ResponseEntity.ok("등급이 저장되었습니다.");
    }

    // 6. 사용자의 시험 결과를 조회
    @GetMapping("/results")
    public ResponseEntity<List<MockResult>> getUserResults(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<MockResult> results = mockService.getUserResults(user);

        return ResponseEntity.ok(results);
    }
}
