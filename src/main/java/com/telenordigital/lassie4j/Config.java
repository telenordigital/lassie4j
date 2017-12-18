/*
Copyright 2018 Telenor Digital AS

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.telenordigital.lassie4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.io.IOException;

/**
* Simple utility class to return the configured endpoint and token. It will
* start out with the default values, read the configuration file (if it exists)
* and finally check the environment variables if they are used to override the
* defaults or the configuration file.
*/
/* package-private */ class Config {
    /**
    * The defaeult endpoint to use for Lassie4j. This is the address of the
    * Congress REST API.
    */
    public final static String DEFAULT_ENDPOINT = "https://api.lora.telenor.io";
    
    /**
    * The default token to use for Lassie4j. This is empty.
    */
    public final static String DEFAULT_TOKEN = "";
    
    private final static Logger LOG = Logger.getGlobal();
    
    private String endpoint = DEFAULT_ENDPOINT;
    private String token = DEFAULT_TOKEN;
    
    /**
    * Create a new configuration.
    */
    public Config() {
        // Read file
        try {
            final Path configFile = Paths.get(
                System.getProperty("user.home"), ".lassie");
            if (configFile.toFile().exists()) {
                readFile(configFile);
            }
        } catch (final IOException ioe) {
            LOG.finest("Got exception reading config file. Ignoring it: " 
            + ioe.getMessage());
        }
        
        final String envEndpoint = System.getenv("LASSIE_ADDRESS");
        if (envEndpoint != null && !envEndpoint.isEmpty()) {
            this.endpoint = envEndpoint;
        }
        final String envToken = System.getenv("LASSIE_TOKEN");
        if (envToken != null && !envToken.isEmpty()) {
            this.token = envToken;
        }
    }
    
    /**
    * Read configuration file. Logs finest and fine diagnostic messages.
    */
    private void readFile(final Path sourcePath) throws IOException {
        Files.lines(sourcePath).forEach((text) -> {
            final String[] elements = text.split("=");
            if (elements.length < 2) {
                LOG.finest("Ignoring line with " + text);
                return;
            }
            if (elements[0].startsWith("#")) {
                return;
            }
            if (elements[0].startsWith("address")) {
                this.endpoint = elements[1];
                return;
            }
            if (elements[0].startsWith("token")) {
                this.token = elements[1];
                return;
            }
            LOG.fine("Unknown entry in file: " + text);
        });
    }
    
    /**
    * @return the configured endpoint.
    */
    public String endpoint() {
        return endpoint;
    }
    
    /**
    * @return the configured token.
    */
    public String token() {
        return token;
    }
}