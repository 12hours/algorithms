package classes.sortings;

/**
 * Created by nm on 20.5.17.
 */
public class InsertionSort implements IntArraySort {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index_a = i;
            int index_b = i - 1;
            while (index_b >= 0 && array[index_a] < array[index_b]){
                swap(array, index_a, index_b);
                index_a--;
                index_b--;
            }
        }

    }

    public static class A{
        public void fooA(){
            System.out.println("fooA");
            this.bar();
        }

        private void bar() {
            System.out.println("barA");
        }
    }

    public static class B extends A{
        public void fooB(){
            System.out.println("fooB");
            fooA();
        }

        private void bar(){
            System.out.println("barB");
        }
    }

    public static void main(String[] args) {
        new B().fooB();
    }
}
