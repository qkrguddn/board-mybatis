package phw.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phw.board.domain.dto.BoardDto;
import phw.board.mapper.BoardMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<BoardDto> getList() {
        return boardMapper.getList();
    }

    public void saveBoard(BoardDto boardDto) {
        boardMapper.saveBoard(boardDto);
    }


    public BoardDto getBoard(Long boardId) {
        BoardDto boardDto = boardMapper.getBoard(boardId);
        return boardDto;
    }

    public void deleteBoard(Long id) {
        boardMapper.deleteBoard(id);
    }

    public void updateBoard(Long boardId, BoardDto boardDto) {
        BoardDto dto = boardMapper.getBoard(boardId);
        dto.updateBoard(boardDto.getTitle(), boardDto.getContent());
        boardMapper.updateBoard(boardId, boardDto);
    }
}