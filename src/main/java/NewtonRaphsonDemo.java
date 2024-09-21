public class NewtonRaphsonDemo {
    public static void main(String[] args) {
        NewtonRaphson nr = new NewtonRaphson();
        if (nr.rootCalc()) {
            System.out.println(nr);
        } else {
            System.out.printf("Newton-Raphson unable to find root for %s, " +
                    "given an estimate of %.2f. %n", nr.getPolyFunc(), nr.getGuess());
            System.out.printf("The calculation took %d iterations.%n",
                    nr.getEstimateCount());
        }
        System.out.println(nr.testRoot());
    }
}