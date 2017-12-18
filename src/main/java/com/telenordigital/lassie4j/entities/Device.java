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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

/**
 * A device. Devices are associated with applications.
 */
@Value.Immutable
@Value.Style(builder = "new")
@JsonDeserialize(builder = ImmutableDevice.Builder.class)
public interface Device {

    /**
     * The device's EUI.
     */
    @JsonProperty("deviceEUI")
    @Nullable
    String eui();

    /**
     *
     */
    @JsonProperty("devAddr")
    @Nullable
    String devAddr();

    /**
     *
     */
    @JsonProperty("appKey")
    @Nullable
    String appKey();

    /**
     *
     */
    @JsonProperty("appSKey")
    @Nullable
    String appSKey();

    /**
     *
     */
    @JsonProperty("nwkSKey")
    @Nullable
    String nwkSKey();

    /**
     *
     */
    @JsonProperty("fCntUp")
    @Nullable
    Integer fCntUp();

    /**
     *
     */
    @JsonProperty("fCntDn")
    @Nullable
    Integer fCntDn();

    /**
     *
     */
    @JsonProperty("relaxedCounter")
    @Nullable
    Boolean relaxedCounter();

    /**
     *
     */
    @JsonProperty("deviceType")
    @Nullable
    String deviceType();

    /**
     *
     */
    @JsonProperty("keyWarning")
    @Nullable
    Boolean keyWarning();

    /**
     *
     */
    @JsonProperty("tags")
    @Nullable
    Map<String, String> tags();
}