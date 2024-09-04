package dw.into.service;

import dw.into.model.Notice;
import dw.into.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice getNoticeById(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("Notice not found"));
    }

    public String saveNotice(Notice notice) {
        Notice temp = noticeRepository.save(notice);
        if (temp.getNoticeId() > 0) {
            return "Success";
        }else {
            throw new RuntimeException("notice error");
        }
    }

    public Notice updateNotice(Long id, Notice updatedNotice) {
        Optional<Notice> existingNoticeOpt = noticeRepository.findById(id);
        if (existingNoticeOpt.isPresent()) {
            Notice existingNotice = existingNoticeOpt.get();
            existingNotice.setTitle(updatedNotice.getTitle());
            existingNotice.setContent(updatedNotice.getContent());
            existingNotice.setWriteDate(updatedNotice.getWriteDate());
            return noticeRepository.save(existingNotice);
        } else {
            throw new RuntimeException("공지사항을 찾을 수 없습니다.");
        }
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }}
