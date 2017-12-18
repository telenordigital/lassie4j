package com.telenordigital.lassie4j;
import org.immutables.value.Value;
import java.util.Map;

/**
 * An application. Applications represents collections of devices and may
 * contain zero or more devices. The devices can be of different types.
 */
@Value.Immutable
public abstract class Application {
    public abstract String eui();
    public abstract Map<String,String> tags();
}
