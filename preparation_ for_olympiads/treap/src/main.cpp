#include <iostream>
#include <vector>
#include <utility>
#include <random>

using namespace std;

default_random_engine gen;
uniform_real_distribution dist(0.0, 1.0);

// Генерирует случайное число в промежутке [0.0, 1.0)
double random_double()
{
    return dist(gen);
}


// Функции и структуры для декартова дерева
namespace treap
{

// Вершина в декартовом дереве
struct Node
{
    // Конструктор: создаёт вершину с заданным ключом и случайным приоритетом
    Node(int x): x(x)
    {
        y = random_double();
    }

    Node* left = nullptr;   // Левый ребёнок
    Node* right = nullptr;  // Правый ребёнок
    int x;                  // Ключ
    double y;               // Приоритет
};


// Слияние двух деревьев
// Требования: дерево b полностью лежит строго правее, чем дерево a
Node* merge(Node* a, Node* b)
{
    // База рекурсии: merge(a, пустое дерево) = a
    // Пустое дерево обозначается нулевым указателем (nullptr)
    if (a == nullptr) {
        return b;
    }
    if (b == nullptr) {
        return a;
    }

    // Сравниваем приоритеты в корнях деревьев a и b
    if (a->y > b->y) {
        // Случай 1: в корне a приоритет больше, чем в корне b.
        // Значит корень дерева a будет корнем объединения деревьев a и b.
        // Левым потомком результирующего корня останется левое поддерево a (как и было до этого).
        // Правым потомком результирующего корня станет объединение правого поддерева a и дерева b.
        Node* m = merge(a->right, b);
        a->right = m;
        return a;
    } else {
        // Случай 2: в корне b приоритет больше, чем в корне a.
        // Случай противположен первому.
        // Корень дерева b будет корнем объединения деревьев a и b.
        // Правым потомком результирующего корня останется правое поддерево b.
        // Левым потомком результирующего корня станет объединение дерева a и левого поддерева a.
        Node* m = merge(a, b->left);
        b->left = m;
        return b;
    }
}

// Разделение дерева на два поддерева.
// Первое возвращаемое дерево будет иметь все ключи, меньшие или равные x,
// а второе — строго большие x.
pair<Node*, Node*> split(Node* a, int x)
{
    // База рекурсии: split(пустое дерево, x) = два пустых дерева (для любого x)
    if (a == nullptr) {
        return {nullptr, nullptr};
    }

    // Смотрим, в каком дереве будет лежать исходный корень
    if (a->x <= x) {
        // Первое (левое) поддерево
        // Значит, x лежит где-то справа от корня, и мы рекурсивно запускаемся от правого поддерева a
        auto [left_split, right_split] = split(a->right, x);
        a->right = left_split;
        return {a, right_split};
    } else {
        // Второе (правое) поддерево
        // Мне лень писать комментарии, помогите
        auto [left_split, right_split] = split(a->left, x);
        a->left = right_split;
        return {left_split, a};
    }
}

// Добавляет элемент с заданным ключом к дереву
// Если передан nullptr, работает корректно
Node* add(Node* a, int x)
{
    Node* to_insert = new Node(x);
    // left_and_mid содержит элементы с ключом ≤ x
    // right — с ключом > x
    auto [left_and_mid, right] = split(a, x);

    // left_subtree содержит все элементы с ключом ≤ x, включая только что добавленный элемент
    Node* left_subtree = merge(left_and_mid, to_insert);
    // full_tree содержит все элементы дерева, включая добавленный
    Node* full_tree = merge(left_subtree, right);
    return full_tree;
}

// Выполняет поиск по ключу и возвращает true, если такой ключ существует, и false в противном случае
bool has_key(Node* a, int x)
{
    // Реально лень писать комментарии, но, короче, это просто поиск в бинарном дереве поиска
    if (a == nullptr) {
        return false;
    }
    if (a->x == x) {
        return true;
    } else if (a->x < x) {
        return has_key(a->right, x);
    } else /* a->x > x */ {
        return has_key(a->left, x);
    }
}


// Удаляет (под)дерево, освобождая память
void delete_tree(Node* a)
{
    if (a == nullptr) {
        return;
    }
    delete_tree(a->left);
    delete_tree(a->right);
    delete a;
}

// Удаляет все элементы с заданным ключом из дерева
Node* remove(Node* a, int x)
{
    // left_and_mid содержит элементы с ключом ≤ x
    // right — с ключом > x
    auto [left_and_mid, right] = split(a, x);

    // left содержит элементы с ключом < x
    // mid — с ключом == x
    auto [left, mid] = split(left_and_mid, x - 1); // TODO: работает только для целого ключа

    delete_tree(mid);

    auto full_tree = merge(left, right);
    return full_tree;
}

} // namespace treap


void print(treap::Node* a, int nest = 0)
{
    string indent(nest * 4, ' ');
    if (a == nullptr) {
        cout << indent << "<empty tree>" << endl;
        return;
    }
    cout << indent << "x = " << a->x << ", " << "y = " << a->y << endl;
    print(a->left, nest + 1);
    print(a->right, nest + 1);
}


int main()
{
    cout << boolalpha; // Вывод true/false вместо 1/0
    treap::Node* root = nullptr;

    // Добавляем в ДД элементы (они могут повторяться)
    root = treap::add(root, 1);
    root = treap::add(root, 4);
    root = treap::add(root, 6);
    root = treap::add(root, 3);
    root = treap::add(root, 2);
    root = treap::add(root, 5);
    root = treap::add(root, 2);

    cout << "====== TREE AFTER INSERTION ======" << endl;
    print(root);
    cout << endl;

    // Несколько тестов
    cout << treap::has_key(root, 3) << endl;   // true
    cout << treap::has_key(root, 2) << endl;   // true
    cout << treap::has_key(root, -10) << endl; // false
    cout << treap::has_key(root, 8) << endl;   // false

    // Удаляем из ДД некоторые элементы
    root = treap::remove(root, 1);
    root = treap::remove(root, 2);
    cout << "====== TREE AFTER DELETION ======" << endl;
    print(root);
    cout << endl;

    // Несколько тестов
    cout << treap::has_key(root, 3) << endl;   // true
    cout << treap::has_key(root, 2) << endl;   // false
    cout << treap::has_key(root, -10) << endl; // false
    cout << treap::has_key(root, 8) << endl;   // false

    // Освобождаем память, выделенную под дерево
    treap::delete_tree(root);
}
