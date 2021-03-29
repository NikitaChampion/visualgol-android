//
//  AlgorithmsListViewController.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 28.02.2021.
//

import UIKit
import CoreData

class AlgorithmsListViewController: UITableViewController, NSFetchedResultsControllerDelegate {
    
    // TODO: вынести Item из внутренних файлов
    private var itemArray = [Item]()
    private var infoArray = [
        Info(title: "Сортировка",
             description: "Сортировка — последовательное расположение или разбиение на группы чего-либо в зависимости от выбранного критерия. Часто сортировка предпринимается для того, чтобы облегчить последующий поиск элементов в отсортированном множестве. К примеру, людей в школе на уроках физкультуры и в армии строят по росту, чтобы соблюдался порядок.\nВыше представлен простейший учебный алгоритм сортировки низкой эффективности: он проходит по искомому массиву, сравнивает соседние элементы и, если порядок в их паре неверный, меняет их и возвращается в начало массива, после чего повторяет все те же действия. Алгоритм завершается, если во время полного обхода он не обнаруживает ни одной неотсортированной пары.",
             task: "Отсортируйте следующий массив чисел по возрастанию:\n2 1 3",
             answer: "1 2 3",
             level: "Easy"),
        Info(title: "Сортировка пузырьком",
             description: "Сортировка пузырьком — простой алгоритм сортировки, который на каждом шаге обходит массив от начала до конца, сравнивая значения соседних ячеек и меняя их, если порядок в их паре неверный. Таким образом, в результате каждого прохода как минимум один элемент (максимальный среди неотсортированных) встаёт на своё место. Этот алгоритм считается учебным и практически не применяется вне учебной литературы, так как эффективен лишь для небольших массивов. Вместо него на практике применяются более эффективные алгоритмы сортировки.",
             task: "Определите, сколько обменов сделает алгоритм пузырьковой сортировки по возрастанию для данного массива:\n4 1 5 3",
             answer: "3",
             level: "Easy"),
        Info(title: "Сортировка выбором",
             description: "Сортировка выбором — простой алгоритм сортировки, который обходит массив от начала до конца, на каждом i-ом шаге с помощью линейного поиска находит i-ый минимальный элемент и меняет его местами с первым элементом в неотсортированной части массива (i-ым элементом). Таким образом будет получен массив, отсортированный по неубыванию.",
             task: "Определите, как будет выглядеть следующий массив после двух обменов сортировки выбором:\n4 1 2 3",
             answer: "1 2 4 3",
             level: "Easy"),
        Info(title: "Сортировка вставками",
             description: "Сортировка вставками — алгоритм сортировки, в котором элементы входной последовательности просматриваются по одному, и каждый новый поступивший элемент с помощью линейного поиска размещается в подходящее место среди ранее упорядоченных элементов.",
             task: "Определите, как будет выглядеть следующий массив после двух обменов сортировки вставками:\n4 3 2 1",
             answer: "3 4 2 1",
             level: "Easy"),
        Info(title: "Линейный поиск",
            description: "Линейный поиск — алгоритм нахождения заданного значения произвольной функции (в том числе и значения массива) на некотором отрезке, осуществляемый путём последовательного сравнения очередного рассматриваемого значения с искомым до тех пор, пока эти значения не совпадут (с той или иной точностью). Данный алгоритм является простейшим алгоритмом поиска и, в отличие, например, от бинарного поиска, не накладывает никаких ограничений на функцию и имеет простейшую реализацию.",
            task: "Определите, сколько действий потребуется, чтобы линейным поиском в массиве\n7 2 5 1 8 4 3 8\nнайти элемент\n8",
            answer: "5",
            level: "Easy"),
        Info(title: "Бинарный поиск",
            description: "Бинарный поиск — алгоритм поиска значения отсортированного массива, осуществляемый путём дробления массива на половины. Его процедуру можно описать следующим образом:\n Мы определяем значение элемента посередине, между границами, и сравниваем его с искомым. Если искомое значение больше значения середины, то мы сужаем область поиска так, чтобы новая левая граница была равна индексу середины предыдущей области. В противном случае присваиваем это значение правой границе. Проделываем эту процедуру до тех пор, пока правая граница больше левой более чем на 1.",
            task: "Определите, сколько действий потребуется, чтобы бинарным поиском в массиве\n1 2 3 4 5 6 7 8\nнайти элемент\n8",
            answer: "4",
            level: "Medium")
    ]
    private let context = CoreDataContainer().context
    private var predicate : NSPredicate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if UserDefaults.standard.object(forKey: "firstOpen") == nil {
            UserDefaults.standard.set(true, forKey: "firstOpen")
            for i in 0..<infoArray.count {
                let itemToSave = Item(context: context)
                itemToSave.done = false
                itemToSave.id = Int32(i)
                itemArray.append(itemToSave)
            }
        }
        
