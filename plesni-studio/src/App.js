import React from 'react';
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';
import PlesneSkupine from './components/PlesneSkupine';
import PlesnaSkupinaDetalji from './components/PlesnaSkupinaDetalji';
import Treneri from './components/Treneri';
import Clanovi from './components/Clanovi';
import PlesniStudio from './components/PlesniStudio';
import UrediTrenera from './components/UrediTrenera';
import './App.css';

const App = () => {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li><NavLink to="/" className={({ isActive }) => isActive ? 'active' : ''}>Plesni Studio</NavLink></li>
            <li><NavLink to="/plesne-skupine" className={({ isActive }) => isActive ? 'active' : ''}>Plesne Skupine</NavLink></li>
            <li><NavLink to="/treneri" className={({ isActive }) => isActive ? 'active' : ''}>Treneri</NavLink></li>
            <li><NavLink to="/clanovi" className={({ isActive }) => isActive ? 'active' : ''}>Članovi</NavLink></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<PlesniStudio />} />
          <Route path="/plesne-skupine" element={<PlesneSkupine />} />
          <Route path="/plesne-skupine/:id" element={<PlesnaSkupinaDetalji />} />
          <Route path="/treneri" element={<Treneri />} />
          <Route path="/treneri/uredi/:id" element={<UrediTrenera />} />
          <Route path="/clanovi" element={<Clanovi />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
