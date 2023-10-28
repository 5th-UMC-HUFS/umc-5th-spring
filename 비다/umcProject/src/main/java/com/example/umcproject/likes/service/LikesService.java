package com.example.umcproject.likes.service;

import com.example.umcproject.likes.entity.Likes;
import com.example.umcproject.likes.repository.LikesRepository;
import com.example.umcproject.member.entity.Member;
import com.example.umcproject.member.exception.NoMemberException;
import com.example.umcproject.member.repository.MemberRepository;
import com.example.umcproject.post.entity.Post;
import com.example.umcproject.post.exception.NoPostException;
import com.example.umcproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository ;
    private final MemberRepository memberRepository ;
    private final PostRepository postRepository ;

    /*
    좋아요 버튼 (좋아요/좋아요 취소)
     */
    @Transactional
    public Long pressLikeButton(Long memberId , Long postId, LocalDateTime createdAt){

        // 게시물 존재 확인 (누르기 직전 게시물 삭제될 수 있음)
        Member member = memberRepository.find(memberId);
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new NoPostException("해당 게시물이 없거나 삭제되었습니다"));

        // 좋아요 또는 좋아요 취소
        Optional<Likes> likes = likesRepository.findByLikerAndTargetPost(member, post);

        if(likes.isPresent()){
            Likes findLikes =  likes.get() ;

            findLikes.cancelLike(); // 연관관계 메서드
            likesRepository.delete(findLikes); // likes 삭제 메서드
            return findLikes.getId();
        }else{
            Likes createdLikes = Likes.createLikes(member, post, createdAt);
            likesRepository.save(createdLikes);
            return createdLikes.getId() ;
        }
    } // pressLikeButton


    @Transactional(readOnly = true)
    public List<Post> getTargetPosts(Long memberId){

        Member author = memberRepository.find(memberId) ;
        if(author==null){
            throw new  NoMemberException("해당 회원이 없거나 삭제되었습니다");
        }

        List<Likes> likesOfMember = likesRepository.findByLiker(author);
        List<Post> targetPosts = new ArrayList<>();
        Likes likes ;

        Iterator iterator = likesOfMember.iterator() ;
        while (iterator.hasNext()){
            likes = (Likes) iterator.next();
            targetPosts.add(likes.getTargetPost()) ;
        }
        return targetPosts ;
    }

    @Transactional(readOnly = true)
    public List<Member> getLikers(Long postId){

        // 모건님 코드 참고버전 ㅎㅎ...
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new NoPostException("해당 게시물이 없거나 삭제되었습니다"));

        List<Likes> likesOfPost = likesRepository.findByTargetPost(post);

        List<Member> likers =  likesOfPost.stream()
                .map(Likes::getLiker)
                .collect((Collectors.toList())) ;

        return likers ;
    } // likers

}
