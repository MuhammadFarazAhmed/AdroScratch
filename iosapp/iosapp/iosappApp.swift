//
//  iosappApp.swift
//  iosapp
//
//  Created by Faraz Ahmed on 21/10/2022.
//

import SwiftUI
import shared
import CryptoKit

@main
struct iosappApp: App {
    
    init(){
        KoinModuleKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
    

    
}


