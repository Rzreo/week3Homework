package com.sparta.weekendhomework.controller;

import com.sparta.weekendhomework.models.*;
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
    public ResponseDto<List<Memo>> getProducts() {
        List<Memo> memo = memoRepository.findAllByOrderByModifiedAtDesc();
        return new ResponseDto<>(true, memo,null);
    }

    // 신규 게시글 등록

    @PostMapping("/api/memos")
    public ResponseDto<Memo> createProduct(@RequestBody MemoRequestDto requestDto) {
        Memo product = new Memo(requestDto);
        memoRepository.save(product);
        return new ResponseDto<>(true, product, null);
    }


    @GetMapping("/api/memos/{id}")
    public ResponseDto<Memo> findMemo(@PathVariable Long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        return new ResponseDto<>(true, memo, null);
    }


    @PostMapping("/api/memos/{id}")
    public ResponseDto<Boolean> checkMemo(@PathVariable Long id, @RequestBody MemoSearchRequestDto requestDto ){
        boolean c = memoService.check(id,requestDto);
        return new ResponseDto<>(true,c,null);
    }


    @DeleteMapping("/api/memos/{id}")
    public ResponseDto<Boolean> deleteMemo(@PathVariable Long id){
        memoRepository.deleteById(id);
        return new ResponseDto<>(true,true,null);
    }


    @PutMapping("/api/memos/{id}")
    public ResponseDto<Memo> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        Memo memo = memoService.update(id, requestDto);
        return new ResponseDto<>(true, memo, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseDto<Object> handleE(Exception e){
        return new ResponseDto<>(false,null,e.getMessage());
    }
}
