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

import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import com.telenordigital.lassie4j.entities.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceTest {
    @Test
    public void testDevice() throws Exception {
        final Lassie4j client = new Lassie4j();
        final Map<String, String> tags = new HashMap<String, String>();
        tags.put("name", "The test application");
        final Application app = client.createApplication(new ImmutableApplication.Builder().tags(tags).build());

        final List<Device> devices = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Device newDevice = client.createDevice(app.eui(), new ImmutableDevice.Builder().build());
            devices.add(newDevice);
        }

        for (final Device d : devices) {
            client.deleteDevice(app.eui(), d.eui());
        }
        client.deleteApplication(app.eui());
    }
}