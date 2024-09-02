"use client"
import React, { useState, useRef, useEffect } from "react";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { FaInfoCircle } from "react-icons/fa";
import { useRouter } from 'next/navigation'
import http from "../../../axios";

function Participate({params}: any) {
  const [nationalId, setNationalId] = useState("");
  const [verificationNumber, setVerificationNumber] = useState("");
  const [photo, setPhoto] = useState(null);
  const videoRef: any = useRef(null);
  const canvasRef: any = useRef(null);
  const router = useRouter()

  useEffect(() => {
    startCamera();
  }, []);

  const startCamera = () => {
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .then((stream) => {
        videoRef.current.srcObject = stream;
      })
      .catch((err) => {
        console.error("Error accessing camera: ", err);
        alert("Error accessing camera. Please check your browser settings.");
      });
  };

  const takePhoto = () => {
    const context = canvasRef.current.getContext("2d");
    context.drawImage(videoRef.current, 0, 0, 300, 300);
    setPhoto(canvasRef.current.toDataURL("image/png"));
  };

  const handleSubmit = async (event: any) => {
    event.preventDefault();
    takePhoto();
    
    const formData = new FormData();
    formData.append("documentId", nationalId);
    formData.append("verificationNumber", verificationNumber);
    if (photo) {
      const blob = await fetch(photo).then((res) => res.blob());
      formData.append("verificationImage", blob, "photo.png");
    }

    try {
      const response = await http.post(`/citizen/identify`, formData, {
        headers: {
         "Authorization": null,
          "Content-Type": "multipart/form-data",
        },
      });
      const token = response.data;
      localStorage.setItem('authToken', token);
      router.push(`/election-events/${params.slug}/candidates`)

      console.log("Response:", response.data);
    } catch (error) {
      console.error("There was an error!", error);
    }
  };

  const handleNationalIdChange = (e: any) => {
    const value = e.target.value;
    if (/^\d*$/.test(value) && value.length <= 8) {
      setNationalId(value);
    }
  };

  const handleVerificationNumberChange = (e: any) => {
    const value = e.target.value;
    if (/^\d?$/.test(value)) {
      setVerificationNumber(value);
    }
  };

  const showVerificationInfo = () => {
    toast.info(
      <div>
        <img src="verification-info.png" alt="Verification Number Info" />
        <p>This is where you can find your verification number.</p>
      </div>,
      {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      }
    );
  };

  const isFormValid = nationalId.length === 8 && verificationNumber.length === 1;

  return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg">
      <h2 className="text-2xl font-bold mb-6 text-center">Identify User</h2>
      <form onSubmit={handleSubmit} className="space-y-6">
        <div>
          <label className="block text-sm font-medium text-gray-700">
            National Identification Number
          </label>
          <input
            type="text"
            placeholder="National ID"
            value={nationalId}
            onChange={handleNationalIdChange}
            className="mt-1 block w-full px-3 py-2 bg-gray-50 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm text-black"
            maxLength={8}
          />
          <small className="text-gray-500">Must be exactly 8 digits.</small>
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700">
            Verification Number
          </label>
          <div className="flex items-center">
            <input
              type="text"
              placeholder="Verification Number"
              value={verificationNumber}
              onChange={handleVerificationNumberChange}
              className="mt-1 block w-full px-3 py-2 bg-gray-50 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm text-black"
              maxLength={1}
            />
            <FaInfoCircle
              className="ml-2 text-gray-500 hover:text-indigo-600 cursor-pointer"
              onClick={showVerificationInfo}
              title="Click for more info"
            />
          </div>
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Capture Photo
          </label>
          <div className="flex items-center justify-center relative">
            <video
              ref={videoRef}
              className="rounded-full w-48 h-48 object-cover"
              autoPlay
            ></video>
            <canvas
              ref={canvasRef}
              className="hidden"
              width="300"
              height="300"
            ></canvas>
            {photo && (
              <img
                src={photo}
                alt="Captured"
                className="rounded-full w-48 h-48 object-cover absolute top-0 left-0"
              />
            )}
          </div>
        </div>
        <div>
          <button
            type="submit"
            className={`w-full px-4 py-2 font-medium rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 ${
              isFormValid
                ? "bg-indigo-600 text-white hover:bg-indigo-700"
                : "bg-gray-400 text-gray-700 cursor-not-allowed"
            }`}
            disabled={!isFormValid}
          >
            Identify
          </button>
        </div>
      </form>
      <ToastContainer />
    </div>
  );
}

export default Participate;
