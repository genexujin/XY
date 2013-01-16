package com.xy.scpms.view.backing;

import org.apache.myfaces.trinidad.model.BoundedRangeModel;

public class MyProgressRangeModel extends BoundedRangeModel {
    public MyProgressRangeModel() {
        super();
    }

    private long availableBytes;
    private long maximumBytes;

    public long getMaximum() {
        long result;
        long maxByte = getMaximumBytes();
        if (maxByte == 0)
            result = -1;
        else
            result = maxByte;
        return result;
    }


    public long getValue() {
        long result;
        long availableByte = getMaximumBytes() - getAvailableBytes();
        if (availableByte == 0 || availableByte == getMaximumBytes())
            result = -1;
        else
            result = availableByte;
        return result;
    }

    public void setAvailableBytes(long availableBytes) {
        this.availableBytes = availableBytes;
    }

    public long getAvailableBytes() {
        return availableBytes;
    }

    public void setMaximumBytes(long maximumBytes) {
        this.maximumBytes = maximumBytes;
    }

    public long getMaximumBytes() {
        return maximumBytes;
    }


}
