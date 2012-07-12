package com.googlecode.utterlyidle.snoopy;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Strings;
import com.googlecode.utterlyidle.Request;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.handlers.Auditor;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

public class WaitrestFormatAuditor implements Auditor {
    private SnoopyOutputStream outputStream;

    public WaitrestFormatAuditor(SnoopyOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void audit(Pair<Request, Date> request, Pair<Response, Date> response) throws Exception {
        Writer writer = new OutputStreamWriter(outputStream.outputStream());
        writer.write(waitrestFormat(request.first(), response.first()));
        writer.close();
    }

    public static String waitrestFormat(Object... values) {
        return String.format(Strings.toString(WaitrestFormatAuditor.class.getResourceAsStream("waitrest.format")), values);
    }
}
