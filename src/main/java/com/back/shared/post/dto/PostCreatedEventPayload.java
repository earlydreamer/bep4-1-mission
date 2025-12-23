package com.back.shared.post.dto;

import com.back.boundedContext.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


/**
 * 게시글 DTO
 * 이 DTO의 역할은 '게시물 생성' 을 알리는 이벤트의 후속 동작(핸들링)에 사용할 값을 실어 보내는 것이다.
 * 이 DTO를 통해 이벤트리스너가 값을 받아 핸들링을 실행한다.
 * 엔티티 구조가 단순하고 아직 게시글 생성과 관련된 동작밖에 없기 때문에 이름을 뭉뚱그려도 상관없긴한데
 * 좀더 엄밀하게 동작에 맞춘 네이밍을 적용
 * PostDto -> PostCreatedPayload
 * Payload = 값을 실어나르는 일종의 컨테이너. 관용적으로 사용되는 네이밍
 */

@AllArgsConstructor
@Getter
public class PostCreatedEventPayload {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final int authorId;

    // 이 부분이 필요한가? (Post 행위는 서비스 레이어에서 일어나고 이벤트 핸들링의 동작과 직접적 관계 없다.
    // 핸들러가 건드리는 부분은 Member의 점수라서 작성자, 제목, 본문 정보는 사용되지 않는다.
    // 일단 의도가 있을 것 같아서 예제의 구조를 따라 작성
    private final String authorName;
    private final String title;
    private final String content;

    public PostCreatedEventPayload(Post post) {
        this(
                post.getId(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getTitle(),
                post.getContent()
        );
    }
}