package com.pay.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Column(length = 8, insertable = false)
    private String available;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(length = 16, nullable = false)
    private String kind;

    @Column(insertable = false, updatable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long price;

    @Column(length = 16)
    private String method;

}
