package dk.sportsclubsuite.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Morten Andersen (mortena@gmail.com)
 */
public class LotteryDraw {
    private static final Logger LOGGER = Logger.getLogger(LotteryDraw.class.getName());

    private final String drawName;
    private final int year;
    private final int month;
    private List<Integer> winnerNumbers = new ArrayList<Integer>();

    public LotteryDraw(String drawName, int year, int month) {
        this.drawName = drawName;
        this.year = year;
        this.month = month;
    }


    public String getDrawName() {
        return drawName;
    }

    public List<Integer> getWinnerNumbers() {
        return winnerNumbers;
    }

    public void addWinnerNumber(Integer number) {
        if (winnerNumbers.contains(number)) {
            throw new RuntimeException("Number is already a winner in this draw.");
        }
        winnerNumbers.add(number);
    }

    public String getString(LotteryStringGenerator engine) {
        StringBuffer stringBuffer = new StringBuffer();

        engine.applyText(drawName, winnerNumbers, year, month, stringBuffer);

        return stringBuffer.toString();
    }
}
