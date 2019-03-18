package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    
    public List<Item> parseItemList(String valueToParse) {
        return itemList(valueToParse);
    }
    
    public Item parseSingleItem(String singleItem) throws ItemParseException {
        return itemSeparator(singleItem);
    }
    
    public static String[] itemSplitter(String stringToSplit) {
        return stringToSplit.split("##");
    }
    
    public static Item itemSeparator(String input) throws ItemParseException {
        String pattern = ("(\\w+)([:@^*%])(\\w+);(\\w+)([:@^*%])((\\d*\\.)?\\d+);(\\w+)([:@^*%])(\\w+);(\\w+)([:@^*%])(\\d{1,2}/\\d{1,2}/\\d{4})(##)?");
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String productName = m.group(3).toLowerCase();
            Double priceAsDouble = Double.parseDouble(m.group(6));
            String typeOfItem = m.group(10).toLowerCase();
            String dateOfExpiration = m.group(13);
            return new Item(productName, priceAsDouble, typeOfItem, dateOfExpiration);
        } else {
            throw new ItemParseException();
        }
    }
    
    public static List<Item> itemList(String input) {
        List<Item> itemList = new ArrayList<>();
        String[] output = itemSplitter(input);
        for (String s : output) {
            try {
                itemList.add(itemSeparator(s));
            } catch (ItemParseException e) {
                System.err.println(e.getMessage());
            }
        }
        return itemList;
    }
}