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

/**
 * Generic exception whenever something (tm) goes wrong.
 */
public class Lassie4jException extends Exception {
    static final long serialVersionUID = 0;

    private final String errorMessage;
    private final int statusCode;

    /**
     * Wrap a source exception.
     */
    public Lassie4jException(final Throwable t) {
        super(t);
        errorMessage = "Client exception";
        statusCode = 0;
    }

    /**
     * Invalid response from server.
     */
    public Lassie4jException(final String errorMessage, final int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    /**
     * Status code from server (if applicable)
     */
    public int status() {
        return statusCode;
    }

    /**
     * Error message from server (if applicable)
     */
    public String message() {
        return errorMessage;
    }
}