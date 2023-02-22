//
//  AesCipher.swift
//  iosapp
//
//  Created by Faraz Ahmed on 20/02/2023.
//

import Foundation
import CryptoKit

class AesCipher {
    func encrypt(plainText: String, key: Data, iv: Data) -> Data? {
        do {
            let key = SymmetricKey(data: key)
            let iv = try! AES.GCM.Nonce(data: iv)
            let data = Data(plainText.utf8)
            let sealedBox = try AES.GCM.seal(data, using: key, nonce: iv)
            return sealedBox.combined
        } catch {
            print("Error: \(error)")
            return nil
        }
    }
    
    func decrypt(cipherText: Data, key: Data, iv: Data) -> String? {
        do {
            let key = SymmetricKey(data: key)
            let iv = try! AES.GCM.Nonce(data: iv)
            let sealedBox = try AES.GCM.SealedBox(combined: cipherText)
            let decryptedData = try AES.GCM.open(sealedBox, using: key)
            return String(decoding: decryptedData, as: UTF8.self)
        } catch {
            print("Error: \(error)")
            return nil
        }
    }
}
