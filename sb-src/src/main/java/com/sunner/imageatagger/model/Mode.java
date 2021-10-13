package com.sunner.imageatagger.model;

public enum Mode {
    NSFW("nsfw_beta"),
    TAGS("tags"),
    PERSONAL("personal_photos")
    ;

    private String code;

    Mode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
