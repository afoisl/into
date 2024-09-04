package dw.into.controller;

import dw.into.model.Notice;
import dw.into.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public List<Notice> getAllNotices() {
        return noticeService.getAllNotices();
    }

    @GetMapping("/{id}")
    public Notice getNoticeById(@PathVariable Long id) {
        return noticeService.getNoticeById(id);
    }

    @PostMapping
    public String createNotice(@RequestBody Notice notice) {
        return noticeService.saveNotice(notice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable Long id, @RequestBody Notice updatedNotice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();

        if (!currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        Notice updated = noticeService.updateNotice(id, updatedNotice);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().build();
    }
}
