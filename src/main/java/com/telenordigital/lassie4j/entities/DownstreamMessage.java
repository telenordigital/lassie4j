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

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;;

/**
 * A downstream (aka to the device) message.
 */
@Value.Immutable
@Value.Style(builder = "new")
@JsonDeserialize(builder = ImmutableDownstreamMessage.Builder.class)
public interface DownstreamMessage {
    /**
     * The device EUI. This is set by the server. It is ignored when a message
     * is created.
     */
    @JsonProperty("deviceEUI")
    @Nullable
    String deviceEui();

    /**
     * The hex encoded data. This field is required.
     */
    @JsonProperty("data")
    String data();

    /**
     * The port number (1-223). Required field.
     */
    @JsonProperty("port")
    Byte port();

    /**
     * Ack field. Set to true if the message must be acknowledged by the device.
     * Required field.
     */
    @JsonProperty("ack")
    Boolean ack();

    /**
     * The time the message was created. This field is set by the server and
     * ignored when messages are scheduled.
     */
    @JsonProperty("createdTime")
    @Nullable
    Long createdTime();

    /**
     * The time the message is set to the device. This field is ignored when
     * messages are scheduled.
     */
    @JsonProperty("sentTime")
    @Nullable
    Long sentTime();

    /**
     * The time the message is acknowledged. This field will be zero for 
     * messages that haven't got the ack flag set.
     */
    @JsonProperty("ackTime")
    @Nullable
    Long ackTime();

    /**
     * State of the message.
     */
    @JsonProperty("state")
    @Nullable
    String state();
}