package org.example.booknote.activity.domain;

import lombok.Getter;

@Getter
public enum ActionType {
    CREATE_BOOK("LOG001", "Book creation initiated."),
    CREATE_NOTE("LOG002", "Note creation initiated."),
    DELETE_NOTE("LOG003", "Note deletion initiated."),
    UPDATE_NOTE("LOG004", "Note update initiated.");


    private final String logCode;
    private final String logMessage;


    ActionType(String logCode, String logMessage) {
        this.logCode = logCode;
        this.logMessage = logMessage;
    }
}
