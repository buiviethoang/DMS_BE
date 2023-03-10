package com.thesis.dms.common.response;

import org.springframework.data.domain.Sort;

public interface ISort {
    /**
     * sort by id giam dan
     *
     * @return
     */
    default Sort getSortDESC() {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "created_date");
        return Sort.by(order);
    }

    default Sort getSortDESC2() {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createdDate");
        return Sort.by(order);
    }

    default Sort getSortDESC3(String col, Sort.Direction type) {
        Sort.Order order = new Sort.Order(type, col);
        return Sort.by(order);
    }


    /**
     * Sort by Id tang dan
     *
     * @return
     */
    default Sort getSortASC() {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "created_date");
        return Sort.by(order);
    }

    default Sort getSortASC2() {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "created_date");
        return Sort.by(order);
    }
}
