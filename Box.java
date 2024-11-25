public class Box<T> {
    private T item;

    // Метод для размещения объекта в коробке
    public void putItem(T item) throws IllegalStateException {
        if (this.item != null) {
            throw new IllegalStateException("Коробка уже заполнена.");
        }
        this.item = item;
    }

    // Метод для извлечения объекта из коробки
    public T getItem() throws IllegalStateException {
        if (this.item == null) {
            throw new IllegalStateException("Коробка пуста.");
        }
        T itemToReturn = this.item;
        this.item = null; // Обнуляем ссылку на объект
        return itemToReturn;
    }

    // Метод для проверки, заполнена ли коробка
    public boolean isFull() {
        return this.item != null;
    }
}