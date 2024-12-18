package de.jaydee101.adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Day11Test {

    private final List<Long> longValueList = List.of(0L, 5601550L, 3914L, 852L, 50706L, 68L, 6L, 645371L);
    private Map<Long, Long> stoneCountMap;

    @BeforeEach
    void resetStoneCountMap() {
        stoneCountMap = new HashMap<>();
        for (Long aLong : longValueList) {
            stoneCountMap.put(aLong, 1L);
        }
    }

    @ParameterizedTest
    @CsvSource({"Day 11 - Part 1, 25",
                "Day 11 - Part 2, 75"})
    void solve(String part, int blinks) {
        for (int i = 0; i < blinks; i++) {
            stoneCountMap = mutate(stoneCountMap);
        }
        System.out.println("Solution " + part+": " + stoneCountMap.values().stream().mapToLong(Long::longValue).sum());
    }

    private Map<Long, Long> mutate(Map<Long, Long> stoneCountMap) {
        Map<Long, Long> stoneCountTempMap = new HashMap<>();
        for (Map.Entry<Long, Long> entry : stoneCountMap.entrySet()) {
            Long stone = entry.getKey();
            Long count = entry.getValue();
            if (stone.equals(0L)) {
                stoneCountTempMap.merge(1L, count, Long::sum);
            } else if (String.valueOf(stone).length() % 2 == 0) {
                String stoneString = String.valueOf(stone);
                String leftString = stoneString.substring(0, stoneString.length() / 2);
                String rightString = stoneString.substring(stoneString.length() / 2);
                stoneCountTempMap.merge(Long.valueOf(leftString), count, Long::sum);
                stoneCountTempMap.merge(Long.valueOf(rightString), count, Long::sum);
            } else {
                stoneCountTempMap.merge(stone * 2024, count, Long::sum);
            }
            stoneCountMap = stoneCountTempMap;
        }
        return stoneCountMap;
    }

}
