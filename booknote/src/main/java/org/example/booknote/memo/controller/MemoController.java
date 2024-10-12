package org.example.booknote.memo.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.memo.controller.port.MemoService;
import org.example.booknote.memo.controller.response.MemoResponse;
import org.example.booknote.memo.domain.MemoCreate;
import org.example.booknote.memo.domain.MemoUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
@Builder
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponse> getById(@PathVariable long id){
        return ResponseEntity
                .ok()
                .body(MemoResponse.from(memoService.getMemoById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponse> update(@PathVariable long id, @RequestBody MemoUpdate memoUpdate){
        return ResponseEntity
                .ok()
                .body(MemoResponse.from(memoService.update(id,memoUpdate)));
    }

    @PostMapping
    public ResponseEntity<MemoResponse> create(@RequestBody MemoCreate memoCreate){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MemoResponse.from(memoService.create(memoCreate)));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<List<MemoResponse>> getByBookId(@PathVariable long id){
        return ResponseEntity
                .ok()
                .body(memoService.getMemosByBookId(id).stream().map(MemoResponse::from).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemoResponse> delete(@PathVariable long id){
        return ResponseEntity
                .ok()
                .body(MemoResponse.from(memoService.delete(id)));
    }
}
