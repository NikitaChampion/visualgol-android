//
//  StupidSort.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 28.03.2021.
//

import Foundation
import UIKit

class StupidSort: Algorithm {
    private var view: AlgoViewController
    private var mainArray: [Int]
    private var currentArray: [Int]
    private var colors: [UIColor]
    private var time: Int
    private var timerCounter: Int
    
    init(view: AlgoViewController,
         mainArray: [Int],
         timerCounter: Int) {
        self.view = view
        self.mainArray = mainArray
        self.currentArray = mainArray
        self.colors = [UIColor](repeating: .lightGray, count: mainArray.count)
        self.timerCounter = timerCounter
        self.time = -1
    }
    
    @objc
    override func algorithm() {
        time = -1
        currentArray = mainArray
        colors = [UIColor](repeating: .lightGray, count: mainArray.count)
        
        sort()
        
        view.setColors(colors: colors)
        view.setText(array: currentArray)
        timerCounter += 1
    }
    
    func sort() {
        if time == timerCounter {
            return
        }
        time += 1
        var i = 1
        while i < currentArray.capacity {
            colors[i - 1] = .orange
            colors[i] = .orange
            if time == timerCounter {
                return
            }
            time += 1

            if currentArray[i - 1] > currentArray[i] {
                colors[i - 1] = .red
                colors[i] = .red
                if time == timerCounter {
                    return
                }
                time += 1

                (currentArray[i - 1], currentArray[i]) = (currentArray[i], currentArray[i - 1])

                colors[i - 1] = .lightGray
                colors[i] = .lightGray
                if time == timerCounter {
                    return
                }
                time += 1
                i = 0
            } else {
                colors[i - 1] = .lightGray
                colors[i] = .lightGray
                if time == timerCounter {
                    return
                }
                time += 1
            }
            i += 1
        }
        for i in 0..<currentArray.capacity {
            colors[i] = .darkGray
        }
        if time == timerCounter {
            return
        }
        timerCounter -= 1
    }
}
