package com.pay.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@ToString(exclude = {"paymentList"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Column(length = 8, insertable = false)
    private String available;

    @Column(length = 32, nullable = false, unique = true)
    private String identify;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private String name;

    private Long capital;

    private Long income;

    @Column(insertable = false)
    private Long outcome;

    private Long fixedIncome;

    private String cycleIncome;

    @OneToMany(mappedBy = "user")
    private List<Payment> paymentList;

}
