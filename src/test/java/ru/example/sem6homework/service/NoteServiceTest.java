package ru.example.sem6homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.example.sem6homework.model.Note;
import ru.example.sem6homework.repository.NoteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NoteServiceTest {
    @Test
    @DisplayName("noteCreateTest")
    public  void noteSaveTest() {
        // Предпосылки
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        // Вызов
        Note note1 = noteService.createNote(note);
        //System.out.println(note1.getId());
        // Провкрка
        assertEquals(note1, note);
        //System.out.println(note1.toString());
    }
    @Test
    @DisplayName("noteFindByIdTest")
    public  void noteFindByIdTest() {
        // Предпосылки
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);

        Note note = new Note();
        Note note1 = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        //List<Note> expectNotes = Collections.singletonList(note);
        //when(noteRepository.findAll()).thenReturn(expectNotes);
      //  when(noteRepository.findById(1L)).thenReturn(new Optional(note1));
         // Вызов
        Optional<Note> note2 = noteService.findById(1L);
        // Провкрка
      // assertEquals(expectNotes, actualNote);
        assertEquals(note, note1);

    }
}
