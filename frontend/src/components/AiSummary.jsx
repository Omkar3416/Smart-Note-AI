function AiSummary({ summary }) {
    return (
        <div
            style={{
                border: "1px solid #ddd",
                padding: "16px",
                borderRadius: "8px",
            }}
        >
            <h2>AI Summary</h2>

            <pre>{summary}</pre>
        </div>
    );
}

export default AiSummary;