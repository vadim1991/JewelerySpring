package com.epam.Vadym_Vlasenko.eShop.service.exception;

/**
 * Created by swift-seeker-89717 on 14.05.2015.
 */
public class ShopException extends RuntimeException {

    public ShopException() {
    }

    public ShopException(String message) {
        super(message);
    }

    public ShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopException(Throwable cause) {
        super(cause);
    }

    public ShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
