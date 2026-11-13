import React, { useState } from 'react';
import axios from 'axios';

const DodajTrenera = ({ onClose, onAdd }) => {
  const [ime, setIme] = useState('');
  const [prezime, setPrezime] = useState('');
  const [oib, setOib] = useState('');
  const [email, setEmail] = useState('');
  const [lozinka, setLozinka] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    const noviTrener = {
      ime,
      prezime,
      oib,
      email,
      lozinka
    };

    axios.post('http://localhost:8080/api/treneri', noviTrener)
      .then(response => {
        onAdd(response.data);
        onClose();
      })
      .catch(error => {
        console.error('There was an error creating the trener!', error);
      });
  }

  const isFormValid = ime && prezime && oib && email && lozinka;

  return (
    <div className="modal">
      <div className="modal-content">
        <span className="close" onClick={onClose}>&times;</span>
        <h2>Dodaj novog Trenera</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Ime:</label>
            <input 
              type="text" 
              value={ime} 
              onChange={(e) => setIme(e.target.value)} 
              required 
            />
          </div>
          <div className="form-group">
            <label>Prezime:</label>
            <input 
              type="text" 
              value={prezime} 
              onChange={(e) => setPrezime(e.target.value)} 
              required 
            />
          </div>
          <div className="form-group">
            <label>OIB:</label>
            <input 
              type="text" 
              value={oib} 
              onChange={(e) => setOib(e.target.value)} 
              required 
            />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input 
              type="email" 
              value={email} 
              onChange={(e) => setEmail(e.target.value)} 
              required 
            />
          </div>
          <div className="form-group">
            <label>Lozinka:</label>
            <input 
              type="password" 
              value={lozinka} 
              onChange={(e) => setLozinka(e.target.value)} 
              required 
            />
          </div>
          <button type="submit" disabled={!isFormValid}>Dodaj</button>
        </form>
      </div>
    </div>
  );
}

export default DodajTrenera;
