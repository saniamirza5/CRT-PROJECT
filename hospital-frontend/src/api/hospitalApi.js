import axios from "axios";

const API = axios.create({
  baseURL: "https://crt-project-un9v.onrender.com",
});

export const getPatients = async () => {
  return API.get("/patients");
};

export const addPatient = async (patientData) => {
  return API.post("/patients", patientData);
};

export const deletePatient = async (id) => {
  return API.delete(`/patients/${id}`);
};