package com.googlecode.utterlyidle.snoopy;

import java.io.OutputStream;

public class SnoopyOutputStream {
    private OutputStream outputStream;

    public SnoopyOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public static SnoopyOutputStream snoopyOutputStream(OutputStream outputStream) {
        return new SnoopyOutputStream(outputStream);
    }

    public OutputStream outputStream() {
        return outputStream;
    }
}
