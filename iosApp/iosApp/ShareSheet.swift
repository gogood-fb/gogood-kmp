//
//  ShareSheet.swift
//  iosApp
//
//  Created by Scott Keller on 9/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI

struct ShareSheet: UIViewControllerRepresentable {
    var content: String
    
    func makeUIViewController(context: Context) -> UIViewController {
        var excludedActivityTypes: [UIActivity.ActivityType]? = nil
        
        let controller = UIActivityViewController(
            activityItems: [content],
            applicationActivities: nil)
        
        controller.excludedActivityTypes = excludedActivityTypes
        
        return controller
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
