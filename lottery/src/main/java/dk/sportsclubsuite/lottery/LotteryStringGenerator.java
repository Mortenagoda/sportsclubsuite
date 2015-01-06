package dk.sportsclubsuite.lottery;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Morten Andersen (mortena@gmail.com)
 */
public abstract class LotteryStringGenerator {
    private static final Logger LOGGER = Logger.getLogger(LotteryStringGenerator.class.getName());

    public abstract void applyText(String drawName, List<Integer> winnerNumbers, int year, int month, StringBuffer textBuffer);

}
