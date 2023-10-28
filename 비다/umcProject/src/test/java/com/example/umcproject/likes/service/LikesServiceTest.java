package com.example.umcproject.likes.service;

import com.example.umcproject.likes.entity.Likes;
import com.example.umcproject.likes.repository.LikesRepository;
import com.example.umcproject.member.entity.Member;
import com.example.umcproject.member.repository.MemberRepository;
import com.example.umcproject.post.entity.Post;
import com.example.umcproject.post.exception.NoPostException;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LikesServiceTest {

    @Autowired  LikesService likesService ;
    @Autowired  LikesRepository likesRepository ;
    @Autowired  MemberRepository memberRepository ;
    @Autowired  EntityManager em ;


    @Test
    public void 좋아요(){ // ok
        // given
        Member member = Member.createMember("hm", "1111", LocalDateTime.now()) ;
        //em.persist(member);  // post쪽에 cascade = CascadeType.ALL 해줌
        Post post = Post.createPost(member, "테스트", "좋아요테스트") ;
        em.persist(post);

        // when
        Long likesId = likesService.pressLikeButton(member.getId(), post.getId(), LocalDateTime.now());

        // then
        Likes likes = likesRepository.findById(likesId).get() ;

        Assert.assertEquals(member.getId() , likes.getLiker().getId() );
        Assert.assertEquals(post.getId() , likes.getTargetPost().getId() ); // likes 객체 생성 테스트
        Assertions.assertThat(member.getLikes().contains(likes)) ;
        Assertions.assertThat(post.getLikes().contains(likes)) ;// 연관관계 테스트

    }

    @Test
    public void 좋아요취소(){ // ok

        // given
        Member member = Member.createMember("hm", "1111", LocalDateTime.now()) ;
        Post post = Post.createPost(member, "테스트", "좋아요테스트") ;
        em.persist(post);

        Long likes1 = likesService.pressLikeButton(member.getId(), post.getId(), LocalDateTime.now()); // '좋아요'
        Optional<Likes> likesOptional1 = likesRepository.findById(likes1);
        Likes likes = likesOptional1.get();

        // when
        Long likes2 = likesService.pressLikeButton(member.getId(), post.getId(), LocalDateTime.now()); // 버튼 한번더
        Optional<Likes> likesOptional2 = likesRepository.findById(likes2);

        // then

        Assertions.assertThat(!likesOptional2.isPresent()) ; // likes 객체 컨텍스트에서 제거
        Assertions.assertThat(!member.getLikes().contains(likes));
        Assertions.assertThat(!post.getLikes().contains(likes)); // 연관관계 메서드
        
    }

    @Test(expected = NoPostException.class)
    public void 게시물_없음_예외(){ // ok

        // given
        Member member = Member.createMember("hm", "1111", LocalDateTime.now()) ;
        Post post = Post.createPost(member, "테스트", "좋아요테스트") ;
        em.persist(post);

        Long postId = post.getId() ;

        // when
        em.remove(post);
        likesService.pressLikeButton(member.getId(), postId, LocalDateTime.now());

        // then
        Assertions.fail("게시글 없음 예외가 발생해야한다") ;
    }

    @Test
    public void 게시글당_좋아요_조회(){

        // given
        Member author = Member.createMember("author", "1111", LocalDateTime.now()) ;
        Member liker1 = Member.createMember("liker1", "1111", LocalDateTime.now()) ;
        Member liker2 = Member.createMember("liker2", "1111", LocalDateTime.now()) ;

        Post post = Post.createPost(author, "테스트", "좋아요테스트") ;
        em.persist(post);
        em.persist(liker1);
        em.persist(liker2);

        Long postId = post.getId() ;
        likesService.pressLikeButton(liker1.getId(), postId, LocalDateTime.now());
        likesService.pressLikeButton(liker2.getId(), postId, LocalDateTime.now());
        // 문제 : post.getId() --> 2가나옴... 오류 원인  : 메서드 자체에서 매개변수 순서를 잘못줌 (포스트id, 멤버 id)

        // when
        List<Member> likers = likesService.getLikers(postId);

        // then
        Assertions.assertThat(likers.contains(liker1)) ;
        Assertions.assertThat(likers.contains(liker2)) ;

    } // 게시글당_좋아요_조회
    
    @Test
    public void 멤버당_좋아요_조회(){

        // given
        Member author = Member.createMember("author", "1111", LocalDateTime.now()) ;
        Member liker = Member.createMember("liker1", "1111", LocalDateTime.now()) ;

        Post post1 = Post.createPost(author, "테스트1", "좋아요테스트") ;
        Post post2 = Post.createPost(author, "테스트2", "좋아요테스트") ;
        Post post3 = Post.createPost(author, "테스트3", "좋아요테스트") ;

        em.persist(post1);
        em.persist(post2);
        em.persist(post3);
        em.persist(liker);

        Long likerId = liker.getId() ;
        likesService.pressLikeButton(likerId, post1.getId(), LocalDateTime.now());
        likesService.pressLikeButton(likerId, post2.getId(), LocalDateTime.now());
        likesService.pressLikeButton(likerId, post3.getId() , LocalDateTime.now());

        // when
        List<Post> likers = likesService.getTargetPosts(likerId);

        // then
        Assertions.assertThat(likers.contains(post1)) ;
        Assertions.assertThat(likers.contains(post2)) ;
        Assertions.assertThat(likers.contains(post3)) ;

    }
}