package com.googlecode.utterlyidle.snoopy;

import com.googlecode.totallylazy.Streams;
import com.googlecode.utterlyidle.Request;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.handlers.AuditHandler;
import com.googlecode.utterlyidle.handlers.HttpClient;

import static com.googlecode.utterlyidle.snoopy.SnoopyOutputStream.snoopyOutputStream;

public class SnoopingClient implements HttpClient {
    private HttpClient httpClient;

    public SnoopingClient(HttpClient httpClient, SnoopyOutputStream outputStream) {
        this.httpClient = new AuditHandler(httpClient, new WaitrestFormatAuditor(outputStream));
    }

    public SnoopingClient(HttpClient httpClient) {
        this(httpClient, snoopyOutputStream(Streams.nullOutputStream()));
    }

    @Override
    public Response handle(Request request) throws Exception {
        return httpClient.handle(request);
    }
}
