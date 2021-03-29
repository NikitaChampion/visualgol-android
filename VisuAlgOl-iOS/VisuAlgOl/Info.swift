//
//  Info.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 28.03.2021.
//

import Foundation

class Info {
    public let title: String
    public let description: String
    public let task: String
    public let answer: String
    public let level: String
    
    init(title: String, description: String, task: String, answer: String, level: String) {
        self.title = title
        self.description = description
        self.task = task
        self.answer = answer
        self.level = level
    }
}
