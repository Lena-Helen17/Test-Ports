import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

class CombinationTest {

    Combination testClass = new Combination();

    @Test
    void createSequenceFromString() {
        int[] expectedResult = new int[]{1, 2, 3, 4, 5, 7, 9, 10, 11};
        String string = "1-5,7,9-11";
        int[] result = testClass.createSequenceFromString(string);
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    void createSequenceFromStringNull() {
        int[] expectedResult = new int[]{};
        String string = "";
        int[] result = testClass.createSequenceFromString(string);
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    void createSequenceFromStringError() {
        int[] expectedResult = null;
        String string = "k";
        int[] result = testClass.createSequenceFromString(string);
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    void createSequenceFromStringOneChar() {
        int[] expectedResult = new int[]{1};
        String string = "1";
        int[] result = testClass.createSequenceFromString(string);
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    void createSequenceFromStringBigNumber() {
        int[] expectedResult = new int[]{256364, 1245456};
        String string = "256364,1245456";
        int[] result = testClass.createSequenceFromString(string);
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    void makeCombinations() {
        String[] indexes = new String[]{"1,3-5", "2", "3-4"};
        List<int[]> expectedResult = Arrays.asList(new int[]{1, 2, 3},
                new int[]{1, 2, 4}, new int[]{3, 2, 3}, new int[]{3, 2, 4},
                new int[]{4, 2, 3}, new int[]{4, 2, 4}, new int[]{5, 2, 3},
                new int[]{5, 2, 4});
        List<int[]> result = testClass.makeCombinations(indexes);
        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertArrayEquals(expectedResult.get(i), result.get(i));
        }
    }

    @Test
    void makeCombinationsTree() {
        String[] indexes = new String[]{"1,2", "4,5", "6,7"};
        List<int[]> expectedResult = Arrays.asList(new int[]{1, 4, 6},
                new int[]{1, 4, 7}, new int[]{1, 5, 6}, new int[]{1, 5, 7},
                new int[]{2, 4, 6}, new int[]{2, 4, 7}, new int[]{2, 5, 6},
                new int[]{2, 5, 7});
        List<int[]> result = testClass.makeCombinations(indexes);
        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertArrayEquals(expectedResult.get(i), result.get(i));
        }
    }

    @Test
    void makeCombinationsOne() {
        String[] indexes = new String[]{"22365"};
        List<int[]> expectedResult = Arrays.asList(new int[]{22365});
        List<int[]> result = testClass.makeCombinations(indexes);
        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertArrayEquals(expectedResult.get(i), result.get(i));
        }
    }

    @Test
    void makeCombinationsMany() {
        String[] indexes = new String[]{"1", "2", "5", "8", "10,15"};
        List<int[]> expectedResult = Arrays.asList(new int[]{1, 2, 5, 8, 10},
                new int[]{1, 2, 5, 8, 15});
        List<int[]> result = testClass.makeCombinations(indexes);
        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertArrayEquals(expectedResult.get(i), result.get(i));
        }
    }

    @Test
    void makeCombinationsNull() {
        String[] indexes = new String[]{""};
        List<int[]> result = testClass.makeCombinations(indexes);
        Assert.assertTrue(result.isEmpty());

    }
}