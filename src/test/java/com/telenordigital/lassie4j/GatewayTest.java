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

import java.util.HashMap;
import java.util.Map;

import com.telenordigital.lassie4j.entities.Gateway;
import com.telenordigital.lassie4j.entities.ImmutableGateway;
import org.junit.Test;

public class GatewayTest {
    @Test
    public void testGateway() throws Exception {
        final Lassie4j client = new Lassie4j();
        final Map<String, String> tags = new HashMap<String, String>();
        tags.put("name", "The test gateway");
        final String eui = Utils.randomEui();
        final Gateway template = new ImmutableGateway.Builder().eui(eui).altitude(0.0f).longitude(1.0f).latitude(2.0f)
                .ip("127.0.0.1").strictIp(false).tags(tags).build();
        final Gateway gw = client.createGateway(template);
        client.updateGateway(gw);
        client.deleteGateway(gw.eui());
    }
}