        self.tableView.register(UINib(nibName: String(describing: TableViewCell.self), bundle: nil), forCellReuseIdentifier: "AlgoItemCell")
        
        loadItems()
        saveItems()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        loadItems()
    }
    
    @IBAction func doneOnlyTapped(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 2 {
            predicate = NSPredicate(format: "done = %@", "0")
        } else if sender.selectedSegmentIndex == 1 {
            predicate = NSPredicate(format: "done = %@", "1")
        } else {
            predicate = nil
        }
        
        loadItems()
    }
    
    
    // MARK: - Navigation
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "showToEdit") {
            
            let destinationVC = segue.destination as! AlgoViewController
            
            destinationVC.hidesBottomBarWhenPushed = true
            
            destinationVC.context = context
            let item = itemArray[(sender as! IndexPath).row]
            destinationVC.info = infoArray[Int(item.id)]
            destinationVC.item = itemArray[(sender as! IndexPath).row]
        } else {
            
            let destinationVC = segue.destination as! SearchViewController
            
            destinationVC.hidesBottomBarWhenPushed = true
            
            destinationVC.context = context
            let item = itemArray[(sender as! IndexPath).row]
            destinationVC.info = infoArray[Int(item.id)]
            destinationVC.item = itemArray[(sender as! IndexPath).row]
        }
    }
    
    
    // MARK: - private functionality
    
    private func loadItems() {
        let request: NSFetchRequest<Item> = Item.fetchRequest()
        
        request.sortDescriptors = [NSSortDescriptor(key: "id", ascending: true)]
        
        if let predicateUnwrapped = predicate {
            request.predicate = predicateUnwrapped
        }
        
        do {
            itemArray = try context.fetch(request)
            // print(itemArray.count)
            
            tableView.reloadData()
        } catch {
            print("Error fetching data from contect \(error)")
        }
    }
    
    private func saveItems() {
        do {
            try context.save()
        } catch {
            print("Error saving context \(error)")
        }
        
        tableView.reloadData()
    }
    
    
    // MARK: - TableView Datasource Methods
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return itemArray.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "AlgoItemCell", for: indexPath) as! TableViewCell
        
        let item = itemArray[indexPath.row]
        
        cell.accessoryType = item.done ? .checkmark : .none
        
        cell.titleLabel!.text = infoArray[Int(item.id)].title
        
        let level = infoArray[Int(item.id)].level
        cell.levelLabel!.text = level
        if level == "Easy" {
            cell.levelLabel!.textColor = UIColor(red: 0, green: 0.6471, blue: 0.149, alpha: 1.0)
        } else if level == "Medium" {
            cell.levelLabel!.textColor = .orange
        } else {
            cell.levelLabel!.textColor = .red
        }
        
        return cell
    }
    
    
    // MARK: - TableView Delegate Methods
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let item = itemArray[indexPath.row]
        if item.id <= 2 {
            performSegue(withIdentifier: "showToEdit", sender: indexPath)
        } else {
            performSegue(withIdentifier: "showToEdit2", sender: indexPath)
        }
        tableView.deselectRow(at: indexPath, animated: true)
    }
}

// MARK: - Search bar Methods

extension AlgorithmsListViewController: UISearchBarDelegate {
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        predicate = NSPredicate(format: "title CONTAINS[cd] %@", searchBar.text!)
        
        loadItems()
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if searchBar.text?.count == 0 {
            predicate = nil
            loadItems()
            
            DispatchQueue.main.async {
                searchBar.resignFirstResponder()
            }
        }
    }
}
