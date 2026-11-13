import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import DodajPlesnuSkupinu from './DodajPlesnuSkupinu';

const PlesneSkupine = () => {
  const [plesneSkupine, setPlesneSkupine] = useState([]);
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    fetchGrupe();
  }, []);

  const fetchGrupe = () => {
    axios.get('http://localhost:8080/api/grupe')
      .then(response => {
        setPlesneSkupine(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the plesne skupine!', error);
      });
  }

  const handleAddGrupa = (novaGrupa) => {
    fetchGrupe(); // Fetch updated list of groups after adding a new one
  }

  return (
    <div>
      <h2>Plesne Skupine</h2>
      <table>
       
        <tbody>
          {plesneSkupine.map(skupina => (
            <tr key={skupina.id}>
              <td><Link to={`/plesne-skupine/${skupina.id}`}>{skupina.naziv}</Link></td>
              
            </tr>
          ))}
        </tbody>
      </table>
      <button className="add-button" onClick={() => setShowForm(true)}>Dodaj novu</button>
      {showForm && <DodajPlesnuSkupinu onClose={() => setShowForm(false)} onAdd={handleAddGrupa} />}
    </div>
  );
}

export default PlesneSkupine;
