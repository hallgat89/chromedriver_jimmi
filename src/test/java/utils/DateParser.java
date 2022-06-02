package utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class DateParser {

    private enum MonthEnum {
        JANUAR("január", Calendar.JANUARY),
        FEBRUAR("február", Calendar.FEBRUARY),
        MARCIUS("március", Calendar.MARCH),
        APRILIS("április", Calendar.APRIL),
        MAJUS("május", Calendar.MAY),
        JUNIUS("június", Calendar.JUNE),
        JULIUS("július", Calendar.JULY),
        AUGUSZTUS("augusztus", Calendar.AUGUST),
        SZEPTEMBER("szeptember", Calendar.SEPTEMBER),
        OKTOBER("október", Calendar.OCTOBER),
        NOVEMBER("november", Calendar.NOVEMBER),
        DECEMBER("december", Calendar.DECEMBER);

        private MonthEnum(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public final String name;
        public final int value;

        public static MonthEnum fromString(String str) {
            Optional<MonthEnum> found = Arrays.stream(MonthEnum.values()).filter(e -> e.name.equalsIgnoreCase(str)).findFirst();
            if (found.isEmpty()) {
                throw new IllegalArgumentException();
            }
            return found.get();
        }

    }

    public static Date fromString(String str) {
        String[] parts = str.split("[\\ ]");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cleanToInt(parts[0]));
        cal.set(Calendar.MONTH, MonthEnum.fromString(parts[1]).value);
        cal.set(Calendar.DAY_OF_MONTH, cleanToInt(parts[2]));
        return cal.getTime();
    }

    private static Integer cleanToInt(String str) {
        return Integer.parseInt(str.replaceAll("[.]", ""));
    }

}
