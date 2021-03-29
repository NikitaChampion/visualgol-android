//
//  Sort.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 29.03.2021.
//

import Foundation

class Algorithm {
    public var timer: Timer?
    
    func startAlgorithm(timeInterval: Float) {
        timer = Timer.scheduledTimer(timeInterval: TimeInterval(timeInterval), target: self, selector: #selector(algorithm), userInfo: nil, repeats: true)
    }
    
    func stopAlgorithm() {
        timer?.invalidate()
    }
    
    @objc
    func algorithm() {
    }
}
