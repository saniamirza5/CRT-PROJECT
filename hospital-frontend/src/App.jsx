import { useEffect, useState } from "react";

import {
  getPatients,
  addPatient,
  deletePatient,
} from "./api/hospitalApi";

function App() {

  const [patients, setPatients] = useState([]);

  const [patientId, setPatientId] = useState("");
  const [patientName, setPatientName] = useState("");
  const [disease, setDisease] = useState("");
  const [doctor, setDoctor] = useState("");

  const [searchTerm, setSearchTerm] = useState("");

  const [loading, setLoading] = useState(false);

  // FETCH PATIENTS

  const fetchPatients = async () => {

    try {

      setLoading(true);

      const response = await getPatients();

      setPatients(response.data);

    } catch (error) {

      console.log(error);

      alert("Failed to fetch patients");

    } finally {

      setLoading(false);

    }
  };

  useEffect(() => {

    fetchPatients();

  }, []);

  // ADD PATIENT

  const admitPatient = async () => {

    if (!patientId || !patientName || !disease || !doctor) {

      alert("Please fill all fields");

      return;
    }

    const newPatient = {

      patientId,
      patientName,
      disease,
      doctorAssigned: doctor,
    };

    try {

      setLoading(true);

      await addPatient(newPatient);

      await fetchPatients();

      setPatientId("");
      setPatientName("");
      setDisease("");
      setDoctor("");

      alert("Patient admitted successfully");

    } catch (error) {

      console.log(error);

      alert("Failed to add patient");

    } finally {

      setLoading(false);

    }
  };

  // DELETE PATIENT

  const dischargePatient = async (id) => {

    try {

      setLoading(true);

      await deletePatient(id);

      await fetchPatients();

    } catch (error) {

      console.log(error);

      alert("Failed to discharge patient");

    } finally {

      setLoading(false);

    }
  };

  // SEARCH

  const filteredPatients = patients.filter((patient) =>
    patient.patientId
      ?.toString()
      .includes(searchTerm) ||

    patient.patientName
      ?.toLowerCase()
      .includes(searchTerm.toLowerCase())
  );

  return (

    <div className="min-h-screen flex bg-black text-white overflow-hidden">

      {/* SIDEBAR */}

      <div className="w-[250px] bg-[#09090b] border-r border-purple-500/10 flex flex-col justify-between px-6 py-8">

        <div>

          <div className="mb-14">

            <h1 className="text-4xl font-bold bg-gradient-to-r from-purple-400 via-pink-500 to-orange-400 bg-clip-text text-transparent">
              AetherCare
            </h1>

            <p className="text-slate-500 mt-2 text-sm">
              Smart Hospital Platform
            </p>

          </div>

          <div className="space-y-3">

            <button className="w-full text-left bg-gradient-to-r from-purple-500/20 to-orange-400/10 border border-purple-500/20 rounded-xl px-5 py-4 hover:scale-[1.02] transition-all">
              Dashboard
            </button>

            <button className="w-full text-left hover:bg-white/5 rounded-xl px-5 py-4 transition-all">
              Patients
            </button>

            <button className="w-full text-left hover:bg-white/5 rounded-xl px-5 py-4 transition-all">
              Doctors
            </button>

            <button className="w-full text-left hover:bg-white/5 rounded-xl px-5 py-4 transition-all">
              Appointments
            </button>

            <button className="w-full text-left hover:bg-white/5 rounded-xl px-5 py-4 transition-all">
              Analytics
            </button>

            <button className="w-full text-left hover:bg-white/5 rounded-xl px-5 py-4 transition-all">
              Settings
            </button>

          </div>

        </div>

        <button className="bg-gradient-to-r from-purple-500 to-orange-400 hover:opacity-90 transition-all py-4 rounded-xl font-semibold shadow-[0_0_35px_rgba(168,85,247,0.35)]">

          Logout

        </button>

      </div>

      {/* MAIN */}

      <div className="flex-1 overflow-y-auto bg-gradient-to-br from-[#09090b] via-[#130915] to-[#2a0f0f]">

        {/* NAVBAR */}

        <div className="h-[90px] border-b border-purple-500/10 px-10 flex justify-between items-center backdrop-blur-xl sticky top-0 z-50 bg-black/20">

          <div>

            <h1 className="text-3xl font-bold bg-gradient-to-r from-purple-300 via-pink-400 to-orange-400 bg-clip-text text-transparent">

              AetherCare Hospital

            </h1>

            <p className="text-slate-500 text-sm mt-1">
              Intelligent Healthcare Platform
            </p>

          </div>

          <button className="bg-purple-500 hover:bg-purple-400 transition-all px-6 py-3 rounded-xl font-semibold shadow-[0_0_25px_rgba(168,85,247,0.4)]">

            Emergency Support

          </button>

        </div>

        {/* HERO */}

        <div className="px-10 py-14 border-b border-purple-500/10">

          <div className="max-w-5xl">

            <h1 className="text-6xl leading-[75px] font-bold tracking-[-2px] bg-gradient-to-r from-purple-300 via-pink-400 to-orange-400 bg-clip-text text-transparent">

              Intelligent healthcare operations with modern patient management.

            </h1>

            <p className="text-slate-400 mt-7 text-lg leading-8 max-w-3xl">

              Monitor patients, streamline admissions, manage doctors, and improve healthcare efficiency using a premium hospital dashboard.

            </p>

          </div>

        </div>

        {/* STATS */}

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 px-10 py-10">

          <div className="bg-white/[0.03] border border-purple-500/10 rounded-2xl p-7 backdrop-blur-xl hover:border-pink-400/30 hover:translate-y-[-2px] transition-all">

            <p className="text-slate-400 text-sm">
              Total Patients
            </p>

            <h2 className="text-6xl font-light mt-4">
              {patients.length}
            </h2>

          </div>

          <div className="bg-white/[0.03] border border-purple-500/10 rounded-2xl p-7 backdrop-blur-xl hover:border-pink-400/30 hover:translate-y-[-2px] transition-all">

            <p className="text-slate-400 text-sm">
              Active Doctors
            </p>

            <h2 className="text-6xl font-light mt-4">
              24
            </h2>

          </div>

          <div className="bg-white/[0.03] border border-purple-500/10 rounded-2xl p-7 backdrop-blur-xl hover:border-pink-400/30 hover:translate-y-[-2px] transition-all">

            <p className="text-slate-400 text-sm">
              Emergency Rooms
            </p>

            <h2 className="text-6xl font-light mt-4">
              08
            </h2>

          </div>

        </div>

        {/* FORM */}

        <div className="px-10 pb-10">

          <div className="bg-white/[0.03] border border-purple-500/10 rounded-3xl p-10 backdrop-blur-xl">

            <div className="flex justify-between items-center mb-10">

              <div>

                <h2 className="text-4xl font-semibold">
                  Patient Admission
                </h2>

                <p className="text-slate-400 mt-2">
                  Register and manage patients instantly.
                </p>

              </div>

              <div className="w-24 h-24 rounded-full bg-gradient-to-br from-purple-500 to-orange-400 blur-3xl opacity-40"></div>

            </div>

            <div className="grid md:grid-cols-2 gap-5">

              <input
                type="number"
                placeholder="Patient ID"
                value={patientId}
                onChange={(e) => setPatientId(e.target.value)}
                className="bg-black/30 border border-purple-500/10 focus:border-pink-400 rounded-xl p-4 outline-none"
              />

              <input
                type="text"
                placeholder="Patient Name"
                value={patientName}
                onChange={(e) => setPatientName(e.target.value)}
                className="bg-black/30 border border-purple-500/10 focus:border-pink-400 rounded-xl p-4 outline-none"
              />

              <input
                type="text"
                placeholder="Disease"
                value={disease}
                onChange={(e) => setDisease(e.target.value)}
                className="bg-black/30 border border-purple-500/10 focus:border-pink-400 rounded-xl p-4 outline-none"
              />

              <input
                type="text"
                placeholder="Doctor Assigned"
                value={doctor}
                onChange={(e) => setDoctor(e.target.value)}
                className="bg-black/30 border border-purple-500/10 focus:border-pink-400 rounded-xl p-4 outline-none"
              />

            </div>

            <button
              onClick={admitPatient}
              disabled={loading}
              className="mt-8 bg-gradient-to-r from-purple-500 to-orange-400 hover:scale-[1.02] transition-all px-8 py-4 rounded-xl font-semibold shadow-[0_0_30px_rgba(168,85,247,0.35)]"
            >

              {loading ? "Processing..." : "Admit Patient"}

            </button>

          </div>

        </div>

        {/* SEARCH */}

        <div className="px-10 pb-6">

          <input
            type="text"
            placeholder="Search patient by ID or Name..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full md:w-[420px] bg-black/30 border border-purple-500/10 focus:border-orange-400 rounded-xl p-4 outline-none"
          />

        </div>

        {/* RECORDS */}

        <div className="px-10 pb-16">

          <h2 className="text-4xl font-semibold mb-3">
            Patient Records
          </h2>

          <p className="text-slate-400 mb-10">
            Real-time hospital admission tracking.
          </p>

          <div className="space-y-5">

            {filteredPatients.length === 0 ? (

              <div className="bg-white/[0.03] border border-purple-500/10 rounded-2xl p-10 text-center text-slate-400">

                No patient records found.

              </div>

            ) : (

              filteredPatients.map((patient, index) => (

                <div
                  key={index}
                  className="bg-white/[0.03] border border-purple-500/10 rounded-2xl p-7 hover:border-orange-400/30 hover:translate-y-[-2px] transition-all"
                >

                  <div className="grid md:grid-cols-5 gap-6 items-center">

                    <div>
                      <p className="text-slate-500 text-sm">
                        ID
                      </p>

                      <h3 className="text-xl mt-2">
                        {patient.patientId}
                      </h3>
                    </div>

                    <div>
                      <p className="text-slate-500 text-sm">
                        Name
                      </p>

                      <h3 className="text-xl mt-2">
                        {patient.patientName}
                      </h3>
                    </div>

                    <div>
                      <p className="text-slate-500 text-sm">
                        Disease
                      </p>

                      <h3 className="text-xl mt-2">
                        {patient.disease}
                      </h3>
                    </div>

                    <div>
                      <p className="text-slate-500 text-sm">
                        Doctor
                      </p>

                      <h3 className="text-xl mt-2">
                        {patient.doctorAssigned}
                      </h3>
                    </div>

                    <button
                      onClick={() =>
                        dischargePatient(patient.patientId)
                      }
                      className="bg-red-500 hover:bg-red-400 transition-all px-5 py-3 rounded-xl font-medium"
                    >

                      Discharge

                    </button>

                  </div>

                </div>

              ))

            )}

          </div>

        </div>

      </div>

    </div>
  );
}

export default App;