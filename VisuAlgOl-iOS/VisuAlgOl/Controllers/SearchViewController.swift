//
//  AlgoViewController.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 05.03.2021.
//

import UIKit
import CoreData

class SearchViewController: UIViewController {
    
    public var info: Info?
    public var item: Item?
    public var context: NSManagedObjectContext!
    private var array = [Int]()
    private var element: Int = 0
    private var timerAction: Algorithm?
    private var timeInterval: Float = 1.0
    
    @IBOutlet var arrayLabels: [UILabel]!
    @IBOutlet weak var searchLabel: UILabel!
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var slider: UISlider!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var taskLabel: UILabel!
    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var checkButtonOutlet: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        for i in 0..<arrayLabels.capacity {
            arrayLabels[i].layer.borderWidth = 1.0
        }
        
        searchLabel.layer.borderWidth = 1.0
        descriptionLabel.text = info!.description
        taskLabel.text = info!.task
        
        let borderColor: UIColor = UIColor(red: 0.85, green: 0.85, blue: 0.85, alpha: 1.0)
        textView.layer.borderWidth = 0.5
        textView.layer.borderColor = borderColor.cgColor
        textView.layer.cornerRadius = 5.0
        
        generate()
    }
    
    @IBAction func sliderValueChanged(_ sender: UISlider) {
        timeInterval = slider.value
        timeLabel.text = "\(timeInterval) sec"
    }
    
    @IBAction func searchButtonPressed(_ sender: UIButton) {
        update()
        timerAction!.startAlgorithm(timeInterval: timeInterval)
    }
    
    @IBAction func generateButtonPressed(_ sender: UIButton) {
        generate()
    }
    
    @IBAction func checkButtonPressed(_ sender: UIButton) {
        item!.done = textView.text == info!.answer
        checkButtonOutlet.backgroundColor = item!.done ? .green : .red
        do {
            try context.save()
        } catch {
            print("Error saving context \(error)")
        }
    }
    
    private func createTimerAction() {
        if timerAction != nil {
            timerAction!.stopAlgorithm()
        }
        switch info!.title {
        case "Линейный поиск":
            timerAction = LinearSearch(view: self,
                                       mainArray: array,
                                       element: element,
                                       timerCounter: -1)
        case "Бинарный поиск":
            timerAction = BinarySearch(view: self,
                                       mainArray: array,
                                       element: element,
                                       timerCounter: -1)
        default:
            timerAction = LinearSearch(view: self,
                                       mainArray: array,
                                       element: element,
                                       timerCounter: -1)
        }
    }
    
    private func makeRandomList(_ n: Int) -> [Int] {
        return (0..<n).map { _ in .random(in: 0..<10) }
    }
    
    private func generate() {
        array = makeRandomList(8)
        if info!.title == "Бинарный поиск" {
            array.sort()
        }
        element = array[.random(in: 0..<array.count)]
        update()
    }
    
    private func update() {
        setColors(colors: [UIColor](repeating: .lightGray, count: array.count),
                  color: .lightGray)
        setText(array: array, element: element)
        createTimerAction()
    }
}

// MARK: - Text field Methods

extension SearchViewController: UITextFieldDelegate {
    
    // чтобы после нажатия на кнопку return закрывалась клавиатура
    func textFieldShouldReturn(_ scoreText: UITextField) -> Bool {
        self.view.endEditing(true)
        return true
    }
}

// MARK: - Work with arrayLabels

extension SearchViewController {
    
    public func setColors(colors: [UIColor], color: UIColor) {
        for i in 0..<arrayLabels.capacity {
            arrayLabels[i].backgroundColor = colors[i]
        }
        searchLabel.backgroundColor = color
    }
    
    public func setText(array: [Int], element: Int) {
        for i in 0..<arrayLabels.capacity {
            arrayLabels[i].text = String(array[i])
        }
        searchLabel.text = String(element)
    }
}
