package com.back.service;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.entity.enums.ScoreEnum;
import com.back.repository.MemberRepository;
import com.back.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Post CreatePost(String title, Member author, String content){
        Post post = new Post(title, author, content);
        author.increasePoint(ScoreEnum.POST_CREATE.getScore());
        memberRepository.save(author);
        postRepository.save(post);
        return post;

    }

    public Optional<Post> findByPostId(int i) {
        return postRepository.findById(i);
    }

    public long count() {
        return postRepository.count();
    }

}
