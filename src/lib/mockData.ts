export interface Destination {
  id: string;
  name: string;
  description: string;
  imageUrl: string;
  location: {
    latitude: number;
    longitude: number;
  };
  rating: number;
  priceLevel: number;
  tags: string[];
}

export const mockDestinations: Destination[] = [
  {
    id: '1',
    name: 'New York City',
    description: 'The city that never sleeps. Experience the vibrant energy of Manhattan, iconic landmarks, and world-class entertainment.',
    imageUrl: '/images/new-york.jpg',
    location: {
      latitude: 40.7128,
      longitude: -74.0060
    },
    rating: 4.8,
    priceLevel: 4,
    tags: ['urban', 'culture', 'shopping', 'food']
  },
  {
    id: '2',
    name: 'Paris',
    description: 'The City of Light. Discover romantic architecture, exquisite cuisine, and timeless art.',
    imageUrl: '/images/paris.jpg',
    location: {
      latitude: 48.8566,
      longitude: 2.3522
    },
    rating: 4.9,
    priceLevel: 4,
    tags: ['culture', 'romance', 'food', 'art']
  },
  {
    id: '3',
    name: 'Tokyo',
    description: 'Where tradition meets innovation. Experience the perfect blend of ancient culture and cutting-edge technology.',
    imageUrl: '/images/tokyo.jpg',
    location: {
      latitude: 35.6762,
      longitude: 139.6503
    },
    rating: 4.7,
    priceLevel: 4,
    tags: ['culture', 'technology', 'food', 'shopping']
  }
]; 