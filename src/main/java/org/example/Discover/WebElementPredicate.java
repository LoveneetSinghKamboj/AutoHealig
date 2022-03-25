package org.example.Discover;

import org.jsoup.nodes.Element;

public interface WebElementPredicate {
    boolean test(Element element);
}
