package io.castle.client.model;

import com.google.common.collect.ImmutableMap;
import io.castle.client.internal.json.CastleGsonModel;
import io.castle.client.utils.DeviceUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class CastleUserDeviceTest {

    private CastleGsonModel model = new CastleGsonModel();

    @Test
    public void jsonSerialized() {
        // Given
        CastleUserDevice device = new CastleUserDevice();

        // When
        String payloadJson = model.getGson().toJson(device);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"risk\":0.0,\"is_current_device\":false}");
    }

    @Test
    public void fullBuilderJson() {
        // Given
        CastleUserDevice device = new CastleUserDevice();
        device.setToken(DeviceUtils.DEVICE_TOKEN);
        device.setCreatedAt(DeviceUtils.DEVICE_CREATED_AT);
        device.setLastSeenAt(DeviceUtils.DEVICE_LAST_SEEN_AT);
        device.setApprovedAt(DeviceUtils.DEVICE_APPROVED_AT);
        device.setEscalatedAt(DeviceUtils.DEVICE_ESCALATED_AT);
        device.setMitigatedAt(DeviceUtils.DEVICE_MITIGATED_AT);
        device.setCurrentDevice(true);

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

        device.setContext(deviceContext);

        // When
        String payloadJson = model.getGson().toJson(device);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"token\":\"abcdefg12345\",\"risk\":0.0,\"created_at\":\"2018-06-15T16:36:22.916Z\",\"last_seen_at\":\"2018-07-19T23:09:29.681Z\",\"context\":{\"ip\":\"1.1.1.1\",\"user_agent\":{\"raw\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36 OPR/54.0.2952.51\",\"browser\":\"Opera\",\"version\":\"54.0.2952\",\"os\":\"Mac OS X 10.13.6\",\"mobile\":false,\"platform\":\"Mac OS X\",\"device\":\"Unknown\",\"family\":\"Opera\"},\"type\":\"desktop\"},\"is_current_device\":true}");

        Assert.assertEquals(device.getToken(), DeviceUtils.DEVICE_TOKEN);
        Assert.assertEquals(device.getCreatedAt(), DeviceUtils.DEVICE_CREATED_AT);
        Assert.assertEquals(device.getLastSeenAt(), DeviceUtils.DEVICE_LAST_SEEN_AT);
        Assert.assertEquals(device.getApprovedAt(), DeviceUtils.DEVICE_APPROVED_AT);
        Assert.assertEquals(device.getEscalatedAt(), DeviceUtils.DEVICE_ESCALATED_AT);
        Assert.assertEquals(device.getMitigatedAt(), DeviceUtils.DEVICE_MITIGATED_AT);
        Assert.assertEquals(device.isCurrentDevice(), true);
        Assert.assertEquals(device.getContext(), deviceContext);
    }
}
