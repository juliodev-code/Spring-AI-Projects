import React, { useState } from 'react'

const ImageGenerator = () => {
  const [prompt, setPrompt] = useState('');
  const [imageURLs, setImageURLs] = useState([]);
  const generateImages = async () => {
    try{
      const response = await fetch(`http://localhost:8080/generate-image?prompt=${prompt}`);
      const urls = await response.json();
      setImageURLs(urls);
    }
    catch(error){
      console.error("Error generating images:", error);
    }
  }
  return (
    <div className="tab-content">
      <h2>Generate Image</h2>
      <input 
        type="text"
        value={prompt}
        onChange={(e)=>setPrompt(e.target.value)}
        placeholder='Enter your prompt for generate the image'/>
      <button onClick={generateImages}>Generate Image</button>
      <div className="image-grid">
        {imageURLs.map((url, index)=>(<img key={index} src={url} alt={`Generated ${index}`} />))}
        {[...Array(4 - imageURLs.length)].map((_ , index)=>(<div key={index + imageURLs.length} className="empty-image-slot"/>))}
      </div>
    </div>
  )
}

export default ImageGenerator