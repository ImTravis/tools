package com.tools.Utils;

import lombok.Data;

@Data
public class Count {
    private int indexDay;
    private String rate;
    private String point;//up  down
    private String income;//收入

    public Count(int indexDay, String rate, String point) {
        this.indexDay = indexDay;
        this.rate = rate;
        this.point = point;
    }
}
