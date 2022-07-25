package com.sparta.weekendhomework.controller;

import com.sparta.weekendhomework.models.Memo;
import com.sparta.weekendhomework.models.MemoRepository;
import com.sparta.weekendhomework.models.MemoRequestDto;
import com.sparta.weekendhomework.models.MemoSearchRequestDto;
import com.sparta.weekendhomework.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoRestController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    // 등록된 전체 게시글 조회
    @GetMapping("/api/memos")
    public List<Memo> getProducts() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    // 신규 게시글 등록
    @PostMapping("/api/memos")
    public Memo createProduct(@RequestBody MemoRequestDto requestDto) {
        Memo product = new Memo(requestDto);
        memoRepository.save(product);
        return product;
    }

    @GetMapping("/api/memos/{id}")
    public Memo findMemo(@PathVariable Long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        return memo;
    }


    @PostMapping("/api/password/{id}")
    public boolean checkMemo(@PathVariable Long id,@RequestBody MemoSearchRequestDto requestDto ){
        return memoService.check(id,requestDto);
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        memoRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoRequestDto requestDto){
        memoService.update(id,requestDto);
        return id;
    }
}
