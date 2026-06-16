import { useEffect, useState } from "react";
import ChatPanel from "../components/ChatPanel";

import {
    getAllNotes,
    createNote,
    updateNote,
    deleteNote
} from "../services/noteService";

import {
    summarizeNote
} from "../services/aiService";

function Dashboard() {

    const [notes, setNotes] = useState([]);

    const [selectedNote, setSelectedNote] = useState(null);

    const [summary, setSummary] = useState("");

    const [search, setSearch] = useState("");

    const [title, setTitle] = useState("");

    const [content, setContent] = useState("");

    const [editing, setEditing] = useState(false);

    useEffect(() => {
        loadNotes();
    }, []);

    async function loadNotes() {

        try {

            const data = await getAllNotes();

            setNotes(data);

        } catch (error) {

            console.error(error);
        }
    }

    async function handleCreateNote() {

        if (!title || !content) {
            alert("Title and Content required");
            return;
        }

        await createNote({
            title,
            content
        });

        setTitle("");
        setContent("");

        loadNotes();
    }

    async function handleUpdateNote() {

        if (!selectedNote) return;

        await updateNote(
            selectedNote.id,
            {
                title,
                content
            }
        );

        setEditing(false);

        loadNotes();
    }

    async function handleDeleteNote() {

        if (!selectedNote) return;

        const confirmDelete =
            window.confirm(
                "Delete this note?"
            );

        if (!confirmDelete) return;

        await deleteNote(selectedNote.id);

        setSelectedNote(null);

        loadNotes();
    }

    async function generateSummary() {

        if (!selectedNote) return;

        const result =
            await summarizeNote(
                selectedNote.content
            );

        setSummary(result);
    }

    const filteredNotes =
        notes.filter(note =>
            note.title
                .toLowerCase()
                .includes(
                    search.toLowerCase()
                )
        );

    return (
        <div
            style={{
                display: "flex",
                height: "100vh",
                background: "#f5f7fb"
            }}
        >

            {/* LEFT SIDEBAR */}

            <div
                style={{
                    width: "350px",
                    background: "#fff",
                    borderRight: "1px solid #ddd",
                    overflowY: "auto",
                    padding: "20px"
                }}
            >

                <h2>Smart Notes AI</h2>

                <input
                    placeholder="Search notes..."
                    value={search}
                    onChange={(e) =>
                        setSearch(e.target.value)
                    }
                    style={{
                        width: "100%",
                        padding: "10px",
                        marginBottom: "15px"
                    }}
                />

                <h3>Create Note</h3>

                <input
                    placeholder="Title"
                    value={title}
                    onChange={(e) =>
                        setTitle(e.target.value)
                    }
                    style={{
                        width: "100%",
                        padding: "10px",
                        marginBottom: "10px"
                    }}
                />

                <textarea
                    placeholder="Content"
                    value={content}
                    onChange={(e) =>
                        setContent(e.target.value)
                    }
                    rows={4}
                    style={{
                        width: "100%",
                        padding: "10px"
                    }}
                />

                <button
                    onClick={handleCreateNote}
                    style={{
                        width: "100%",
                        marginTop: "10px",
                        padding: "10px"
                    }}
                >
                    Save Note
                </button>

                <hr />

                {filteredNotes.map(note => (

                    <div
                        key={note.id}
                        onClick={() => {

                            setSelectedNote(note);

                            setTitle(note.title);

                            setContent(
                                note.content
                            );
                        }}
                        style={{
                            padding: "15px",
                            cursor: "pointer",
                            borderBottom:
                                "1px solid #eee"
                        }}
                    >
                        <h4>
                            {note.title}
                        </h4>

                        <small>
                            {
                                note.content
                                    ?.substring(
                                        0,
                                        60
                                    )
                            }
                        </small>
                    </div>
                ))}
            </div>

            {/* MAIN PANEL */}

            <div
                style={{
                    flex: 1,
                    padding: "30px"
                }}
            >

                {!selectedNote &&
                    <h2>
                        Select a note
                    </h2>
                }

                {selectedNote && (

                    <>
                        <h1>
                            {selectedNote.title}
                        </h1>

                        <p>
                            {selectedNote.content}
                        </p>

                        <div
                            style={{
                                display: "flex",
                                gap: "10px",
                                marginTop: "20px"
                            }}
                        >

                            <button
                                onClick={() =>
                                    setEditing(true)
                                }
                            >
                                Edit
                            </button>

                            <button
                                onClick={
                                    handleDeleteNote
                                }
                            >
                                Delete
                            </button>

                            <button
                                onClick={
                                    generateSummary
                                }
                            >
                                Generate AI Summary
                            </button>

                        </div>

                        {editing && (

                            <div
                                style={{
                                    marginTop:
                                        "30px"
                                }}
                            >

                                <h3>
                                    Edit Note
                                </h3>

                                <input
                                    value={title}
                                    onChange={(e) =>
                                        setTitle(
                                            e.target.value
                                        )
                                    }
                                    style={{
                                        width:
                                            "100%",
                                        padding:
                                            "10px"
                                    }}
                                />

                                <textarea
                                    value={content}
                                    rows={8}
                                    onChange={(e) =>
                                        setContent(
                                            e.target.value
                                        )
                                    }
                                    style={{
                                        width:
                                            "100%",
                                        marginTop:
                                            "10px",
                                        padding:
                                            "10px"
                                    }}
                                />

                                <button
                                    onClick={
                                        handleUpdateNote
                                    }
                                    style={{
                                        marginTop:
                                            "10px"
                                    }}
                                >
                                    Update Note
                                </button>

                            </div>
                        )}

                        <div
                            style={{
                                marginTop: "30px",
                                background:
                                    "#fff",
                                padding:
                                    "20px",
                                borderRadius:
                                    "12px"
                            }}
                        >
                            <ChatPanel />
                            <h3>
                                AI Summary
                            </h3>

                            <pre
                                style={{
                                    whiteSpace:
                                        "pre-wrap"
                                }}
                            >
                                {summary}
                            </pre>
                        </div>

                    </>
                )}

            </div>

        </div>
    );
}

export default Dashboard;