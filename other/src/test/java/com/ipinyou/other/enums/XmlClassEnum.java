package com.ipinyou.other.enums;

import com.ipinyou.other.parseXml.entity.NSQProtectionSwitch;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum XmlClassEnum {

    protectionSwitch("protectionSwitch", NSQProtectionSwitch.class),

    commonEventInformation("commonEventInformation", NSQProtectionSwitch.class),
    ;

    private final String xmlClassName;

    private final Class<?> beanClass;
}
