const colors = [
    "#FEF3C7",
    "#DBEAFE",
    "#DCFCE7",
    "#FCE7F3",
    "#EDE9FE",
    "#FEE2E2",
];

function NoteCard({
                      note,
                      onSelect
                  }) {

    const color =
        colors[note.id % colors.length];

    return (
        <div
            onClick={() => onSelect(note)}
            style={{
                background: color,
                borderRadius: "16px",
                padding: "16px",
                marginBottom: "12px",
                cursor: "pointer",
                boxShadow:
                    "0 2px 8px rgba(0,0,0,.08)"
            }}
        >
            <h4
                style={{
                    marginBottom: "8px"
                }}
            >
                {note.title}
            </h4>

            <p
                style={{
                    fontSize: "13px",
                    color: "#555"
                }}
            >
                {note.content?.substring(0,80)}
            </p>
        </div>
    );
}

export default NoteCard;