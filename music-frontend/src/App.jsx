import { Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import AddFavorite from './pages/AddFavorite';
import Favorites from './pages/Favorites';

export default function App() {
  return (
    <div className="container mt-4">
      <nav className="mb-4">
        <Link to="/" className="btn btn-outline-primary me-2">Accueil</Link>
        <Link to="/add" className="btn btn-outline-success me-2">Ajouter un favori</Link>
        <Link to="/favorites" className="btn btn-outline-info">Mes favoris</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add" element={<AddFavorite />} />
        <Route path="/favorites" element={<Favorites />} />
      </Routes>
    </div>
  );
}