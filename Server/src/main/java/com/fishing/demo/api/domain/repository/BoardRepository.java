package com.fishing.demo.api.domain.repository;

import com.fishing.demo.api.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // Add custom query methods if needed...
}
