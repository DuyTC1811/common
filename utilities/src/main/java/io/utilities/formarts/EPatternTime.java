package io.utilities.formarts;

public enum EPatternTime {
    DD_MM_YYYY("dd_MM_yyyy"),
    DD_MM_YYYY_HH_MM("dd_MM_yyyy HH:mm");

    private final String pattern;

    EPatternTime(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
