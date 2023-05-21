package fr.camposcosta.fraudapi.repository;

import fr.camposcosta.fraudapi.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
