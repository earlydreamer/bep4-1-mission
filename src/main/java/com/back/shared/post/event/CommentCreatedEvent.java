package com.back.shared.post.event;

import com.back.shared.post.dto.CommentCreatedEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreatedEvent {
    private final CommentCreatedEventPayload commentCreatedEventPayload;
}
