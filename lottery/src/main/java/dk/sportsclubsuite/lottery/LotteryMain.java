package dk.sportsclubsuite.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Morten Andersen (mortena@gmail.com)
 */
public class LotteryMain {
    private static final Logger LOGGER = Logger.getLogger(LotteryMain.class.getName());

    public static void main(String[] args) {
        int lastNumber = 400;
        int winnersInDraw = 7;
        String[] drawNames = {"januar", "februar", "marts", "april", "maj", "juni", "juli",
                "august", "september", "oktober", "november", "december"};

        int[] prevWinners = {};

        List<Integer> doubleCheckList = new ArrayList<Integer>();
        List<Integer> allWinnersEverList = new ArrayList<Integer>();

        for (int i = 0; i < prevWinners.length; i++) {
            int prevWinner = prevWinners[i];
            allWinnersEverList.add(new Integer(prevWinner));
        }

        LotteryStringGenerator stringGenerator = new LotteryStringGenerator() {
            @Override
            public void applyText(String drawName, List<Integer> winnerNumbers, StringBuffer textBuffer) {
                textBuffer.append("Hermed vindere af Gandrup Sportsklubs klublotto for " + drawName + " måned 2014.\n\n");

                for (int i = 0; i < winnerNumbers.size(); i++) {
                    Integer winnerNumber = winnerNumbers.get(i);
                    textBuffer.append((i + 1) + ". præmie: " + winnerNumber + "\n");
                }

                textBuffer.append("\nTillykke til de heldige.");
            }
        };

        LotteryNumberEngine engine = new LotteryNumberEngine(1, lastNumber, true);
        engine.removePrevWinners(prevWinners);

        List<LotteryDraw> draws = new ArrayList<LotteryDraw>(12);
        for (int i = 0; i < drawNames.length; i++) {
            String drawName = drawNames[i];

            int numbersLeft = winnersInDraw;
            LotteryDraw draw = new LotteryDraw(drawName);
            while (numbersLeft-- > 0) {
                Integer number = engine.drawNumber();
                if (doubleCheckList.contains(number)) {
                    throw new RuntimeException("Numbers was already drawn!");
                }
                draw.addWinnerNumber(number);
                allWinnersEverList.add(number);
                doubleCheckList.add(number);
            }
            draws.add(draw);
        }

        System.out.println("");

        // Print results
        for (int i = 0; i < draws.size(); i++) {
            LotteryDraw lotteryDraw = draws.get(i);
            System.out.println(lotteryDraw.getString(stringGenerator));
            System.out.println("");
        }

        StringBuffer allWinnersListBuffer = new StringBuffer();
        for (int i = 0; i < allWinnersEverList.size(); i++) {
            Integer number = allWinnersEverList.get(i);
            if (i > 0) {
                allWinnersListBuffer.append(", ");
            }
            allWinnersListBuffer.append(number.toString());
        }
        System.out.println("Vindere over al tid:");
        System.out.println(allWinnersListBuffer.toString());
    }
}
