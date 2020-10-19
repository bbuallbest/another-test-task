package com.testtask.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "email")
public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private Sex sex;
    private LocalDate creationDate;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
}

