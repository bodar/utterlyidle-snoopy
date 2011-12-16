package com.googlecode.utterlyidle.snoopy;

import com.googlecode.utterlyidle.Request;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.handlers.AuditHandler;
import com.googlecode.utterlyidle.handlers.HttpClient;
import com.googlecode.waitrest.Waitrest;

public class SnoopingHttpClient implements HttpClient {

    private HttpClient httpClient;

    public SnoopingHttpClient(HttpClient httpClient, Waitrest waitrest) {
        this.httpClient = new AuditHandler(httpClient, new SnoopingWaitrestHttpAuditor(waitrest));
    }

    @Override
    public Response handle(Request request) throws Exception {
        return httpClient.handle(request);
    }
}
