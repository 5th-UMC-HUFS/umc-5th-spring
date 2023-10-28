package umcHomework.umc1.PostLikes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umcHomework.umc1.PostLikes.dto.PostLikesRequestDto;
import umcHomework.umc1.PostLikes.entity.PostLikes;
import umcHomework.umc1.PostLikes.repository.PostLikesRepository;
import umcHomework.umc1.member.entity.Member;
import umcHomework.umc1.member.repository.MemberRepository;
import umcHomework.umc1.post.entity.Post;
import umcHomework.umc1.post.repository.PostRepository;
import umcHomework.umc1.post.service.PostService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikesService {
    private final PostLikesRepository postLikesRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    @Transactional
    public void createPostLikes(PostLikesRequestDto postLikesRequestDto){
        Member member = memberRepository.findById(postLikesRequestDto.getMember().getId())
                .orElseThrow(() -> new RuntimeException("can not find memberId"));
        Post post = postRepository.findById(postLikesRequestDto.getPost().getId())
                .orElseThrow(() -> new RuntimeException("can not find postId"));
        //이미 좋아요 했으면 에러 반환
        if(postLikesRepository.findByMemberLikesAndPostLikes(member.getId(), post.getId()).isPresent()){
            throw new RuntimeException();
        }

        PostLikes postLikes = PostLikes.builder()
                .memberLikes(postLikesRequestDto.getMember())
                .postLikes(postLikesRequestDto.getPost())
                .build();
        postLikesRepository.save(postLikes);

        updatePostLikesCount(post.getId(), 1);
    }

    @Transactional
    public void deletePostLikes(PostLikesRequestDto postLikesRequestDto){
        Member member = memberRepository.findById(postLikesRequestDto.getMember().getId())
                .orElseThrow(() -> new RuntimeException("can not find memberId"));
        Post post = postRepository.findById(postLikesRequestDto.getPost().getId())
                .orElseThrow(() -> new RuntimeException("can not find postId"));
        PostLikes postLikes = postLikesRepository.findByMemberLikesAndPostLikes(member.getId(), post.getId())
                .orElseThrow(() -> new RuntimeException("can not find postLikes"));
        postLikesRepository.delete(postLikes);

        updatePostLikesCount(post.getId(), -1);

    }

    public void updatePostLikesCount(Long postId, int plusOrMinus){
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()){
            throw new RuntimeException("can not find post");
        }

        Post post = postOptional.get();
        post.setCountLikes(post.getCountLikes() + plusOrMinus);
        postRepository.save(post);
    }
}
