package com.omkar.smartnotes.controller.api;

import com.omkar.smartnotes.dto.NoteRequest;
import com.omkar.smartnotes.entity.Note;
import com.omkar.smartnotes.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:5173")
public class NoteApiController {

    private final NoteService noteService;

    public NoteApiController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public Note createNote(@RequestBody NoteRequest request) {
        return noteService.createNote(request);
    }

    @PutMapping("/{id}")
    public Note updateNote(
            @PathVariable Long id,
            @RequestBody NoteRequest request
    ) {
        return noteService.updateNote(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}