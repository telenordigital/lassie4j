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
 * Data received from a device.
 */
@Value.Immutable
@Value.Style(builder = "new")
@JsonDeserialize(builder = ImmutableDeviceData.Builder.class)
public interface DeviceData {
    /**
     * The device address of the device that sent the data.
     */
    @JsonProperty("devAddr")
    @Nullable
    String devAddr();

    /**
     * The time when the data was received by the gateway.
     */
    @JsonProperty("timestamp")
    @Nullable
    Long timestamp();

    /**
     * This is the data from the device. It is hex encoded and in clear text.
     */
    @JsonProperty("data")
    @Nullable
    String data();

    /**
     * The application EUI of the application the device belonged to.
     */
    @JsonProperty("appEUI")
    @Nullable
    String appEui();

    /**
     * The device EUI of the device that sent the data.
     */
    @JsonProperty("deviceEUI")
    @Nullable
    String deviceEui();

    /**
     * RSSI (aka Relative Signal Strength Indicator) for the radio packet.
     */
    @JsonProperty("rssi")
    @Nullable
    Integer rssi();

    /**
     * SNR (Signal to Noise Ratio) for the radio packet
     */
    @JsonProperty("snr")
    @Nullable
    Float snr();

    /**
     * The frequency used to send the packet.
     */
    @JsonProperty("frequency")
    @Nullable
    Float frequency();

    /**
     * The EUI of the gateway that received the packet.
     */
    @JsonProperty("gatewayEUI")
    @Nullable
    String gatewayEui();

    /**
     * The Data Rate used to send the packet (DR0..7)
     */
    @JsonProperty("dataRate")
    @Nullable
    String dataRate();
}