package com.sparta.weekendhomework.service;

import com.sparta.weekendhomework.models.Memo;
import com.sparta.weekendhomework.models.MemoRepository;
import com.sparta.weekendhomework.models.MemoRequestDto;
import com.sparta.weekendhomework.models.MemoSearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public boolean check(Long id, MemoSearchRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        String memoPassword = memo.getPassword();
        return memoPassword.equals(requestDto.getPassword());
    }

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        memo.update(requestDto);
        return memo.getId();
    }
}