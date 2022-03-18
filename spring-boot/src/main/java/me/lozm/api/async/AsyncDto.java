package me.lozm.api.async;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AsyncDto {

    @Getter
    public static class Request {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String result;
    }

}
