package com.back.service;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private PostRepository postRepository;
    public Post CreatePost(String title, Member author, String content){
        Post post = new Post(title, author, content);
        return postRepository.save(post);
    }
}
