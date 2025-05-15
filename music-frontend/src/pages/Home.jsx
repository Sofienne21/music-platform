import { useState } from 'react';
import { searchTracks } from '../services/youtubeService';
import { addFavorite } from '../services/api';

export default function Home() {
  const [query, setQuery] = useState('');
  const [tracks, setTracks] = useState([]);
  const [userId, setUserId] = useState('');

  const handleSearch = async () => {
    const results = await searchTracks(query);
    setTracks(results);
  };

  const handleAddFavorite = async (track) => {
    if (!userId) {
      alert("Merci de saisir l'ID utilisateur.");
      return;
    }

    try {
      await addFavorite(userId, track);
      alert('Ajouté aux favoris !');
    } catch (err) {
      console.error(err);
      alert('Erreur lors de l’ajout du favori.');
    }
  };

  return (
    <div>
      <h2>Rechercher un morceau 🎵</h2>

      <div className="row mb-4">
        <div className="col-md-4">
          <input
            type="text"
            className="form-control"
            placeholder="ID utilisateur"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
        </div>

        <div className="col-md-6">
          <input
            type="text"
            className="form-control"
            placeholder="Recherche YouTube"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
        </div>

        <div className="col-md-2">
          <button className="btn btn-primary w-100" onClick={handleSearch}>
            Rechercher
          </button>
        </div>
      </div>

      <div className="row">
        {tracks.map((track) => (
          <div className="col-md-6 mb-3" key={track.id}>
            <div className="card">
              <div className="card-body d-flex align-items-center">
                <img src={track.thumbnail} alt="miniature" className="me-3" />
                <div className="flex-grow-1">
                  <h5>{track.title}</h5>
                  <p className="mb-1"><small>{track.artist}</small></p>
                </div>
                <button
                  className="btn btn-outline-success"
                  onClick={() => handleAddFavorite(track)}
                >
                  + Favori
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
