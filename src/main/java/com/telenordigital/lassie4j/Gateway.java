package com.telenordigital.lassie4j;
import org.immutables.value.Value;
import java.util.Map;
/**
 * 
 */
@Value.Immutable
public abstract class Gateway {
    public abstract String eui();
    public abstract Map<String, String> tags();
}