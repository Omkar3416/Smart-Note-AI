import { useState } from "react";
import { sendMessage } from "../services/chatService";

function ChatPanel() {

    const [message, setMessage] = useState("");

    const [messages, setMessages] = useState([]);

    const [loading, setLoading] = useState(false);

    async function handleSend() {

        if (!message.trim()) {
            return;
        }

        setLoading(true);

        try {

            const userMessage = {
                role: "user",
                content: message
            };

            setMessages(prev => [...prev, userMessage]);

            const result = await sendMessage(message);

            const aiMessage = {
                role: "assistant",
                content: result
            };

            setMessages(prev => [...prev, aiMessage]);

            setMessage("");

        } catch (error) {

            console.error(error);

            setMessages(prev => [
                ...prev,
                {
                    role: "assistant",
                    content: "AI Error occurred"
                }
            ]);

        } finally {

            setLoading(false);
        }
    }

    return (
        <div
            style={{
                marginTop: "30px",
                background: "#fff",
                padding: "20px",
                borderRadius: "12px",
                boxShadow:
                    "0 2px 10px rgba(0,0,0,0.05)"
            }}
        >
            <h3>
                AI Chat Assistant
            </h3>

            <textarea
                rows={4}
                placeholder="Ask anything..."
                value={message}
                onChange={(e) =>
                    setMessage(
                        e.target.value
                    )
                }
                style={{
                    width: "100%",
                    padding: "12px",
                    marginTop: "10px"
                }}
            />

            <button
                onClick={handleSend}
                disabled={loading}
                style={{
                    marginTop: "10px",
                    padding:
                        "10px 20px"
                }}
            >
                {
                    loading
                        ? "Thinking..."
                        : "Send"
                }
            </button>

            {messages.length > 0 && (

                <div
                    style={{
                        marginTop: "20px"
                    }}
                >
                    {messages.map((msg, index) => (

                        <div
                            key={index}
                            style={{
                                marginBottom: "12px",
                                padding: "12px",
                                borderRadius: "10px",
                                background:
                                    msg.role === "user"
                                        ? "#e0f2fe"
                                        : "#f1f5f9"
                            }}
                        >
                            <strong>
                                {msg.role === "user"
                                    ? "You"
                                    : "AI"}
                            </strong>

                            <p
                                style={{
                                    marginTop: "5px",
                                    whiteSpace: "pre-wrap"
                                }}
                            >
                                {msg.content}
                            </p>

                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default ChatPanel;