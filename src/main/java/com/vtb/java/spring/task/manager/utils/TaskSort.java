package com.vtb.java.spring.task.manager.utils;

import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskSort {
    List<Order> orders;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public TaskSort(String[] sort) {
        orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] tempSort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(tempSort[1]), tempSort[0]));
            }
        } else {
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }
    }
}
