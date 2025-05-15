import axios from 'axios';

const API_KEY = 'AIzaSyC-GNyFBIXpgPQCzlrLBLVbaKCwcN-oGUM';
const BASE_URL = 'https://www.googleapis.com/youtube/v3/search';

export const searchTracks = async (query) => {
  try {
    const response = await axios.get(BASE_URL, {
      params: {
        part: 'snippet',
        q: query,
        type: 'video',
        videoCategoryId: '10', // Musique
        maxResults: 50,
        key: API_KEY
      }
    });

    return response.data.items.map((item) => ({
      id: item.id.videoId,
      title: item.snippet.title,
      artist: item.snippet.channelTitle,
      thumbnail: item.snippet.thumbnails.default.url,
      source: 'YOUTUBE'
    }));
  } catch (error) {
    console.error('Erreur YouTube API', error);
    return [];
  }
};