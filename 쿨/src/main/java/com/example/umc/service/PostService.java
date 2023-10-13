package com.example.umc.service;

import com.example.umc.entity.Post;
import com.example.umc.entity.PostRequest;
import com.example.umc.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostRequest postRequest){

        Post post = new Post();

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        postRepository.save(post);

        return post;

    }

    public Post getPost(Long Id){

        Optional<Post> postOptional = postRepository.findById(Id);

        return postOptional.get();
    }

    public Post updatePost(PostRequest postRequest, Long Id){

        Optional<Post> postOptional = postRepository.findById(Id);

        Post post = postOptional.get();

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        return postRepository.save(post);

    }

    public Post deletePost(Long Id){

        Optional<Post> post = postRepository.findById(Id);

        postRepository.delete(post.get());

        return post.get();

    }



}
