package com.mycompany.myapp.service.utils;

import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginatorCheck {

    private static final int NUMBER_PAGE = 0;
    private static final int COUNT_ELEMENT = 5;
    private static final Integer ORDER_NUMBER = 1;

    public static Sort sortColumn(Optional<String> optionalNameColumn, Optional<Integer> optionalSort, String defaultSort) {
        IntPredicate inOrder = i -> i == 1;
        return inOrder.test(optionalSort.orElse(ORDER_NUMBER))
            ? Sort.by(optionalNameColumn.orElse(defaultSort)).ascending()
            : Sort.by(optionalNameColumn.orElse(defaultSort)).descending();
    }

    public static int numberOfPage(Optional<Integer> numberPage) {
        return numberPage.orElse(NUMBER_PAGE);
    }

    public static int countElementOnPage(Optional<Integer> countElement) {
        return countElement.orElse(COUNT_ELEMENT);
    }

    public static int elementStart(Pageable pageable) {
        return pageable.getPageNumber() * pageable.getPageSize();
    }

    public static int elementEnd(Pageable pageable, List items) {
        return ((pageable.getPageNumber() + 1) * pageable.getPageSize()) > items.size()
            ? items.size()
            : ((pageable.getPageNumber() + 1) * pageable.getPageSize());
    }
}
