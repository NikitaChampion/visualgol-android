//
//  BinarySearch.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 28.03.2021.
//

import Foundation
import UIKit

class BinarySearch: Algorithm {
    private var view: SearchViewController
    private var mainArray: [Int]
    private var currentArray: [Int]
    private var element: Int
    private var colors: [UIColor]
    private var color: UIColor
    private var time: Int
    private var timerCounter: Int
    
    init(view: SearchViewController,
         mainArray: [Int],
         element: Int,
         timerCounter: Int) {
        self.view = view
        self.mainArray = mainArray
        self.element = element
        self.currentArray = mainArray
        self.colors = [UIColor](repeating: .lightGray, count: mainArray.count)
        self.color = .lightGray
        self.timerCounter = timerCounter
        self.time = -1
    }
    
    @objc
    override func algorithm() {
        time = -1
        currentArray = mainArray
        colors = [UIColor](repeating: .lightGray, count: mainArray.count)
        
        sort()
        
        view.setColors(colors: colors,
                       color: color)
        view.setText(array: currentArray,
                     element: element)
        timerCounter += 1
    }
    
    func sort() {
        if time == timerCounter {
            return
        }
        time += 1
        var l = -1, r = currentArray.count
        while l < r - 1 {
            let mid = (l + r) / 2
            colors[mid] = .orange
            color = .orange
            if time == timerCounter {
                return
            }
            time += 1
            
            if currentArray[mid] < element {
                for i in l + 1...mid {
                    colors[i] = .white
                }
                l = mid
            } else if currentArray[mid] > element {
                for i in mid..<r {
                    colors[i] = .white
                }
                r = mid
            } else {
                colors[mid] = .red
                color = .red
                break
            }
        }
    }
}
