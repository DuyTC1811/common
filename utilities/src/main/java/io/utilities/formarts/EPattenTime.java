package io.utilities.formarts;

public enum EPattenTime {
    DD_MM_YYYY("dd_MM_yyyy"),
    DD_MM_YYYY_HH_MM("dd_MM_yyyy HH:mm");

    private final String patten;

    EPattenTime(String patten) {
        this.patten = patten;
    }

    public String getPatten() {
        return patten;
    }
}
