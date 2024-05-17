package com.swyp.boardpick.service.implement;

import com.swyp.boardpick.domain.BoardGame;
import com.swyp.boardpick.domain.Category;
import com.swyp.boardpick.dto.response.BoardGameDto;
import com.swyp.boardpick.repository.BoardGameCategoryRepository;
import com.swyp.boardpick.repository.BoardGameRepository;
import com.swyp.boardpick.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardGameService {
    private final BoardGameRepository boardGameRepository;
    private final BoardGameCategoryRepository boardGameCategoryRepository;
    private final CategoryRepository categoryRepository;

    public Optional<BoardGame> getBoardGameById(Long id) {
        return boardGameRepository.findById(id);
    }

    public List<BoardGameDto> getBoardGamesByCategory(String category, int page, int size) {
        Long categoryId = categoryRepository.findByType(category).getId();
        return boardGameCategoryRepository.findByCategory_Id(categoryId, PageRequest.of(page, size))
                .stream().map(boardGameCategory -> {
                    BoardGame boardGame = boardGameCategory.getBoardGame();
                    return new BoardGameDto(boardGame);
                }).toList();
    }
}
