package me.lozm.restDocs;

import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.snippet.Attributes.key;

public interface DocumentFormatGenerator {

    static Attributes.Attribute getDateFormat() {
        return key("format").value("yyyy-MM-dd");
    }

    static Attributes.Attribute getDateTimeFormat() {
        return key("format").value("yyyy-MM-dd'T'HH:mm:ss");
    }

    static Attributes.Attribute getYnFormat() {
        return key("format").value("사용: Y, 미사용: N");
    }

    static Attributes.Attribute getHealthCheckType() {
        return key("format").value("short, full");
    }

}
