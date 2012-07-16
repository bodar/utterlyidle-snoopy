package com.googlecode.utterlyidle.snoopy;

import com.googlecode.utterlyidle.Request;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.handlers.Auditor;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;

import static com.googlecode.totallylazy.Pair.pair;
import static com.googlecode.utterlyidle.HttpHeaders.CONTENT_LENGTH;
import static com.googlecode.utterlyidle.RequestBuilder.get;
import static com.googlecode.utterlyidle.ResponseBuilder.response;
import static com.googlecode.utterlyidle.snoopy.SnoopyOutputStream.snoopyOutputStream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WaitrestFormatAuditorTest {
    @Test
    public void outputsInExpectedFormat() throws Exception {
        OutputStream output = new ByteArrayOutputStream();
        Auditor auditor = new WaitrestFormatAuditor(snoopyOutputStream(output));

        Request request = get("http://localhost/hello").build();
        Response response = response().header(CONTENT_LENGTH, "8").entity("<hello/>").build();

        auditor.audit(pair(request, new Date()), pair(response, new Date()));

        assertThat(output.toString(), is(
                "---------- REQUEST ------------\n" +
                "GET http://localhost/hello HTTP/1.1\r\n" +
                "Content-Length: 0\r\n" +
                "\r\n" +
                "\n" +
                "---------- RESPONSE -----------\n" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 8\r\n" +
                "\r\n" +
                "<hello/>\n\n\n"));
    }
}
