#include <iostream>

struct Student {
    char name;
    int num;
    Student *next;
    Student *prev;

    Student(const char n, const int number) {
        num = number;
        name = n;
        next = nullptr;
        prev = nullptr;
    }
};

void addToList(Student **head, char name, int num) {
    auto *newStudent = new Student(name, num);
    Student *current = *head;
    if (*head == nullptr) {
        *head = newStudent;
        return;
    }

    if (current->prev == nullptr && newStudent->num <= current->num) {
        newStudent->next = current;
        current->prev = newStudent;

        *head = newStudent;
        return;
    }

    while (current->next != nullptr) {
        if (current->num <= newStudent->num && current->next->num >= newStudent->num) {
            newStudent->prev = current;
            newStudent->next = current->next;

            current->next->prev = newStudent;
            current->next = newStudent;
            return;
        } else {
            current = current->next;
        }
    }

    newStudent->prev = current;
    current->next = newStudent;
}

void deleteFromList(Student **head, const int num) {
    Student *current = *head;
    if (*head == nullptr) {
        return;
    }

    if (current->prev == nullptr && current->num == num) {
        current->next->prev = nullptr;
        *head = current->next;
        return;
    }

    while (current->next != nullptr) {
        if (current->num == num) {
            Student *prev = current->prev;
            Student *next = current->next;

            current->next->prev = prev;
            current->prev->next = next;
            return;
        } else {
            current = current->next;
        }
    }

    if (current->next == nullptr && current->num == num) {
        current->prev->next = nullptr;
    }
}

Student *find(Student **head, const char name, const int num) {
    Student *current = *head;
    while (current != nullptr) {
        if (current->num == num && current->name == name) {
            return current;
        }
        current = current->next;
    }

    return nullptr;
}

void printList(Student *head) {
    Student *current = head;
    while (current != nullptr) {
        std::cout << "Name: " << current->name << ", Num: " << current->num << std::endl;
        current = current->next;
    }
}

int main() {
    Student *listHead = nullptr;

    addToList(&listHead, *"F", 6);
    addToList(&listHead, *"C", 3);
    addToList(&listHead, *"B", 2);
    addToList(&listHead, *"E", 5);
    addToList(&listHead, *"D", 4);
    addToList(&listHead, *"A", 1);
    printList(listHead);
    printf("---\n");

    deleteFromList(&listHead, 1);
    printList(listHead);
    printf("---\n");

    deleteFromList(&listHead, 6);
    printList(listHead);
    printf("---\n");

    deleteFromList(&listHead, 4);
    printList(listHead);
    printf("---\n");

    Student *student = find(&listHead, *"C", 3);
    std::cout << "Name: " << student->name << ", Num: " << student->num << std::endl;


    std::string action;

    while (action != "ex") {
        std::cout << "Delete ('d') or create ('c')? (exit on 'ex') ";
        std::cin >> action;
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        if (action == "ex") {
            std::cout << "Выход из программы." << std::endl;
        } else {
            switch (action[0]) {
                case 'c':
                    int number;
                    char name;

                    std::cout << "enter name: ";
                    std::cin >> name;
                    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

                    std::cout << "enter num: ";
                    std::cin >> number;
                    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

                    addToList(&listHead, name, number);

                    printList(listHead);
                    break;
                case 'd':
                    std::cout << "enter num: ";
                    std::cin >> number;
                    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

                    deleteFromList(&listHead, number);

                    printList(listHead);
                    break;
                default:
                    std::cout << "Неверное действие. Попробуйте еще раз." << std::endl;
                    break;
            }
        }

    }
    while (action != "ex");


    return 0;
}
