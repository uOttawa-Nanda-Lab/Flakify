package io.castle.client.model;

import com.google.gson.JsonParser;
import io.castle.client.internal.json.CastleGsonModel;
import io.castle.client.utils.DeviceUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class DeviceUserAgentTest {
    private CastleGsonModel model = new CastleGsonModel();

    @Test
    public void jsonSerialized() {
        // Given
        DeviceUserAgent userAgent = new DeviceUserAgent();

        // When
        String payloadJson = model.getGson().toJson(userAgent);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"mobile\":false}");
    }

    @Test
    public void fullBuilderJson() {
        // Given
        DeviceUserAgent userAgent = new DeviceUserAgent();
        userAgent.setBrowser(DeviceUtils.USER_AGENT_BROWSER);
        userAgent.setDevice(DeviceUtils.USER_AGENT_DEVICE);
        userAgent.setFamily(DeviceUtils.USER_AGENT_FAMILY);
        userAgent.setMobile(DeviceUtils.USER_AGENT_MOBILE);
        userAgent.setOs(DeviceUtils.USER_AGENT_OS);
        userAgent.setPlatform(DeviceUtils.USER_AGENT_PLATFORM);
        userAgent.setRaw(DeviceUtils.USER_AGENT_PLATFORM);
        userAgent.setVersion(DeviceUtils.USER_AGENT_PLATFORM);

        // When
        String payloadJson = model.getGson().toJson(userAgent);
        JsonParser parser = new JsonParser();
        String expected = "{\"raw\":\"Mac OS X\",\"browser\":\"Opera\",\"version\":\"Mac OS X\",\"os\":\"Mac OS X 10.13.6\",\"mobile\":false,\"platform\":\"Mac OS X\",\"device\":\"Unknown\",\"family\":\"Opera\"}";
        // Then
        Assertions.assertThat(parser.parse(payloadJson)).isEqualTo(parser.parse(expected));

        Assert.assertEquals(userAgent.getBrowser(), DeviceUtils.USER_AGENT_BROWSER);
        Assert.assertEquals(userAgent.getDevice(), DeviceUtils.USER_AGENT_DEVICE);
        Assert.assertEquals(userAgent.getFamily(), DeviceUtils.USER_AGENT_FAMILY);
        Assert.assertEquals(userAgent.isMobile(), DeviceUtils.USER_AGENT_MOBILE);
        Assert.assertEquals(userAgent.getOs(), DeviceUtils.USER_AGENT_OS);
        Assert.assertEquals(userAgent.getPlatform(), DeviceUtils.USER_AGENT_PLATFORM);
        Assert.assertEquals(userAgent.getRaw(), DeviceUtils.USER_AGENT_PLATFORM);
        Assert.assertEquals(userAgent.getVersion(), DeviceUtils.USER_AGENT_PLATFORM);
    }
}
