import { Destination, mockDestinations } from './mockData';

export async function getDestinations(): Promise<Destination[]> {
  // Simulate API delay
  await new Promise(resolve => setTimeout(resolve, 500));
  return mockDestinations;
}

export async function getDestinationById(id: string): Promise<Destination | null> {
  // Simulate API delay
  await new Promise(resolve => setTimeout(resolve, 300));
  return mockDestinations.find(dest => dest.id === id) || null;
} 