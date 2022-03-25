package org.example.Discover;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.example.App;
import org.example.ElementNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutoDiscover {

    private static WebDriver driver= App.getDriver();
    private static final int FUZZY_SCORING_LIMIT = 50;
    public static String getLocator(Map<String, String> characteristics) throws Exception {
        String locator = null;
        try {
            locator = findElement(characteristics);
            return locator;
        } catch (Exception ex) {
            System.out.println("Auto Discovery: Couldn't find the element requested dynamically: " + ex.getMessage());
            throw ex;
        }
    }


    private static String findElement(Map<String, String> characteristics) throws Exception {

        List<Element> elements = Jsoup.parse(driver.getPageSource()).getAllElements();

        if (characteristics.containsKey("TagName")) {
            elements = filterElements(elements, (Element element) -> element.tagName().equals(characteristics.get("TagName")));
        }

        if (characteristics.containsKey("Attributes") ) {

            String[] attributes = characteristics.get("Attributes").split(",");

            for (String attribute : attributes) {
                try {
                    return getBestMatchForAttributeValue(elements, attribute, characteristics.get("AttributeMatcher"),characteristics.get("value"));
                } catch (Exception e) {
                    System.out.println("No Match: " + attribute + " = " + characteristics.get("AttributeMatcher"));
                }
            }
        }
        throw new ElementNotFoundException("Auto Discovery didn't find the element using the Characteristics provided.");
    }

    private static String getBestMatchForAttributeValue(List<Element> elements, String attribute, String attributeMatcher,String value) throws ElementNotFoundException {
        List<String> values = new ArrayList<>();
        for (Element element : elements) {
            if(element.attributes().get(attribute).contains(value)||
                    element.attributes().get(attribute).equals(value))
            {
                values.add(element.attributes().get(attribute));
            }
        }
        ExtractedResult extractedResult = FuzzySearch.extractOne(attributeMatcher, values);
        if (extractedResult.getScore() >= FUZZY_SCORING_LIMIT) {
            int index = extractedResult.getIndex();
            Element matchingElement = elements.get(index);
            return matchingElement.cssSelector();
        }
        throw new ElementNotFoundException("No match for Element with Attribute: " + attribute + " of value: " + attributeMatcher);
    }



    private static List<Element> filterElements(List<Element> elements, WebElementPredicate predicate) {
        List<Element> result = new ArrayList<>();
        for (Element element : elements) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

}