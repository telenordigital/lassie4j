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
package com.telenordigital.lassie4j.entities;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * A gateway. Gateways are used to forward data from the radio network to
 * the Congress.
 */
@Value.Immutable
@Value.Style(builder = "new")
@JsonDeserialize(builder = ImmutableGateway.Builder.class)
public interface Gateway {
    /**
     * The gateway's EUI
     */
    @JsonProperty("gatewayEUI")
    @Nullable
    String eui();

    /**
     * The public IP address of the gateway
     */
    @JsonProperty("ip")
    @Nullable
    String ip();

    /**
     * Strict IP checking for gateway. The default is true.
     */
    @JsonProperty("strictIP")
    @Nullable
    Boolean strictIp();

    /**
     * Position of gateway; latitude
     */
    @JsonProperty("latitude")
    @Nullable
    Float latitude();

    /**
     * Position of gateway; longitude
     */
    @JsonProperty("longitude")
    @Nullable
    Float longitude();

    /**
     * Position of gateway; altitude
     */
    @JsonProperty("altitude")
    @Nullable
    Float altitude();

    /**
     * Gateway tags.
     */
    @JsonProperty("tags")
    @Nullable
    Map<String, String> tags();
}