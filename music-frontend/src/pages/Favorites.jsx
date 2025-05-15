import { useState } from 'react';
import { getFavorites, deleteFavorite } from '../services/api';

export default function Favorites() {
    const [userId, setUserId] = useState('');
    const [favorites, setFavorites] = useState([]);

    const loadFavorites = async () => {
        try {
            const response = await getFavorites(userId);
            setFavorites(response.data);
        } catch (error) {
            console.error('Erreur chargement favoris', error);
            alert("Erreur lors de la récupération.");
        }
    };

    const handleDelete = async (favoriteId) => {
        try {
            await deleteFavorite(favoriteId);
            setFavorites(favorites.filter(fav => fav.id !== favoriteId));
        } catch (err) {
            console.error(err);
            alert("Erreur lors de la suppression.");
        }
    };

    return (
        <div>
            <h2>Mes favoris 🎧</h2>

            <div className="mb-3">
                <label>Entrer l'ID utilisateur :</label>
                <input
                    type="number"
                    className="form-control"
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                />
                <button className="btn btn-primary mt-2" onClick={loadFavorites}>
                    Charger mes favoris
                </button>
            </div>

            {favorites.map((fav) => (
                fav.track ? (
                    <div className="col-md-6 mb-4" key={fav.id}>
                        <div className="card">
                            <div className="card-body">
                                <h5>{fav.track.title}</h5>
                                <p>{fav.track.artist} - {fav.track.source}</p>
                                <div className="ratio ratio-16x9 mb-2">
                                    <iframe
                                        src={`https://www.youtube.com/embed/${fav.track.id}`}
                                        title={fav.track.title}
                                        allowFullScreen
                                    ></iframe>
                                </div>
                                <button
                                    className="btn btn-danger"
                                    onClick={() => handleDelete(fav.id)}
                                >
                                    Supprimer
                                </button>
                            </div>
                        </div>
                    </div>
                ) : null
            ))}
        </div>
    );
}
