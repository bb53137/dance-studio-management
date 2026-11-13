import React, { useEffect, useState } from 'react';
import axios from 'axios';
import DodajTrenera from './DodajTrenera';
import { useNavigate } from 'react-router-dom';

const Treneri = () => {
  const [treneri, setTreneri] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchTreneri();
  }, []);

  const fetchTreneri = () => {
    axios.get('http://localhost:8080/api/treneri')
      .then(response => {
        setTreneri(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the treneri!', error);
      });
  }

  const handleAddTrener = (noviTrener) => {
    setTreneri([...treneri, noviTrener]);
  }

  const handleDelete = (oib) => {
    // Prvo izbriši iz tablice Poducava
    axios.delete(`http://localhost:8080/api/poducava/trener/${oib}`)
      .then(response => {
        // Zatim izbriši trenera
        return axios.delete(`http://localhost:8080/api/treneri/${oib}`);
      })
      .then(response => {
        fetchTreneri();
      })
      .catch(error => {
        console.error('There was an error deleting the trener or poducava!', error);
      });
  }

  const handleUrediClick = (oib) => {
    navigate(`/treneri/uredi/${oib}`);
  };

  return (
    <div>
      <h2>Treneri</h2>
      <table>
        <thead>
          <tr>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {treneri.map(trener => (
            <tr key={trener.oib}>
              <td>{trener.ime}</td>
              <td>{trener.prezime}</td>
              <td>
                <button className="edit-button" onClick={() => handleUrediClick(trener.oib)}>Uredi</button>
                <button className="delete-button" onClick={() => handleDelete(trener.oib)}>Obriši</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button className="add-button" onClick={() => setShowForm(true)}>Dodaj novog</button>
      {showForm && <DodajTrenera onClose={() => { setShowForm(false); fetchTreneri(); }} onAdd={handleAddTrener} />}
    </div>
  );
}

export default Treneri;
