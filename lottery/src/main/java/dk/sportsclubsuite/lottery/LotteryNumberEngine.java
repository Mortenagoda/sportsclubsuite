package dk.sportsclubsuite.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Morten Andersen (mortena@gmail.com)
 */
public class LotteryNumberEngine {
    private static final Logger LOGGER = Logger.getLogger(LotteryNumberEngine.class.getName());

    private Random random = new Random(1051223);
    private List<Integer> numbersList = new ArrayList<Integer>();
    private final boolean numberWinOnlyOnce;

    public LotteryNumberEngine(int firstNumber, int lastNumber, boolean numberWinOnlyOnce) {
        this.numberWinOnlyOnce = numberWinOnlyOnce;
        for (int i = firstNumber; i <= lastNumber; i++) {
            numbersList.add(new Integer(i));
        }
    }

    public Integer drawNumber() {
        if (numbersList.size() == 0) {
            throw new RuntimeException("No more numbers in lottery!");
        }
        Integer number = numbersList.remove(randInt(0, numbersList.size() - 1));
        return number;
    }

    public int randInt(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = random.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void removePrevWinners(int[] prevWinners) {
        for (int i = 0; i < prevWinners.length; i++) {
            int prevWinner = prevWinners[i];
            if (numbersList.remove(new Integer(prevWinner))) {
                System.out.println("Winner removed: " + prevWinner);
            }
        }

        System.out.println("Numbers in engine: " + numbersList.size());
    }
}
