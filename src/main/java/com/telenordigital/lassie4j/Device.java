package com.telenordigital.lassie4j;
import org.immutables.value.Value;
import java.util.Map;

/**
 * 
 */
@Value.Immutable
public abstract class Device {
    public abstract String eui();
    public abstract String appKey();
    public abstract String appSKey();
    public abstract String nwkSKey();
    public abstract int fCntUp();
    public abstract int fCntDn();
    public abstract String devAddr();
    public abstract Map<String, String> tags();
}