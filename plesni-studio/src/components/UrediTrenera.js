import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

const UrediTrenera = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [trener, setTrener] = useState({
    ime: '',
    prezime: '',
    oib: '',
    email: '',
    lozinka: ''
  });

  useEffect(() => {
    axios.get(`http://localhost:8080/api/treneri/${id}`)
      .then(response => {
        setTrener(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the trener details!', error);
      });
  }, [id]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setTrener({ ...trener, [name]: value });
  };

  const handleSpremi = (e) => {
    e.preventDefault();
    axios.put(`http://localhost:8080/api/treneri/${id}`, trener)
      .then(response => {
        alert('Trener uspješno ažuriran!');
        navigate('/treneri');
      })
      .catch(error => {
        console.error('There was an error updating the trener!', error);
      });
  };

  const handleOdustani = () => {
    navigate('/treneri');
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <span className="close" onClick={handleOdustani}>&times;</span>
        <h2>Uredi Trenera</h2>
        <form onSubmit={handleSpremi}>
          <div className="form-group">
            <label>Ime:</label>
            <input
              type="text"
              name="ime"
              value={trener.ime}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Prezime:</label>
            <input
              type="text"
              name="prezime"
              value={trener.prezime}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="form-group">
            <label>OIB:</label>
            <input
              type="text"
              name="oib"
              value={trener.oib}
              disabled
            />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={trener.email}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Lozinka:</label>
            <input
              type="password"
              name="lozinka"
              value={trener.lozinka}
              onChange={handleInputChange}
              required
            />
          </div>
          <button type="submit">Spremi</button>
          <button type="button" onClick={handleOdustani}>Odustani</button>
        </form>
      </div>
    </div>
  );
};

export default UrediTrenera;
