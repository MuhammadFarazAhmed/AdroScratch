//
//  MySwiftClass.swift
//  iosapp
//
//  Created by Faraz Ahmed on 20/02/2023.
//

import Foundation


@objc public class MySwiftClass: NSObject {
  @objc public func createCallback() -> ((String) -> String) {
    return { input in
      // Do something with the input and return a result
      return "Result from Swift code: \(input)"
    }
  }
}
