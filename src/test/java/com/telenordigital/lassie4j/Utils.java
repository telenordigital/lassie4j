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

import java.util.Formatter;
import java.util.Locale;
import java.util.Random;

public class Utils {
    private final static Random RND = new Random();

    public static String randomEui() {
        final byte[] eui = new byte[8];
        RND.nextBytes(eui);

        final StringBuilder sb = new StringBuilder();
        final Formatter formatter = new Formatter(sb, Locale.ROOT);
        formatter.format("%1$02x-%2$02x-%3$02x-%4$02x-%5$02x-%6$02x-%7$02x-%8$02x", 
                eui[0], eui[1], eui[2], eui[3],
                eui[4], eui[5], eui[6], eui[7]);
        formatter.close();
        return sb.toString();
    }
}