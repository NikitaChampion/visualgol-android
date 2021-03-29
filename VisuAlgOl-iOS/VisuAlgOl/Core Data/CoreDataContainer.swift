//
//  CoreDataContainer.swift
//  VisuAlgOl
//
//  Created by Никита Игумнов on 05.03.2021.
//

import Foundation
import CoreData

class CoreDataContainer {
    
    lazy var persistentContainer: NSPersistentContainer = {
        
        let container = NSPersistentContainer(name: "DataModel")
        container.loadPersistentStores(completionHandler: { (storeDescription, error) in
            if let error = error as NSError? {
                fatalError("Unresolved error \(error), \(error.userInfo)")
            }
        })
        return container
    }()
    
    var context: NSManagedObjectContext{
        return persistentContainer.viewContext
    }
}
