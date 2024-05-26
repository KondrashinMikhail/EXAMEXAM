package mk.ru.heap;

public class BinaryHeap {
    private final HeapNode[] heapArray; // массив со всеми вершинами
    private final int maxSize; // размер массива
    private int currentSize; // количество узлов массиве

    public BinaryHeap(int maxSize) { // создание пустой пирамиды
        this.maxSize = maxSize;
        this.currentSize = 0;
        heapArray = new HeapNode[maxSize];
    }

    public void printHeap() { // отображение перамиды в консоль
        System.out.println("Массив значений: ");

        for (int n = 0; n < currentSize; n++) {
            if (heapArray[n] != null) {
                System.out.println(heapArray[n].getValue() + " ");
            } else {
                System.out.println("-");
            }
        }
        System.out.println();

        int countOfGaps = 32;
        int itemsPerRow = 1;
        int columnNumber = 0; // номер элемента в данной строке
        String lines = "___________________________________________________________________";
        System.out.println(lines);
        for (int i = 0; i < currentSize; i++) {
            if (columnNumber == 0) {  // проверяем первый элемент ли в текущей строке
                for (int k = 0; k < countOfGaps; k++) { // добавляем предшествующие пробелы
                    System.out.print(' ');
                }
            }
            System.out.print(heapArray[i].getValue());// выводим в консоль значение вершины

            if (++columnNumber == itemsPerRow) { // проверяем последний ли элемент в строке
                countOfGaps /= 2; // уменьшаем количество оступов применяемое для следующей строки
                itemsPerRow *= 2; // указываем, что элементов может быть вдвое больше
                columnNumber = 0; // сбрасываем счётчик для текущего элемента строки
                System.out.println(); // переходим на нову строку
            } else { //переход к следующему элементу
                for (int k = 0; k < countOfGaps * 2 - 2; k++) {
                    System.out.print(' '); // добавляем оступы
                }
            }
        }
        System.out.println("\n" + lines); // нижний пункир
    }

    public void insertNode(int value) { // вставка нового значения
        if (currentSize == maxSize) return; // проверяем не выходим ли мы за рамки массива
        HeapNode newNode = new HeapNode(value);// создание вершины с данным значением
        heapArray[currentSize] = newNode;// вершину задём в самый низ дерева
        displaceUp(currentSize++);// пытаемся поднять вершину, если значение вершины позволяет
    }

    public void removeNode(int index) { // удалить элемент по индексу массива
        if (index > 0 && currentSize > index) {
            HeapNode root = heapArray[index];
            heapArray[index] = heapArray[--currentSize]; // задаём элементу с переданным индексом, значение последнего элемента
            heapArray[currentSize] = null;// последний элемент удаляем
            displaceDown(index);// проталкиваем вниз новый элемент, чтобы он должное ему место
        }
    }

    public void changeNode(int index, int newValue) {
        if (index < 0 || currentSize <= index) {
            return;
        }
        int oldValue = heapArray[index].getValue(); // сохраняем старое значение
        heapArray[index].setValue(newValue); // присваиваем новое

        if (oldValue < newValue) {// если узел повышается
            displaceUp(index);     // выполняется смещение вверх
        } else {                  // если понижается
            displaceDown(index);   // смещение вниз
        }
    }

    private void displaceUp(int index) { //смещение вверх
        int parentIndex = (index - 1) / 2; // узнаем индекс родителя
        HeapNode bottom = heapArray[index]; // берем элемент
        while (index > 0 && heapArray[parentIndex].getValue() < bottom.getValue()) {// если родительский элемент меньше
            heapArray[index] = heapArray[parentIndex];// то меняем его местами с рассматриваемым
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;// берем новый родительский индекс и повторяем сравнение элементов
        }
        heapArray[index] = bottom;// соохраняем результат
    }

    private void displaceDown(int index) {// смещение вниз
        int largerChild;
        HeapNode top = heapArray[index]; // сохранение корня, пока у узла есть хотя бы один потомок
        while (index < currentSize / 2) {// если данное условие не выполняется, то элемент уже в самом низу пирамиды
            int leftChild = 2 * index + 1; // вычисляем индексы в массиве для левого узла ребенка
            int rightChild = leftChild + 1;// и правого

            if (rightChild < currentSize && heapArray[leftChild].getValue() < heapArray[rightChild].getValue()) {
                largerChild = rightChild;
            }// вычисляем ребенка вершину с наибольшим числовым значением
            else {
                largerChild = leftChild;
            }

            if (top.getValue() >= heapArray[largerChild].getValue()) {// если значение вершины больше или равно
                //значени его наибольшего ребенка
                break;// то выходим из метода
            }

            heapArray[index] = heapArray[largerChild];// заменяем вершину, большей дочерней вершиной
            index = largerChild; // текущий индекс переходит вниз
        }
        heapArray[index] = top; // задаем конечное местоположение для элемента
    }
}
