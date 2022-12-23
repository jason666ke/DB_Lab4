package data;

public class Tuple {

    /**
     * 二元组
     * @param <E> 元组左元素
     * @param <T> 元组右元素
     */
    public static class Pair<E, T> {
        private final E id;
        private final T Element;

        Pair(E id, T Element) {
            this.id = id;
            this.Element = Element;
        }

        public E getId() {
            return id;
        }

        public T getElement() {
            return Element;
        }
    }

    /**
     * 三元组
     * @param <E> 元组左元素
     * @param <T> 元组中元素
     * @param <V> 元组右元素
     */
    public static class Triplet<E, T, V> {
        private final E id;
        private final T leftElement;
        private final V rightElement;

        Triplet(E id, T leftElement, V rightElement) {
            this.id = id;
            this.leftElement = leftElement;
            this.rightElement = rightElement;
        }

        public E getId() {
            return id;
        }

        public T getLeftElement() {
            return leftElement;
        }

        public V getRightElement() {
            return rightElement;
        }
    }

    /**
     * 四元组
     * @param <E> 左一元素
     * @param <T> 左二元素
     * @param <V> 右一元素
     * @param <K> 右二元素
     */
    public static class Quartet<E, T, V, K> {
        private final E id;
        private final K name;
        private final T leftElement;
        private final V rightElement;

        Quartet(E id, K name,T leftElement, V rightElement) {
            this.id = id;
            this.name = name;
            this.leftElement = leftElement;
            this.rightElement = rightElement;
        }

        public E getId() {
            return id;
        }

        public T getLeftElement() {
            return leftElement;
        }

        public V getRightElement() {
            return rightElement;
        }

        public K getName() {
            return name;
        }
    }
}
