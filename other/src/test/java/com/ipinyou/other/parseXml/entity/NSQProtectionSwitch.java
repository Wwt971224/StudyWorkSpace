package com.ipinyou.other.parseXml.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class NSQProtectionSwitch {
    @JsonProperty("NOTIFICATION_ID")
    private String notificationId;
    @JsonProperty("OS_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime sourceTime;
    @JsonProperty("PROTECTION_TYPE")
    private String protectionType;
    @JsonProperty("SWITCH_REASON")
    private String switchReason;
    @JsonProperty("LAYER_RATE")
    private String layerRate;
    @JsonProperty("PG_REF")
    private String pgRef;
    @JsonProperty("PROTECTED_TP_REF")
    private String protectedTpRef;
    @JsonProperty("SWITCH_AWAY_FROM_TP_REF")
    private String switchAwayFromTpRef;
    @JsonProperty("SWITCH_TO_TP_REF")
    private String switchToTpRef;
    private String vendorExtensions;
    private ZonedDateTime osTime;
}
