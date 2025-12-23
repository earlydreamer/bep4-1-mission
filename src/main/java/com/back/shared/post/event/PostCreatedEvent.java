package com.back.shared.post.event;

import com.back.shared.post.dto.PostCreatedEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 이벤트 클래스
 * 이벤트는 이벤트의 후속동작 처리에 필요한 정보를 답는다.
 */
@Getter
@AllArgsConstructor
public class PostCreatedEvent {
    private final PostCreatedEventPayload post;
}
