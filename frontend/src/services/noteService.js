import api from "./api";

export const getAllNotes = async () => {
    const response = await api.get("/api/notes");
    return response.data;
};

export const getNoteById = async (id) => {
    const response = await api.get(`/api/notes/${id}`);
    return response.data;
};

export const createNote = async (note) => {
    const response = await api.post("/api/notes", note);
    return response.data;
};

export const updateNote = async (id, note) => {
    const response = await api.put(
        `/api/notes/${id}`,
        note
    );

    return response.data;
};

export const deleteNote = async (id) => {
    await api.delete(`/api/notes/${id}`);
};