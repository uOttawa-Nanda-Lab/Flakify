package io.castle.client.model;

import com.google.gson.JsonParser;
import io.castle.client.internal.json.CastleGsonModel;
import io.castle.client.utils.DeviceUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CastleUserDevicesTest {
    private CastleGsonModel model = new CastleGsonModel();

    @Test
    public void jsonSerialized() {
        // Given
        CastleUserDevices devices = new CastleUserDevices();

        // When
        String payloadJson = model.getGson().toJson(devices);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"total_count\":0}");
    }

    @Test
    public void fullBuilderJson() {
        // Given
        CastleUserDevices devices = new CastleUserDevices();

        List<CastleUserDevice> devicesList = new ArrayList<>();

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

        devicesList.add(device);

        devices.setDevices(devicesList);

        // When
        String payloadJson = model.getGson().toJson(devices);
        JsonParser parser = new JsonParser();
        String expected = "{\"total_count\":1,\"data\":[{\"token\":\"abcdefg12345\",\"risk\":0.0,\"created_at\":\"2018-06-15T16:36:22.916Z\",\"last_seen_at\":\"2018-07-19T23:09:29.681Z\",\"context\":{\"ip\":\"1.1.1.1\",\"user_agent\":{\"raw\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36 OPR/54.0.2952.51\",\"browser\":\"Opera\",\"version\":\"54.0.2952\",\"os\":\"Mac OS X 10.13.6\",\"mobile\":false,\"platform\":\"Mac OS X\",\"device\":\"Unknown\",\"family\":\"Opera\"},\"type\":\"desktop\"},\"is_current_device\":true}]}";

        // Then
        Assertions.assertThat(parser.parse(payloadJson)).isEqualTo(parser.parse(expected));

        Assert.assertEquals(devices.getTotalCount(), devicesList.size());
        Assert.assertEquals(devices.getDevices(), devicesList);

        devices.setDevices(null);
        Assert.assertEquals(0, devices.getTotalCount());
    }
}
