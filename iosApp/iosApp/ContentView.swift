import UIKit
import SwiftUI
import ComposeApp
import WeatherKit

private let externalRoutes = iOSExternalRoutes()
private let weatherProvider = WKWeatherProvider()

struct ComposeView: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(
            externalRoutes: externalRoutes,
            weatherProvider: weatherProvider,
            mapViewProvider: { (locations: [Location]) -> UIViewController in
                return MapViewProvider().makeMapView(locations: locations)
            }
        )
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @State private var showShareSheet: Bool = false
    @State private var shareSheetContent: String = ""
    
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
            .onAppear(perform: {
                showShareSheet = false
                externalRoutes.onShowShareSheet = { content in
                    shareSheetContent = content
                    showShareSheet = true
                }
            })
            .sheet(isPresented: $showShareSheet, content: {
                ShareSheet(content: shareSheetContent)
            })
    }
}



