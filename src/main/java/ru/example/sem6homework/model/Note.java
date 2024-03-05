package ru.example.sem6homework.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String title;
    @Column(nullable = false, length = 150)
    private String content;
    private LocalDateTime localDateTime = LocalDateTime.now();
}
