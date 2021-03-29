//
//  InsertionSort.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 28.03.2021.
//

import Foundation
import UIKit

class InsertionSort: Algorithm {
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
        for i in 0..<currentArray.capacity {
            for j in (1...i).reversed() {
                colors[j - 1] = .orange
                colors[j] = .orange
                if time == timerCounter {
                    return
                }
                time += 1

                if currentArray[j - 1] > currentArray[j] {
                    colors[j - 1] = .red
                    colors[j] = .red
                    if time == timerCounter {
                        return
                    }
                    time += 1
                    
                    (currentArray[j - 1], currentArray[j]) = (currentArray[j], currentArray[j - 1])

                    if time == timerCounter {
                        return
                    }
                    time += 1

                    colors[j - 1] = .blue
                    colors[j] = .blue
                    if time == timerCounter {
                        return
                    }
                    time += 1
                } else {
                    colors[j - 1] = .blue
                    colors[j] = .blue
                    if time == timerCounter {
                        return
                    }
                    time += 1
                }
            }
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
