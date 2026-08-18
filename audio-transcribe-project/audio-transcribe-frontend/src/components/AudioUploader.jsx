import axios from 'axios';
import React, { useState } from 'react'

const AudioUploader = () => {
    const [file, setFile] = useState(null);
    const [transcription, setTranscription] = useState("");

    //change the file to the local state
    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    }
    //get the transcription from the backend
    const handleUpload = async () => {
        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await axios.post("http://localhost:8080/api/transcribe", formData, {
                headers:{'Content-Type':'multipart/form-data'}
            })
            setTranscription(response.data)
        }
        catch(error){
            console.error("Error transcribing audio", error);
        }
    }
  return (
    <div className='container'>
        <h1>Audio to Text Transcriber</h1>
        <div className="file-input">
            <input type="file" accept="audio/*" onChange={handleFileChange} />
        </div>
        <button className="upload-button" onClick={handleUpload}>Upload and Transcribe</button>
        <div className="transcription-result">
            <h2>Transcription Result</h2>
            <p>{transcription}</p>
        </div>
    </div>
  )
}

export default AudioUploader