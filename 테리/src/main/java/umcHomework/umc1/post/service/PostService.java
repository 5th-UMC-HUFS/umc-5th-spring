package umcHomework.umc1.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umcHomework.umc1.member.entity.Member;
import umcHomework.umc1.member.repository.MemberRepository;
import umcHomework.umc1.post.dto.PostRequestDto;
import umcHomework.umc1.post.dto.PostResponseDto;
import umcHomework.umc1.post.entity.Post;
import umcHomework.umc1.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createPost(PostRequestDto postRequestDto){
        Member member = memberRepository.findById(postRequestDto.getMember().getId())
                .orElseThrow(() -> new RuntimeException("회원 정보 없음"));
        Post post = Post.builder()
                .member(postRequestDto.getMember())
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .build();
        postRepository.save(post);
    }

    @Transactional
    public List<PostResponseDto> getPost(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 정보 없음"));
        List<Post> posts = postRepository.findByMember(member.getId());
        List<PostResponseDto> postResponseDtos = posts.stream()
                .map(PostResponseDto::of)
                .collect(Collectors.toList());
        return postResponseDtos;
    }

    @Transactional
    public void updatePost(PostRequestDto postRequestDto, Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없음"));
        post.updatePost(postRequestDto);
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("헤당 게시글이 없음"));
        postRepository.delete(post);
    }

}
