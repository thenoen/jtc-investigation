package sk.thenoen.jtc;

public class BranchPrediction {


    public void printNumbers() {

        NumberPrinter numberPrinter;

        for (int i = 0; i < 1_00__000; i++) {
            if (i % 2 == 0) {
                numberPrinter = new EvenNumberPrinter();
            } else {
                numberPrinter = new OddNumberPrinter();
            }
            numberPrinter.print(i);
        }
    }

    private interface NumberPrinter {
        void print(int number);
    }

    private static class EvenNumberPrinter implements NumberPrinter {

        public void print(int number) {
            System.out.println("Even number: " + number);
        }
    }


    private static class OddNumberPrinter implements NumberPrinter {

        public void print(int number) {
            System.out.println("Odd number: " + number);
        }
    }

}
