package com.omkar.smartnotes.tools;

import com.omkar.smartnotes.entity.Note;
import com.omkar.smartnotes.repository.NoteRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteTools {

    private final NoteRepository noteRepository;

    public NoteTools(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Tool(description = "Generate a title from note content")
    public String generateTitle(String content) {

        if (content == null || content.isBlank()) {
            return "Untitled Note";
        }

        return content.length() > 20
                ? content.substring(0, 20) + "..."
                : content;
    }

    @Tool(description = "Detect sentiment of note content")
    public String detectSentiment(String content) {

        String lower = content.toLowerCase();

        if (lower.contains("happy")) {
            return "Positive";
        }

        if (lower.contains("sad")) {
            return "Negative";
        }

        return "Neutral";
    }

    @Tool(description = "Get all notes from database")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Tool(description = "Search notes using keyword")
    public String searchNotes(String keyword) {

        List<Note> notes = noteRepository.findAll();

        return notes.stream()
                .filter(note ->
                        note.getTitle().toLowerCase().contains(keyword.toLowerCase())
                                ||
                                note.getContent().toLowerCase().contains(keyword.toLowerCase())
                )
                .map(note ->
                        "Title: " + note.getTitle()
                                + "\nContent: " + note.getContent()
                )
                .collect(Collectors.joining("\n\n"));
    }

    @Tool(description = "Get note content by note id")
    public String getNoteById(Long id) {

        return noteRepository.findById(id)
                .map(Note::getContent)
                .orElse("Note not found");
    }
}