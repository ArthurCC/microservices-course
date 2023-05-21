package fr.camposcosta.fraudapi.service;

import fr.camposcosta.fraudapi.entity.FraudCheckHistory;
import fr.camposcosta.fraudapi.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId) {

        // save in db
        fraudCheckHistoryRepository.save(
            new FraudCheckHistory(
                    customerId,
                    false,
                    LocalDateTime.now()
            )
        );

        // We mock fraud logic and always assume customer is clear
        return false;
    }
}
