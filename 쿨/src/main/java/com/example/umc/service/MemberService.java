package com.example.umc.service;

import com.example.umc.config.BaseException;
import com.example.umc.model.dto.UserRequest;
import com.example.umc.repository.MemberRepository;
import com.example.umc.model.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.umc.config.BaseResponseStatus.*;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    @Transactional
    public Member createUser(UserRequest userRequest) {

        Member member = Member.builder()
                .name(userRequest.getName())
                .nickname(userRequest.getNickname())
                .phonenumber(userRequest.getPhonenumber())
                .birthday(userRequest.getBirthday())
                .build();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member getUser(Long id) throws BaseException {
        try{

            Optional<Member> memberOptional = memberRepository.findById(id);

            if (memberOptional.isPresent()){
                return memberOptional.get();
            } else {
                throw new BaseException(NO_MEMBER);
            }
        } catch (Exception exception) {
            throw new BaseException(INVALID_JWT);
        }
    }

    @Transactional
    public Member updateUser(UserRequest userRequest, Long id) throws BaseException {
        try{

            Optional<Member> userOptional = memberRepository.findById(id);

            if (userOptional.isPresent()) {

                Member member = userOptional.get();

                member.setName(userRequest.getName());
                member.setNickname(userRequest.getNickname());
                member.setBirthday(member.getBirthday());
                member.setPhonenumber(member.getPhonenumber());

                return memberRepository.save(member);
            } else {
                throw new BaseException(NO_MEMBER);
            }
        } catch (Exception exception) {
            throw new BaseException(INVALID_JWT);
        }
    }

    @Transactional
    public void delete(Long id) throws BaseException {
        try{
            Optional<Member> memberOptional = memberRepository.findById(id);

            if(memberOptional.isPresent()){
              memberRepository.delete(memberOptional.get());
            } else {
                throw new BaseException(NO_MEMBER);
            }
        } catch (Exception exception) {
            throw new BaseException(INVALID_JWT);
        }
    }

}
