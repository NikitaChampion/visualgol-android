//
//  SelectionSort.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 28.03.2021.
//

import Foundation
import UIKit

class SelectionSort: Algorithm {
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
            colors[i] = .red
            if time == timerCounter {
                return
            }
            time += 1
            var pos = i
            for j in i + 1..<currentArray.capacity {
                colors[j] = .orange
                if time == timerCounter {
                    return
                }
                time += 1

                if currentArray[pos] > currentArray[j] {
                    colors[pos] = pos == i ? .brown : .lightGray
                    pos = j

                    colors[j] = .red
                } else {
                    colors[j] = .lightGray
                }
                if time == timerCounter {
                    return
                }
                time += 1
            }

            (currentArray[i], currentArray[pos]) = (currentArray[pos], currentArray[i])

            colors[pos] = .lightGray
            colors[i] = .darkGray

            if time == timerCounter {
                return
            }
            time += 1
        }
        timerCounter -= 1
    }
}
