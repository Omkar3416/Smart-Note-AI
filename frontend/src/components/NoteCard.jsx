function NoteCard({ note, onSelect }) {
    return (
        <div
            onClick={() => onSelect(note)}
            style={{
                border: "1px solid #ddd",
                padding: "12px",
                marginBottom: "10px",
                cursor: "pointer",
                borderRadius: "8px",
            }}
        >
            <h3>{note.title}</h3>

            <p>
                {note.content?.substring(0, 100)}
            </p>
        </div>
    );
}

export default NoteCard;