import org.junit.jupiter.api.Test;
import utils.DateParser;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {

    @Test
    public void test() {
        Calendar cal = Calendar.getInstance();
        Date result = DateParser.fromString("2010. szeptember 6.");
        cal.setTime(result);
        assertEquals(2010, cal.get(Calendar.YEAR));
        assertEquals(Calendar.SEPTEMBER, cal.get(Calendar.MONTH));
        assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
    }

}
