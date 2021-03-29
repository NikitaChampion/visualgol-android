//
//  LinearSearch.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 28.03.2021.
//

import Foundation
import UIKit

class LinearSearch: Algorithm {
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
        for i in 0..<currentArray.capacity {
            colors[i] = .orange
            color = .orange
            if time == timerCounter {
                return
            }
            time += 1
            
            if currentArray[i] == element {
                colors[i] = .red
                color = .red
                break
            }
            
            colors[i] = .darkGray
            color = .lightGray
            if time == timerCounter {
                return
            }
            time += 1
        }
    }
}
