package dev.practice.gift.infrastructure.gift;

import dev.practice.gift.domain.gift.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Long> {
}
