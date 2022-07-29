package io.castle.client.model;

import com.google.gson.JsonParser;
import io.castle.client.internal.json.CastleGsonModel;
import io.castle.client.utils.DeviceUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CastleUserDeviceContextTest {
    private CastleGsonModel model = new CastleGsonModel();

    @Test
    public void jsonSerialized() {
        // Given
        CastleUserDeviceContext deviceContext = new CastleUserDeviceContext();

        // When
        String payloadJson = model.getGson().toJson(deviceContext);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{}");
    }

    @Test
    public void fullBuilderJson() {
        // Given
        CastleUserDeviceContext deviceContext = new CastleUserDeviceContext();
        deviceContext.setIp(DeviceUtils.CONTEXT_IP);
        deviceContext.setType(DeviceUtils.CONTEXT_TYPE);

        DeviceUserAgent userAgent = new DeviceUserAgent();
        userAgent.setBrowser(DeviceUtils.USER_AGENT_BROWSER);
        userAgent.setDevice(DeviceUtils.USER_AGENT_DEVICE);
        userAgent.setFamily(DeviceUtils.USER_AGENT_FAMILY);
        userAgent.setMobile(DeviceUtils.USER_AGENT_MOBILE);
        userAgent.setOs(DeviceUtils.USER_AGENT_OS);
        userAgent.setPlatform(DeviceUtils.USER_AGENT_PLATFORM);
        userAgent.setRaw(DeviceUtils.USER_AGENT_RAW);
        userAgent.setVersion(DeviceUtils.USER_AGENT_VERSION);
        deviceContext.setUserAgent(userAgent);

        // When
        String payloadJson = model.getGson().toJson(deviceContext);
        JsonParser parser = new JsonParser();
        String expected = "{\"ip\":\"1.1.1.1\",\"user_agent\":{\"raw\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36 OPR/54.0.2952.51\",\"browser\":\"Opera\",\"version\":\"54.0.2952\",\"os\":\"Mac OS X 10.13.6\",\"mobile\":false,\"platform\":\"Mac OS X\",\"device\":\"Unknown\",\"family\":\"Opera\"},\"type\":\"desktop\"}";

        // Then
        Assertions.assertThat(parser.parse(payloadJson)).isEqualTo(parser.parse(expected));

        Assert.assertEquals(deviceContext.getIp(), DeviceUtils.CONTEXT_IP);
        Assert.assertEquals(deviceContext.getType(), DeviceUtils.CONTEXT_TYPE);
        Assert.assertEquals(deviceContext.getUserAgent(), userAgent);
    }
}
