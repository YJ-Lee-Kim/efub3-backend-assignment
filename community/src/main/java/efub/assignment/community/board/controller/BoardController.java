package efub.assignment.community.board.controller;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardCreateRequestDto;
import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.board.dto.BoardUpdateRequestDto;
import efub.assignment.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponseDto boardAdd(@RequestBody BoardCreateRequestDto requestDto){
        Board board = boardService.create(requestDto);
        return new BoardResponseDto(board);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardFind(@PathVariable Long boardId){
        Board board = boardService.read(boardId);
        return new BoardResponseDto(board);
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardModifiy(@PathVariable Long boardId, @RequestBody BoardUpdateRequestDto requestDto){
        Board board = boardService.update(boardId, requestDto);
        return new BoardResponseDto(board);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String boardRemove(@PathVariable Long boardId){
        boardService.delete(boardId);
        return "성공적으로 삭제되었습니다.";
    }
}