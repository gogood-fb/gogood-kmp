//
//  MapKitMapProvider.swift
//  iosApp
//
//  Created by Scott Keller on 9/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp
import UIKit
import SwiftUI
import MapKit

class MapViewProvider {
    
    func makeMapView(locations: [Location]) -> UIViewController {
        return UIHostingController(rootView: MapView(locations: locations))
    }
    
    private struct MapView: View {
        var locations: [Location]
        @State private var points: [Point]
        @State private var bounds: MapCameraBounds
        
        init(locations: [Location]) {
            self.locations = locations
            
            let points = locations.enumerated().map { (index, element) in
                return Point(name: "\(index)", coordinate: CLLocationCoordinate2D(
                    latitude: Double(element.latitude),
                    longitude: Double(element.longitude)))
            }
            
            let transformed = points.map{$0.to360()}
            
            let paddingFactor = 1.2
            let minLat = transformed.min { $0.latitude < $1.latitude }!.latitude
            let maxLat = transformed.max { $0.latitude < $1.latitude }!.latitude
            let minLon = transformed.min { $0.longitude < $1.longitude }!.longitude
            let maxLon = transformed.max { $0.longitude < $1.longitude }!.longitude
            
            let span = MKCoordinateSpan(
                latitudeDelta: (maxLat - minLat) * paddingFactor,
                longitudeDelta: (maxLon - minLon) * paddingFactor)
            
            let center = inverseTransform(c: CLLocationCoordinate2DMake(
                (maxLat - span.latitudeDelta / 2),
                maxLon - span.longitudeDelta / 2))
            
            let region = MKCoordinateRegion(center: center, span: span)
            
            self.points = points
            self.bounds = MapCameraBounds(centerCoordinateBounds: region)
        }
        
        var body: some View {
            Map(bounds: bounds){
                ForEach($points) {$point in
                    Annotation(
                        coordinate: point.coordinate,
                        content: {
                            Text(point.name)
                                .frame(width: 20, height: 20, alignment: .center)
                                .foregroundColor(.white)
                                .font(.system(size: 10))
                                .background(.red)
                                .clipShape(Circle())
                        },
                        label: {Text(point.name)})
                    
                }
            }
            .mapControlVisibility(.hidden)
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .ignoresSafeArea(.all)
        }
    }
    
    struct Point: Identifiable {
        let id = UUID()
        let name: String
        let coordinate: CLLocationCoordinate2D
        
        // Latitude -180...180 -> 0...360
        func to360() -> CLLocationCoordinate2D {
            if coordinate.longitude < 0 { return CLLocationCoordinate2DMake(coordinate.latitude, 360 + coordinate.longitude) }
            return coordinate
        }
    }
    
    // Latitude 0...360 -> -180...180
    private static func inverseTransform(c: CLLocationCoordinate2D) -> CLLocationCoordinate2D {
        if c.longitude > 180 { return CLLocationCoordinate2DMake(c.latitude, -360 + c.longitude) }
        return c
    }
}
