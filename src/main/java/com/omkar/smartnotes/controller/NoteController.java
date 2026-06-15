package com.omkar.smartnotes.controller;

import com.omkar.smartnotes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.omkar.smartnotes.dto.NoteRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;


@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
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
}