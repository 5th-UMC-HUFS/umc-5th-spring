package com.example.umcproject.likes.repository;

import com.example.umcproject.likes.entity.Likes;
import com.example.umcproject.member.entity.Member;
import com.example.umcproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    // jpa의 기본 repository 메서드 그대로 사용 (save나 find 같이 새로 이름 주지 않고 ! )
    // 왜 그럼 repository 필요 ? 여기에 내가 정의하는 메서드 추가해서 사용!
    // (엥 근데 interface인데...기능 있나...? 테스트 해봐야겠다)

    /*
    PostRepository - interface에 관한 지적 정확합니다
    자바에선 원래는 안되는데 JPA가 구현체를 생성하여 주는겁니다.
     모든 메서드가 다 되는건 아니고 메서드명이 특정패턴을 지켜야합니다
     (findBy__,readBy__,getBy__ 등)->이를 JPA의 Query Creation 동작이라고 합니다
     */

    Optional<Likes> findByLikerAndTargetPost(Member liker, Post targetPost);

    List<Likes> findByTargetPost(Post targetPost) ; // 이거 그냥 post로 하면 오류 : 엔티티에 설정해준 필드명이랑 일치시켜줘야함

    List<Likes> findByLiker(Member liker);
}
