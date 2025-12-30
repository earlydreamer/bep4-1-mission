package com.back.shared.cash.dto;

import com.back.boundedContext.cash.domain.Wallet;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(
        onConstructor_ = @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
)
@Getter
public class WalletDto {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final Long holderId;
    private final String holderName;
    private final long balance;

    public WalletDto(Wallet wallet) {
        this(
                wallet.getId(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt(),
                wallet.getHolder().getId(),
                wallet.getHolder().getUsername(),
                wallet.getBalance()
        );
    }
}