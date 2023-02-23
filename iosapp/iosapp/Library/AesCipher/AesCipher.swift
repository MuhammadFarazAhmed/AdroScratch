//
//  AesCipher.swift
//  iosapp
//
//  Created by Faraz Ahmed on 20/02/2023.
//

import Foundation
import CryptoKit


@_implementationOnly import shared

class CryptoServiceIOS : CryptoService {
    
    override func encrypt(plainText: String, key: KotlinByteArray, iv: KotlinByteArray) -> KotlinByteArray {
        let keyData = SymmetricKey(data: Data(key))
        let nonceData = try! AES.GCM.Nonce(data: Data(iv))
        let sealedBox = try! AES.GCM.seal(Data(data), using: keyData, nonce: nonceData)
        return [UInt8](sealedBox.ciphertext)
    }

    override func decrypt(cipherText: KotlinByteArray, key: KotlinByteArray, iv: KotlinByteArray) -> String {
        let keyData = SymmetricKey(data: Data(key))
        let nonceData = try! AES.GCM.Nonce(data: Data(iv))
        let sealedBox = try! AES.GCM.SealedBox(nonce: nonceData, ciphertext: Data(data))
        let decryptedData = try! AES.GCM.open(sealedBox, using: keyData)
        return [UInt8](decryptedData)
    }
    
}





