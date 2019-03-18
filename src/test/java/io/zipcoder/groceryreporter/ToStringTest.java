package io.zipcoder.groceryreporter;

import io.zipcoder.utils.FileReader;
import io.zipcoder.GroceryReporter;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ToStringTest {
    @Test
    @Ignore
    public void test1() {
        // given
        String fileName = "RawInput.JerkSON";
        String expected = FileReader.readFile("ExpectedOutput.txt");
        GroceryReporter reporter = new GroceryReporter(fileName);

        // when
        String actual = reporter.toString();

        // then
        Assert.assertEquals(expected, actual);
    }
}
