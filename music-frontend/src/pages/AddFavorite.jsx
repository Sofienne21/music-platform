import { useState } from 'react';
import { addFavorite } from '../services/api';

export default function AddFavorite() {
  const [userId, setUserId] = useState('');
  const [track, setTrack] = useState({
    id: '',
    title: '',
    artist: '',
    source: ''
  });

  const handleChange = (e) => {
    setTrack({
      ...track,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addFavorite(userId, track);
      alert('Favori ajouté avec succès !');
    } catch (err) {
      console.error(err);
      alert('Erreur lors de l’ajout du favori.');
    }
  };

  return (
    <div>
      <h2>Ajouter un favori</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-2">
          <label>ID utilisateur :</label>
          <input type="number" className="form-control" value={userId} onChange={(e) => setUserId(e.target.value)} required />
        </div>

        <div className="mb-2">
          <label>ID du morceau (YouTube/Spotify) :</label>
          <input type="text" name="id" className="form-control" value={track.id} onChange={handleChange} required />
        </div>

        <div className="mb-2">
          <label>Titre :</label>
          <input type="text" name="title" className="form-control" value={track.title} onChange={handleChange} required />
        </div>

        <div className="mb-2">
          <label>Artiste :</label>
          <input type="text" name="artist" className="form-control" value={track.artist} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label>Source :</label>
          <input type="text" name="source" className="form-control" value={track.source} onChange={handleChange} required />
        </div>

        <button type="submit" className="btn btn-success">Ajouter</button>
      </form>
    </div>
  );
}