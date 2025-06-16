//
//  iOSExternalRoutes.swift
//  iosApp
//
//  Created by Scott Keller on 9/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp
import SwiftUI
import MapKit

class iOSExternalRoutes: ExternalRoutes {
    
    var onShowShareSheet: ((_ content: String) -> Void)?
    
    func launchBrowser(url: String) {
        openUrl(url: url)
    }
    
    func launchDialer(phoneNumber: String) {
        openUrl(url: "tel://\(phoneNumber)")
    }
    
    func launchMap(location: Location) {
        openUrl(url: "maps://?saddr=&daddr=\(location.latitude),\(location.longitude)")
    }
    
    func launchShare(content: String) {
        onShowShareSheet?(content)
    }
    
    private func openUrl(url: String) {
        let url = URL(string: url)
        if UIApplication.shared.canOpenURL(url!) {
            UIApplication.shared.open(url!)
        }
    }
}
