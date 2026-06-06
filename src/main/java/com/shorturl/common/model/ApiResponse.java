package com.shorturl.common.model;

public record ApiResponse<T>(int code, String message, T data) {
}
