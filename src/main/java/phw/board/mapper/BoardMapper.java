package phw.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import phw.board.domain.dto.BoardDto;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto> getList();

    BoardDto getBoard(Long boardId);

    void saveBoard(BoardDto boardDto);

    void deleteBoard(Long id);

    void updateBoard(@Param("boardId") Long boardId, @Param("boardDto") BoardDto boardDto);
}
