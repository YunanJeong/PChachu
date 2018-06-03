package com.example.yunan.pchachu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelAddressParserTest {

    ModelAddressParser ap = new ModelAddressParser();

    @Test
    public void testParseAddress() {
        ap.parseAddress("대한민국 경기도 수원시 팔달구 우만동");
        assertEquals("대한민국", ap.getNation());
        assertEquals("경기도", ap.getMetropolice());
        assertEquals("수원시", ap.getCity());
        assertEquals("우만동", ap.getTown());
    }

    @Test
    public void testParseAddress2() {
        ap.parseAddress("대한민국");
        assertEquals("대한민국", ap.getNation());
        assertEquals(null, ap.getMetropolice());
        assertEquals(null, ap.getCity());
        assertEquals(null, ap.getTown());
    }

    @Test
    public void testParseAddress3() {
        ap.parseAddress("대한민국 대구광역시");
        assertEquals("대한민국", ap.getNation());
        assertEquals("대구광역시", ap.getMetropolice());
        assertEquals(null, ap.getCity());
        assertEquals(null, ap.getTown());
    }

    @Test
    public void testParseAddress4() {
        ap.parseAddress("대한민국 경기도 수원시 팔달구");
        assertEquals("대한민국", ap.getNation());
        assertEquals("경기도", ap.getMetropolice());
        assertEquals("수원시", ap.getCity());
        assertEquals(null, ap.getTown());
    }

}