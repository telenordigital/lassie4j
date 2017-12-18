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

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import java.io.IOException;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telenordigital.lassie4j.entities.Application;
import com.telenordigital.lassie4j.entities.Device;
import com.telenordigital.lassie4j.entities.DeviceData;
import com.telenordigital.lassie4j.entities.DeviceDataList;
import com.telenordigital.lassie4j.entities.Gateway;
import com.telenordigital.lassie4j.entities.DownstreamMessage;;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
* This is the client for the Congress REST API.
*
* @author Ståle Dahl <stalehd@telenordigital.com>
*/
public class Lassie4j {
    private static final String TOKEN_HEADER = "X-API-Token";
    
    private final String endpoint;
    private final String token;
    
    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private ObjectMapper jacksonObjectMapper = new ObjectMapper();
            
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    
    /**
    * Ping the backend to ensure it is reachable
    */
    private void ping() throws Lassie4jException {
        try {
            final HttpResponse<String> resp = Unirest
                .get(endpoint + "/")
                .header(TOKEN_HEADER, token)
                .asString();
            if (resp.getStatus() == HttpStatus.SC_FORBIDDEN) {
                throw new Lassie4jException(resp.getBody(), resp.getStatus());
            }
            
        } catch (UnirestException ex) {
            throw new Lassie4jException(ex);
        }
    }
    
    /**
    * Create a new Lassie4j client using the configuration. The configuration
    * can either be set by adding a ${HOME}/.lassie file or by setting the
    * environment variables LASSIE_ADDRESS and LASSIE_TOKEN. The environment
    * variables override the configuration file.
    */
    public Lassie4j() throws Lassie4jException {
        final Config cfg = new Config();
        this.token = cfg.token();
        this.endpoint = cfg.endpoint();
        
        ping();
    }
    
    /**
    * Create a new lassie4j client with the specified endpoint or token.
    */
    public Lassie4j(final String endpoint, final String token) {
        this.endpoint = endpoint;
        this.token = token;
    }
    
    /**
    * generic POST on a resource
    */
    private <T> T create(
        final String path, final T template, final Class<T> cls) 
        throws Lassie4jException {
        try {
            return Unirest
                .post(endpoint + path)
                .header(TOKEN_HEADER, token)
                .body(template)
                .asObject(cls)
                .getBody();
        } catch (final UnirestException ue) {
            throw new Lassie4jException(ue);
        }
    }
    
    /**
    * generic DELETE on a resource
    */
    private void delete(final String path) throws Lassie4jException {
        try {
            Unirest.delete(endpoint + path).asString();
        } catch (final UnirestException ue) {
            throw new Lassie4jException(ue);
        }
    }
    
    /**
    * generic PUT on a resource
    */
    private <T> T update(final String path, T updated, final Class<T> cls) 
    throws Lassie4jException {
        try {
            return Unirest
                .put(endpoint + path)
                .header(TOKEN_HEADER, token)
                .body(updated)
                .asObject(cls)
                .getBody();
        } catch (final UnirestException ue) {
            throw new Lassie4jException(ue);
        }
    }
    
    /**
    * generic GET on a resource
    */
    private <T> T get(final String path, final Class<T> cls) 
    throws Lassie4jException {
        try {
            return Unirest
                .get(endpoint + path)
                .header(TOKEN_HEADER, token)
                .asObject(cls).getBody();
        } catch (final UnirestException ue) {
            throw new Lassie4jException(ue);
        }
    }
    
    /**
    * Create a new aplication in Congress.
    */
    public Application createApplication(final Application template) 
    throws Lassie4jException {
        return create("/applications", template, Application.class);
    }
    
    /**
    * Retrieve an application from Congress.
    */
    public Application application(final String appEui) 
    throws Lassie4jException {
        return get("/applications/" + appEui, Application.class);
    }
    
    /**
    * Delete an application in Congress.
    */
    public void deleteApplication(final String applicationEui) 
    throws Lassie4jException {
        delete("/applications/" + applicationEui);
    }
    
    /**
    * Update an application in Congress.
    */
    public Application updateApplication(final Application updated) 
    throws Lassie4jException {
        return update("/applications/" + updated.eui(), 
        updated, Application.class);
    }
    
    /**
    * Create a device in Congress.
    */
    public Device createDevice(final String appEui, final Device template) 
    throws Lassie4jException {
        return create("/applications/" + appEui + "/devices", 
        template, Device.class);
    }
    
    /**
    * Retrieve a device from Congress.
    */
    public Device device(final String appEui, final String deviceEui) 
    throws Lassie4jException {
        return get("/applications/" + appEui + "/devices/" 
        + deviceEui, Device.class);
    }
    
    /**
    * Update a device in Congress.
    */
    public Device updateDevice(final String appEui, final Device updated) 
    throws Lassie4jException {
        return update("/applications/" + appEui + "/devices/" 
        + updated.eui(), updated, Device.class);
    }
    
    /**
    * Delete a device from Congress.
    */
    public void deleteDevice(final String appEui, final String deviceEui) 
    throws Lassie4jException {
        delete("/applications/" + appEui + "/devices/" + deviceEui);
    }
    
    /**
    * Retrieve messages from a device in Congress.
    */
    public DeviceData[] deviceData(final String appEui, final String deviceEui) 
    throws Lassie4jException {
        return get("/applications/" + appEui + "/devices/" 
            + deviceEui + "/data", DeviceDataList.class).data();
    }
    
    /**
    * Create a gateway in Congress.
    */
    public Gateway createGateway(final Gateway template) throws Lassie4jException {
        return create("/gateways", template, Gateway.class);
    }
    
    /**
    * Retrieve a gateway from Congress.
    */
    public Gateway gateway(final String gatewayEui) throws Lassie4jException {
        return get("/gateways/" + gatewayEui, Gateway.class);
    }
    
    /**
    * Update a gateway in Congress.
    */
    public Gateway updateGateway(final Gateway updated) 
    throws Lassie4jException {
        return update("/gateways/" + updated.eui(), updated, Gateway.class);
    }
    
    /**
    * Delete a gateway from Congress.
    */
    public void deleteGateway(final String gatewayEui) 
    throws Lassie4jException {
        delete("/gateways/" + gatewayEui);
    }
    
    /**
    * Schedule a downstream message to a device in Congress.
    */
    public DownstreamMessage scheduleMessage(
        final String appEui, final String deviceEui,
        final DownstreamMessage messageToSend) throws Lassie4jException {
            return create("/applications/" + appEui 
                + "/devices/" + deviceEui + "/message", 
                messageToSend, DownstreamMessage.class);
        }
        
        /**
        * Retrieve the currently scheduled message to a device in Congress.
        */
        public DownstreamMessage message(
            final String appEui, final String deviceEui) 
            throws Lassie4jException {
            return get("/applications/" + appEui + "/devices/" 
                + deviceEui + "/message", DownstreamMessage.class);
        }
        
        /**
        * Remove a scheduled message from Congress.
        */
        public void deleteMessage(final String appEui, final String deviceEui) 
        throws Lassie4jException {
            delete("/applications/" + appEui + "/devices/" 
                + deviceEui + "/message");
        }
    }
    