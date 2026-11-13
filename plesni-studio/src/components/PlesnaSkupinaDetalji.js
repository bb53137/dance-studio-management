import React, { useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

const PlesnaSkupinaDetalji = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [grupa, setGrupa] = useState(null);
  const [treneri, setTreneri] = useState([]);
  const [odabraniTrener, setOdabraniTrener] = useState('');
  const [clanovi, setClanovi] = useState([]);
  const [noviClanovi, setNoviClanovi] = useState([]);
  const [editedClanovi, setEditedClanovi] = useState({});
  const [editingClan, setEditingClan] = useState(null);
  const [showAddClanForm, setShowAddClanForm] = useState(false);
  const [noviClan, setNoviClan] = useState('');

  const fetchGroupDetails = useCallback(() => {
    axios.get(`http://localhost:8080/api/grupa/poducava/${id}`)
      .then(response => {
        setGrupa(response.data);
        setOdabraniTrener(response.data.trener.oib);
        setClanovi(response.data.clanovi);
      })
      .catch(error => {
        console.error('There was an error fetching the group details!', error);
      });
  }, [id]);

  const fetchTreneri = useCallback(() => {
    axios.get('http://localhost:8080/api/treneri')
      .then(response => {
        setTreneri(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the treneri!', error);
      });
  }, []);

  const fetchClanovi = useCallback(() => {
    axios.get('http://localhost:8080/api/clanovi')
      .then(response => {
        setNoviClanovi(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the clanovi!', error);
      });
  }, []);

  useEffect(() => {
    fetchGroupDetails();
    fetchTreneri();
    fetchClanovi();
  }, [fetchGroupDetails, fetchTreneri, fetchClanovi]);

  const handleSpremi = (event) => {
    event.preventDefault();
    const updatedGrupa = {
      id: grupa.id,
      naziv: grupa.naziv,
      opis: grupa.opis,
    };

    const requestData = {
      grupa: updatedGrupa,
      trener: odabraniTrener
    };

    console.log('Sending update request with data:', requestData);

    axios.put(`http://localhost:8080/api/grupa/poducava/${id}`, requestData)
      .then(() => {
        alert('Grupa i trener uspješno ažurirani!');
        navigate('/plesne-skupine');
      })
      .catch(error => {
        console.error('There was an error updating the group!', error);
        console.log('Error details:', error.response ? error.response.data : error.message);
      });
  };

  const handleObrisi = () => {
    axios.delete(`http://localhost:8080/api/grupa/poducava/${id}`)
      .then(response => {
        alert('Grupa uspješno obrisana!');
        navigate('/plesne-skupine');
      })
      .catch(error => {
        console.error('There was an error deleting the group!', error);
      });
  };

  const handleChangeClanarina = (oib, value) => {
    setEditedClanovi({
      ...editedClanovi,
      [oib]: value
    });
  };

  const handleEditClan = (event, oib) => {
    event.preventDefault();
    setEditingClan(oib);
    setEditedClanovi({
      ...editedClanovi,
      [oib]: clanovi.find(clan => clan.oib === oib).placenaClan
    });
  };

  const handleSaveClan = (event, oib) => {
    event.preventDefault();

    axios.put(`http://localhost:8080/api/clanovi/clanarina/${oib}`, {
      placena: editedClanovi[oib] === 'Da'
    })
    .then(() => {
      setEditingClan(null);
      setClanovi(clanovi.map(clan => 
        clan.oib === oib ? { ...clan, placenaClan: editedClanovi[oib] } : clan
      ));
      setEditedClanovi({});
    })
    .catch(error => {
      console.error('There was an error updating the member!', error);
    });
  };

  const handleCancelEditClan = (event, oib) => {
    event.preventDefault();
    setEditingClan(null);
    setEditedClanovi({
      ...editedClanovi,
      [oib]: clanovi.find(clan => clan.oib === oib).placenaClan
    });
  };

  const handleAddClan = () => {
    setShowAddClanForm(true);
  };

  const handleAddClanSubmit = (event) => {
    event.preventDefault();
    const noviClanSkupina = {
      mjesec: new Date().toISOString(),
      oib: noviClan,
      idPlesneSkupine: grupa.id,
      idKategorije: 1, // postavite ispravnu vrijednost idKategorije
      ukupnaClanarina: 0 // ili postavite ispravnu vrijednost
    };

    console.log('Adding new member with data:', noviClanSkupina);

    axios.post('http://localhost:8080/api/clan-skupina', noviClanSkupina)
      .then(response => {
        console.log('Response from server:', response.data);
        alert('Novi član uspješno dodan!');
        fetchGroupDetails(); // Refresh the group details
        setShowAddClanForm(false);
        setNoviClan('');
      })
      .catch(error => {
        console.error('There was an error adding the new member!', error);
        console.log('Error details:', error.response ? error.response.data : error.message);
      });
  };

  const handleDeleteClan = (oib) => {
    console.log(`Deleting member with oib: ${oib} and group id: ${id}`);
    axios.delete(`http://localhost:8080/api/clan-skupina/${oib}/${id}`)
      .then(() => {
        console.log('Član uspješno obrisan!');
        alert('Član uspješno obrisan!');
        fetchGroupDetails();
      })
      .catch(error => {
        console.error('There was an error deleting the member!', error);
        console.log('Error details:', error.response ? error.response.data : error.message);
      });
};

  const isFormValid = grupa?.naziv && grupa?.opis && odabraniTrener;

  return (
    <div>
      <h2>Plesna Skupina Detalji</h2>
      {grupa && (
        <form onSubmit={handleSpremi}>
          <div className="form-group">
            <label>Naziv:</label>
            <input 
              type="text" 
              value={grupa.naziv} 
              onChange={(e) => setGrupa({ ...grupa, naziv: e.target.value })} 
              required 
            />
          </div>
          <div className="form-group">
            <label>Opis:</label>
            <input 
              type="text" 
              value={grupa.opis} 
              onChange={(e) => setGrupa({ ...grupa, opis: e.target.value })} 
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
          <div className="action-buttons">
            <button type="submit" disabled={!isFormValid}>Spremi</button>
            <button type="button" onClick={handleObrisi}>Obriši</button>
          </div>
        </form>
      )}
      <h3>Članovi:</h3>
      <table>
        <thead>
          <tr>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Plaćena Članarina</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {clanovi.map(clan => (
            <tr key={clan.oib}>
              <td>{clan.ime}</td>
              <td>{clan.prezime}</td>
              <td>
                {editingClan === clan.oib ? (
                  <select 
                    value={editedClanovi[clan.oib] || clan.placenaClan} 
                    onChange={(e) => handleChangeClanarina(clan.oib, e.target.value)}
                  >
                    <option value="Da">Da</option>
                    <option value="Ne">Ne</option>
                  </select>
                ) : (
                  clan.placenaClan
                )}
              </td>
              <td>
                {editingClan === clan.oib ? (
                  <>
                    <button className="edit-button" onClick={(event) => handleSaveClan(event, clan.oib)}>Spremi</button>
                    <button className="delete-button" onClick={(event) => handleCancelEditClan(event, clan.oib)}>Odustani</button>
                  </>
                ) : (
                  <>
                    <button className="edit-button" onClick={(event) => handleEditClan(event, clan.oib)}>Uredi</button>
                    <button className="delete-button" onClick={() => handleDeleteClan(clan.oib)}>Obriši</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {showAddClanForm && (
        <form onSubmit={handleAddClanSubmit}>
          <div className="form-group">
            <label>Novi član:</label>
            <select 
              value={noviClan} 
              onChange={(e) => setNoviClan(e.target.value)} 
              required
            >
              <option value="">Odaberi člana</option>
              {noviClanovi.map(clan => (
                <option key={clan.oib} value={clan.oib}>
                  {clan.ime} {clan.prezime}
                </option>
              ))}
            </select>
          </div>
          <div className="action-buttons">
            <button type="submit">Dodaj</button>
            <button type="button" onClick={() => setShowAddClanForm(false)}>Odustani</button>
          </div>
        </form>
      )}
      {!showAddClanForm && (
        <button className="add-button" type="button" onClick={handleAddClan}>Dodaj novog</button>
      )}
    </div>
  );
}

export default PlesnaSkupinaDetalji;
