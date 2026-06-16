import api from "./api";

export const summarizeNote = async (content) => {

    const response = await api.get(
        "/api/ai/summarize",
        {
            params: {
                note: content
            }
        }
    );

    return response.data;
};