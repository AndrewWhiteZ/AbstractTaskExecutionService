package ru.raperan.abstracttaskexecutorservice.masterservice.web.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ItemResponse<T> extends ApiResponse {

    T data;

    public ItemResponse(T data) {
        this.data = data;
    }

}
