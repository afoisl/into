package dw.into.controller;

import dw.into.model.*;
import dw.into.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mock")
public class MockController {

    @Autowired
    private MockService mockService;

    @PostMapping("/save-ticket/{userId}")
    public ResponseEntity<PurchasedMockTicket> saveTicket(@PathVariable String userId) {
        PurchasedMockTicket purchasedMockTicket = mockService.saveTicket(userId);
        return new ResponseEntity<>(purchasedMockTicket, HttpStatus.CREATED);
    }

    @GetMapping("/get-ticket/{userId}")
    public ResponseEntity<List<PurchasedMockTicket>> getTicketsByUserId(@PathVariable User user) {
        List<PurchasedMockTicket> purchasedMockTickets = mockService.getTicketsByUserId(user);
        return ResponseEntity.ok(purchasedMockTickets);
    }



    // 2. 특정 시험의 문제 목록 가져오기
    @GetMapping("/{mockId}/questions")
    public ResponseEntity<List<MockQuestion>> getMockQuestions(@PathVariable int mockId) {
        List<MockQuestion> questions = mockService.getMockQuestions(mockId);
        return ResponseEntity.ok(questions);
    }

    // 3. 사용자의 답안을 저장
    @PostMapping("/submit-answers/{mockId}/{userId}")
    public ResponseEntity<String> submitAnswers(@PathVariable int mockId, User userId,@RequestBody String answers) {
        Mock mock = mockService.getMockById(mockId);

        mockService.saveUserAnswers(userId, mock, answers);
        return ResponseEntity.ok("답안을 저장했습니다.");
    }
    // 4. 점수를 계산하여 저장
    @PostMapping("/calculate-score/{mockId}/{userId}")
    public ResponseEntity<Integer> calculateScore(@PathVariable int mockId, User user) {
        Mock mock = mockService.getMockById(mockId);

        int score = mockService.calculateScore(user, mock);
        return ResponseEntity.ok(score);
    }

    //     5. 점수를 기반으로 등급을 저장
    @PostMapping("/save-grade/{mockId}/{userId}/{score}")
    public ResponseEntity<String> saveGrade(
            @PathVariable int mockId,
            @PathVariable String userId,
            @PathVariable Integer score) {

        Mock mock = mockService.getMockById(mockId);

        if (score == null) {
            return ResponseEntity.badRequest().body("점수가 입력되지 않았습니다.");
        }

        try {
            mockService.saveGrade(userId, mock, score);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("등급이 저장되었습니다.");
    }

    // 6. 사용자의 시험 결과를 조회
    @GetMapping("/results/{mockId}/{userId}")
    public ResponseEntity<List<MockResult>> getUserResults(@PathVariable int mockId, User user) {
        List<MockResult> results = mockService.getUserResults(user);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/score/{userId}")
    public ResponseEntity<List<MockScore>> getUserMockScore(@PathVariable String userId) {
        List<MockScore> scores = mockService.getMockScoreByUser(userId);
        return ResponseEntity.ok(scores);
    }
}
