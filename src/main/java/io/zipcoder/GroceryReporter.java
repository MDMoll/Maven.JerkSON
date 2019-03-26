package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.List;

public class GroceryReporter {
    private final String originalFileText;
    
    ItemParser parser;
    List<Item> itemList;
    
    
    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }
    
    @Override
    public String toString() {
        return originalFileText;
    }
}
