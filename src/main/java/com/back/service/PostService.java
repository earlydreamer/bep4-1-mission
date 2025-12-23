package com.back.service;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public Post CreatePost(String title, Member author, String content){
        Post post = new Post(title, author, content);
        return postRepository.save(post);
    }

    public Optional<Post> findByPostId(int i) {
        return postRepository.findById(i);
    }

    public long count() {
        return postRepository.count();
    }

}
