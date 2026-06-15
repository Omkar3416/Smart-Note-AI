package com.omkar.smartnotes.service.impl;

import com.omkar.smartnotes.entity.Note;
import com.omkar.smartnotes.repository.NoteRepository;
import com.omkar.smartnotes.service.NoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.omkar.smartnotes.dto.NoteRequest;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note createNote(NoteRequest request) {

        Note note = new Note();

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());

        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note getNoteById(Long id) {

        return noteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Note not found"));
    }

    @Override
    public Note updateNote(Long id, NoteRequest request) {

        Note existingNote = getNoteById(id);

        existingNote.setTitle(request.getTitle());
        existingNote.setContent(request.getContent());

        existingNote.setUpdatedAt(LocalDateTime.now());

        return noteRepository.save(existingNote);
    }
}