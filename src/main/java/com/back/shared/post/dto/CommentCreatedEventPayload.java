package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 DTO
 * Comment와 관련된 이벤트에서 사용할 DTO
 *
 */
@AllArgsConstructor
@Getter
public class CommentCreatedEventPayload {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final int postId;
    private final int authorId;

    // 이 부분이 필요한가? (Post 행위는 서비스 레이어에서 일어나고 이벤트 핸들링의 동작과 직접적 관계 없다.
    // 핸들러가 건드리는 부분은 Member의 점수라서 작성자, 본문 정보는 사용되지 않는다.
    // 일단 의도가 있을 것 같아서 예제의 구조를 따라 작성
    private final String authorName;
    private final String content;

    public CommentCreatedEventPayload(Comment comment) {
        this(
                comment.getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getId(),
                comment.getAuthor().getId(),
                comment.getAuthor().getNickname(),
                comment.getContent()
        );
    }

}
