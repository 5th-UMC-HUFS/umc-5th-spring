package com.example.umcproject.post.repository;

import com.example.umcproject.member.entity.Member;
import com.example.umcproject.post.entity.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // jpa의 기본 repository 메서드 그대로 사용 (save나 find 같이 새로 이름 주지 않고 ! )
    // 왜 그럼 repository 필요 ? 여기에 내가 정의하는 메서드 추가해서 사용! (엥 근데 interface인데...기능 있나...? 테스트 해봐야겠다)



}
