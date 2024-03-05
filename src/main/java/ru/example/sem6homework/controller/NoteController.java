package ru.example.sem6homework.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.sem6homework.exception.ResourceNotFoundException;
import ru.example.sem6homework.model.Note;
import ru.example.sem6homework.service.NoteService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    //Количество запросов на создание заметки
    private  final Counter addNoteCounter = Metrics.counter("add-note_counter");
     // Количество всех запросов
    private  final Counter numberOfRequest = Metrics.counter("number_request");
    private final NoteService service;

    /**
     * Запрос возвращает все записи
     * @return - возвращает список записей
     */
    @GetMapping
    public List<Note> findAllNotes(){
        numberOfRequest.increment();
        return  service.findAll();
    }

    /**
     * Запрос создает запись
     * @param note - сама запись
     * @return - возращает сделанную запись
     */
    @PostMapping
    public Note addNote(@RequestBody Note note)  {
        addNoteCounter.increment();
        numberOfRequest.increment();
        return service.createNote(note);
    }

    /**
     * Запрос на поиск записи по id
     * @param id - номер записи
     * @return - возвращает найденную запись по номеру, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает код ошибки 404 (ресурс не найден) на стороне клиента
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        numberOfRequest.increment();
        return ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
    }

    /**
     * Запрос на редактирование записи по номеру (id)
     * @param id - номер записи
     * @param note - новая запись
     * @return - возвращает отредактированную запись, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает иод ошибку 404 (ресурс не найден) на стороне клиента
     */
    @PutMapping("{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note) throws ResourceNotFoundException {
        numberOfRequest.increment();
        note.setId(id);
        ResponseEntity<Note> note1 = ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
        service.createNote(note);
        return ResponseEntity.ok(note);
    }

    /**
     * Запрос на удаление записи по id
     * @param id - номер записи
     * @return - если запись найдена, то возвращается код 200, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает код ошибки 404 (ресурс не найден) на стороне клиента
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        numberOfRequest.increment();
        ResponseEntity<Note> note = ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
