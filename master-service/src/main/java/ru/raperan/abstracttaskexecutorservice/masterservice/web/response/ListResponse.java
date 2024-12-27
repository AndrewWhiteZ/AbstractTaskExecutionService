package ru.raperan.abstracttaskexecutorservice.masterservice.web.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ListResponse<T> extends ApiResponse {

    List<T> items;

    public ListResponse(List<T> items) {
        this.items = items;
    }

}
