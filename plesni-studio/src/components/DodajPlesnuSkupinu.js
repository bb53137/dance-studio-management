import React, { useState, useEffect } from 'react';
import axios from 'axios';

const DodajPlesnuSkupinu = ({ onClose, onAdd }) => {
  const [naziv, setNaziv] = useState('');
  const [opis, setOpis] = useState('');
  const [treneri, setTreneri] = useState([]);
  const [odabraniTrener, setOdabraniTrener] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/api/treneri')
      .then(response => {
        setTreneri(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the treneri!', error);
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const novaGrupa = {
      grupa: {
        naziv,
        opis
      },
      trener: odabraniTrener
    };

    axios.post('http://localhost:8080/api/grupa/poducava', novaGrupa)
      .then(response => {
        onAdd(response.data);
        onClose();
      })
      .catch(error => {
        console.error('There was an error creating the group!', error);
      });
  }

  const isFormValid = naziv && opis && odabraniTrener;

  return (
    <div className="modal">
      <div className="modal-content">
        <span className="close" onClick={onClose}>&times;</span>
        <h2>Dodaj novu Plesnu Skupinu</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Naziv:</label>
            <input 
              type="text" 
              value={naziv} 
              onChange={(e) => setNaziv(e.target.value)} 
              required 
            />
          </div>
          <div className="form-group">
            <label>Opis:</label>
            <input 
              type="text" 
              value={opis} 
              onChange={(e) => setOpis(e.target.value)} 
              required 
            />
          </div>
          <div className="form-group">
            <label>Trener:</label>
            <select 
              value={odabraniTrener} 
              onChange={(e) => setOdabraniTrener(e.target.value)} 
              required
            >
              <option value="">Odaberi trenera</option>
              {treneri.map(trener => (
                <option key={trener.oib} value={trener.oib}>
                  {trener.ime} {trener.prezime}
                </option>
              ))}
            </select>
          </div>
          <button type="submit" disabled={!isFormValid}>Dodaj</button>
        </form>
      </div>
    </div>
  );
}

export default DodajPlesnuSkupinu;
