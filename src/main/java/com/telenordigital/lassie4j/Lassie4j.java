package com.telenordigital.lassie4j;

import java.net.URL;

/**
 * @author Ståle Dahl <stalehd@telenordigital.com> 
 */
public class Lassie4j {
    private final String endpoint;
    private final String token;

    /**
     * Create a new Lassie4j client using the configuration. The configuration
     * can either be set by adding a ${HOME}/.lassie file or by setting the
     * environment variables LASSIE_ADDRESS and LASSIE_TOKEN. The environment
     * variables override the configuration file.
     */
    public Lassie4j() {
        final Config cfg = new Config();
        this.token = cfg.token();
        this.endpoint = cfg.endpoint();
    }

    /**
     * Create a new lassie4j client with the specified endpoint or token. 
     */
    public Lassie4j(final String endpoint, final String token) {
        this.endpoint = endpoint;
        this.token = token;
    }

    /**
     * Create a new aplication in Congress.
     */
    public Application CreateApplication(final Application template) {
        return null;
    }

    /**
     *  Create new device in Congress.
     */
    public Device CreateDevice(final Device template) {
        return null;
    }

    /**
     * Create a new gateway in Congress.
     */
    public Gateway CreateGateway(final Gateway template) {
        return null;
    }
}