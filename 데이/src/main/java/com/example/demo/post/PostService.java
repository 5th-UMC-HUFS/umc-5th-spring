package com.example.demo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostEntity createPost(PostDto dto) {
        PostEntity post = new PostEntity();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(LocalDateTime.now());

        UserEntity user = userRepository.findById((long) Math.toIntExact(dto.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        post.setUser(user);
        return postRepository.save(post);
    }

    public void deletePost(String title) {
        postRepository.deleteByTitle(title);
    }

    public List<PostDto> getPostsByTitle(String title) {
        return postRepository.findByTitle(title).stream()
                .map(post -> new PostDto(
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt().toString(),
                        post.getUser().getUserId()
                ))
                .collect(Collectors.toList());
    }

    public List<PostDto> putPostsByTitle(String title, PostDto updateData) {
        List<PostEntity> posts = postRepository.findByTitle(title);

        for (PostEntity post : posts) {
            post.setTitle(updateData.getTitle());
            post.setContent(updateData.getContent());
        }

        return postRepository.saveAll(posts).stream()
                .map(post -> new PostDto(
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt().toString(),
                        post.getUser().getUserId()
                ))
                .collect(Collectors.toList());
    }
}

