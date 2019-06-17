package com.o2o.exception;

public class ProductCategoryOperationException extends RuntimeException {

    public ProductCategoryOperationException(String message) {
        super(message);
    }

    public ProductCategoryOperationException(Throwable cause) {
        super(cause);
    }
}
