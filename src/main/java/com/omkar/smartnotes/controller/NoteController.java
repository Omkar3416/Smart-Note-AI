package com.omkar.smartnotes.controller;

import com.omkar.smartnotes.dto.NoteRequest;
import com.omkar.smartnotes.entity.Note;
import com.omkar.smartnotes.service.AiService;
import com.omkar.smartnotes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    private final NoteService noteService;
    private final AiService aiService;

    public NoteController(NoteService noteService, AiService aiService) {
        this.noteService = noteService;
        this.aiService = aiService;
    }

    @GetMapping("/notes")
    public String getNotes(Model model) {

        model.addAttribute(
                "notes",
                noteService.getAllNotes()
        );

        model.addAttribute(
                "noteRequest",
                new NoteRequest()
        );

        return "notes";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {

        noteService.deleteNote(id);

        return "redirect:/notes";
    }

    @GetMapping("/notes/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "note",
                noteService.getNoteById(id)
        );

        return "edit-note";
    }

    @PostMapping("/notes/update/{id}")
    public String updateNote(
            @PathVariable Long id,
            @Valid NoteRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "note",
                    noteService.getNoteById(id)
            );

            return "edit-note";
        }

        noteService.updateNote(id, request);

        return "redirect:/notes";
    }

    @PostMapping("/notes")
    public String createNote(
            @Valid NoteRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "notes",
                    noteService.getAllNotes()
            );

            model.addAttribute(
                    "noteRequest",
                    request
            );

            return "notes";
        }

        noteService.createNote(request);

        return "redirect:/notes";
    }

    // ✅ AI SUMMARY FEATURE (NEW)
    @GetMapping("/notes/summary/{id}")
    public String summarizeNote(@PathVariable Long id, Model model) {

        Note note = noteService.getNoteById(id);

        model.addAttribute("note", note);

        try {
            String summary = aiService.summarize(note.getContent());
            model.addAttribute("summary", summary);
        } catch (Exception e) {
            model.addAttribute("summary", "AI Error: " + e.getMessage());
            e.printStackTrace();
        }

        return "note-summary";
    }
}