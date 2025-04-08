package com.erfanlibrary.api.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter @Getter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;


    @Column(name = "title", nullable = false, unique = true)
    String title;

    @Column(name = "author", nullable = false)
    String author;

    @Column(name = "publisher", nullable = false)
    Integer pageCount;

    @Column(name = "available_count", nullable = false)
    Integer availableCount;

}
