package com.omkar.smartnotes.service;

import com.omkar.smartnotes.entity.Note;

import java.util.List;

import com.omkar.smartnotes.dto.NoteRequest;

public interface NoteService {

    Note createNote(NoteRequest request);

    List<Note> getAllNotes();

    void deleteNote(Long id);

    Note getNoteById(Long id);

    Note updateNote(Long id, NoteRequest request);

}