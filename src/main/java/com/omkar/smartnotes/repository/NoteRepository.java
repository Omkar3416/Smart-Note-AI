package com.omkar.smartnotes.repository;

import com.omkar.smartnotes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}