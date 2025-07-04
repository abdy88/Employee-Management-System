package com.example.employee_management.util;


import com.example.employee_management.dto.PageableResponse;
import org.springframework.data.domain.Page;

public class PageableResponseHelper {

    public static <T> PageableResponse<T> toPageableResponse(Page<T> page) {
        PageableResponse<T> response = new PageableResponse<>();
        response.setContent(page.getContent());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLast(page.isLast());
        return response;
    }
}