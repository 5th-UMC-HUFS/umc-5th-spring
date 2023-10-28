package com.example.umcproject.likes.controller;

import com.example.umcproject.likes.dto.LikesRequestDTO;
import com.example.umcproject.likes.service.LikesService;
import com.example.umcproject.member.entity.Member;
import com.example.umcproject.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    // 좋아요 버튼
    @PostMapping("likes/button")
    public String pressLikeButton(LikesRequestDTO dto){

        log.info("LikesController - pressLikeButton (POST)");


        Long memberId = dto.getMemberId() ;
        Long postId = dto.getPostId() ;
        LocalDateTime createdAt = LocalDateTime.now();

        likesService.pressLikeButton(memberId, postId, createdAt) ;

        return "redirect:post/postList" ; // 아직 안만듬 ...
    }

    // 멤버별 조회
    @GetMapping("likes/targetPosts")
    public String getTargetPosts(Long memberId, Model model){

        log.info("LikesController - getTargetPosts (GET)");

        List<Post> targetPosts = likesService.getTargetPosts(memberId);
        model.addAttribute("targetPosts", targetPosts) ;

        return "likes/postList" ;
    }

    // 게시글별 조회
    @GetMapping("likes/likers")
    public String getLikers(Long postId, Model model){

        log.info("LikesController - getLikers (GET)");

        List<Member> likers = likesService.getLikers(postId);
        model.addAttribute("likers", likers) ;

        return "likes/memberList" ;
    }


    // 게시글별 조회
}
