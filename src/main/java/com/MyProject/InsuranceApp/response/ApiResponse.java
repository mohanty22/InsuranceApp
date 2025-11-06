package com.MyProject.InsuranceApp.response;

public class ApiResponse<T> {
    private ApiHeader header;
    private T body;
    private Object error;
    public ApiResponse() {}

    public ApiResponse(ApiHeader header, T body) {
        this.header = header;
        this.body = body;
    }
    public ApiResponse(ApiHeader header, T body, Object error) {
        this.header = header;
        this.body = body;
        this.error = error;
    }

    // Getters and setters
    public ApiHeader getHeader() { return header; }
    public void setHeader(ApiHeader header) { this.header = header; }

    public T getBody() { return body; }
    public void setBody(T body) { this.body = body; }
    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
