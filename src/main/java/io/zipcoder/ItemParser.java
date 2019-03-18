package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    
    public List<Item> parseItemList(String valueToParse) {
        ArrayList<String> output = itemSplitter(valueToParse);
        return itemList(output);
    }
    
    public Item parseSingleItem(String singleItem) throws ItemParseException {
        return itemSeparator(singleItem);
    }
    
    public static ArrayList<String> itemSplitter(String stringToSplit) {
        ArrayList<String> splitItems = new ArrayList<>();
        while (stringToSplit.contains("##")) {
            String pattern = ("(.*)(##)");
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(stringToSplit);
            splitItems.add(m.group(1));
        } return splitItems;
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
    
    public static List<Item> itemList(ArrayList input) {
        List<Item> itemList = new ArrayList<>();
        for (Object s : input) {
            Item splitItem = null;
            try {
                splitItem = itemSeparator((String)s);
            } catch (ItemParseException e) {
                e.printStackTrace();
            }
            itemList.add(splitItem);
        }
        return itemList;
    }
}