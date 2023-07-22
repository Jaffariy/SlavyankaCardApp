package com.example.myapplication.Enums;

public enum UserInfoEnum {
    FIO("fio"),
    BIRTHDAY("dateBirth"),
    CITY("city"),
    PHONE("phone"),
    EMAIL("email"),
    CARDNUM("cardnumber"),
    PASSWORD("pass"),
    GENDER("gender"),
    POINTS("points");
    private final String fields;
    private UserInfoEnum(String fields) {
        this.fields = fields;
    }
    public String getField() {
        return this.fields;
    }
}
