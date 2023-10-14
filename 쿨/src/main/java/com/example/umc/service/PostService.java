package com.example.umc.service;

import com.example.umc.config.BaseException;
import com.example.umc.model.entity.Member;
import com.example.umc.model.entity.Post;
import com.example.umc.model.dto.PostRequest;
import com.example.umc.repository.MemberRepository;
import com.example.umc.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.umc.config.BaseResponseStatus.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Post createPost(PostRequest postRequest){

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post getPost(Long Id) throws BaseException {
        try{

            Optional<Post> postOptional = postRepository.findById(Id);

            if(postOptional.isPresent()) {
                return postOptional.get();
            } else {
                throw new BaseException(NO_POST);
            }
        } catch (Exception e) {
            throw new BaseException(INVALID_JWT);
        }
    }

    @Transactional
    public Post updatePost(PostRequest postRequest, Long Id) throws BaseException {
        try{
            Optional<Post> postOptional = postRepository.findById(Id);

            if (postOptional.isPresent()){
                Post post = postOptional.get();

                post.setTitle(postRequest.getTitle());
                post.setContent(postRequest.getContent());

                return postRepository.save(post);
            } else{
                throw new BaseException(NO_POST);
            }
        } catch (Exception e){
            throw new BaseException(INVALID_JWT);
        }
    }

    public void deletePost(Long Id) throws BaseException {
        try{
            Optional<Post> postoptional = postRepository.findById(Id);

            if (postoptional.isPresent()) {
                postRepository.delete(postoptional.get());
            } else {
                throw new BaseException(NO_POST);
            }
        } catch (Exception e) {
            throw new BaseException(INVALID_JWT);
        }
    }

    public void likePost(Long Id, Long pId) throws BaseException {
        try{
            Optional<Member> memberOptional = memberRepository.findById(Id);
            Optional<Post> postOptional = postRepository.findById(pId);
            if (postOptional.isPresent() && memberOptional.isPresent()){
                Post post = postOptional.get();
                List<Long> likes = post.getWho_likes();
                if(likes.contains(Id)){
                    likes.remove(Id);
                    post.setPost_like(post.getPost_like()-1);
                    postRepository.save(post);
                } else {
                    likes.add(Id);
                    post.setPost_like(post.getPost_like()+1);
                    postRepository.save(post);
                }
            } else{
                //사실 케이스를 나눠서 포스트랑, 멤버에 대해 오류처리를 해야하는데 간단하게 하기위해 그냥 하나로 통일
                throw new BaseException(WRONG_ID);
            }
        } catch (Exception e) {
            throw new BaseException(INVALID_JWT);
        }
    }



}
