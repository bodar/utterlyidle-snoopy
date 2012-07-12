package com.googlecode.utterlyidle.snoopy;


import com.googlecode.utterlyidle.handlers.HttpClient;
import com.googlecode.utterlyidle.modules.Module;
import com.googlecode.utterlyidle.modules.RequestScopedModule;
import com.googlecode.yadic.Container;

public class SnoopyModule implements RequestScopedModule {
    @Override
    public Module addPerRequestObjects(Container container) throws Exception {
        container.decorate(HttpClient.class, SnoopingClient.class);
        return this;
    }
}