//
//  Token.swift
//  ChoreNinja
//
//  Created by Eric Loew on 9/28/19.
//  Copyright © 2019 erl. All rights reserved.
//
import Foundation
import UIKit
import SwiftJWT
import SharedCode


class Token {
    
    public func getToken(email: String)-> String {
        let header = Header(kid: ConstantsShared().secret )
        let claims = MyClaims(iss: ConstantsShared().jwtIssuer, sub: ConstantsShared().jwtSubject, exp: Date(timeIntervalSinceNow: 60), admin: true, aud: ConstantsShared().jwtAudience, email: email)
        //   .claim("userId", settings.getUserId())  // + "zztop"
        let jwt = JWT(header: header, claims: claims)
        
        //print("jwt: \(jwt)")
        
        var jwtString: String? = nil
        
        do {
            let data: Data? = ConstantsShared().secret.data(using: .utf8) // non-nil
            let jwtSigner = JWTSigner.hs256(key: data!)
            let jwtEncoder = JWTEncoder(jwtSigner: jwtSigner)
            jwtString = try jwtEncoder.encodeToString(jwt)
             //let signedJWT = try jwt.sign(using: jwtSigner)
    
        } catch  {
       
        }

        return jwtString ?? ""
    }
}

struct MyClaims: Claims {
    let iss: String
    let sub: String
    let exp: Foundation.Date
    let admin: Bool
    let aud: String
    let email: String
}