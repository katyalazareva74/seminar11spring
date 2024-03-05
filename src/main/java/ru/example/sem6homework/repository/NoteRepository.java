package ru.example.sem6homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.sem6homework.model.Note;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Метод поиска записи по id
     * @param id - номер записи
     * @return возвращает найденную запись
     */
    Optional<Note> findById(Long id);
}
