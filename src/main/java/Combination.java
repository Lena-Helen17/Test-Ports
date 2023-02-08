import java.util.*;
import java.util.stream.Collectors;

public class Combination {
    /**
     * Преобразует массив строк index в массив последовательностей чисел
     * @throws NumberFormatException возвращает null
     * @param index последовательность чисел, перечисленных через дефис и(или) через запятую
     * @return пустой массивб если строка пустая
     */
    public int[] createSequenceFromString(String index) {
        if(index == null || index.equals("")){
            return new int[] {};
        }
        List<Integer> listNumber = new ArrayList<>();
        String[] masString = index.split(",");
        try {
            for (String number : masString) {
                if (number.indexOf('-') > 0) {
                    int numberMin = Integer.parseInt(number.split("-")[0]);
                    int numberMax = Integer.parseInt(number.split("-")[1]);

                    while (numberMin <= numberMax) {
                        listNumber.add(numberMin);
                        numberMin++;
                    }
                } else {
                    listNumber.add(Integer.parseInt(number));
                }
            }
        } catch (NumberFormatException e){
            return null;
        }
        return listNumber.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Возвращающает все возможные пары элементов массивов чисел.
     * @param indexes  набор последовательностей чисел произвольной длины
     * @return пустой лист, если входные данные пусты
     */
    public List<int[]> makeCombinations(String[] indexes) {
        List<int[]> res = new ArrayList<>();
        if(indexes == null || indexes.length == 0){
            return res;
        }

        List<List<Integer>> source = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) {
            source.add(Arrays.stream(createSequenceFromString(indexes[i]))
                    .boxed().collect(Collectors.toList()));
        }
        List<List<Integer>> result = getCombinations(source);

        for (List<Integer> list : result) {
            int[] buf = new int[list.size()];
            for (int i = 0; i < buf.length; i++) {
                buf[i] = list.get(i);
            }
            res.add(buf);
        }
        return res;
    }

    private List<List<Integer>> getCombinations(List<List<Integer>> source) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> newCombinations;
        int index = 0;

        for (Integer key : source.get(0)) {
            List<Integer> newList = new ArrayList<>();
            newList.add(key);
            result.add(newList);
        }
        index++;

        while (index < source.size()) {
            List<Integer> nextList = source.get(index);
            newCombinations = new ArrayList<>();
            for (List<Integer> first : result) {
                for (Integer second : nextList) {
                    List<Integer> newList = new ArrayList<>();
                    newList.addAll(first);
                    newList.add(second);
                    newCombinations.add(newList);
                }
            }
            result = newCombinations;
            index++;
        }
        return result;
    }

}
