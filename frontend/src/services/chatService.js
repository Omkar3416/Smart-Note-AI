import api from "./api";

export const sendMessage = async (message) => {

    const response = await api.post(
        "/api/ai/chat",
        message,
        {
            headers: {
                "Content-Type": "text/plain"
            }
        }
    );

    return response.data;
};