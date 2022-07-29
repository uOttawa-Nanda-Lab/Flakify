package io.castle.client.model;

import com.google.common.collect.ImmutableMap;
import io.castle.client.internal.json.CastleGsonModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CastleDeviceTest {

    private CastleGsonModel model = new CastleGsonModel();

    @Test
    public void jsonSerialized() {
        // Given
        CastleDevice device = new CastleDevice();
        device.setId("d_id");
        device.setName("d_name");
        device.setType("d_type");
        device.setManufacturer("d_manufacturer");
        device.setModel("d_model");

        // When
        String payloadJson = model.getGson().toJson(device);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"id\":\"d_id\",\"manufacturer\":\"d_manufacturer\",\"model\":\"d_model\",\"name\":\"d_name\",\"type\":\"d_type\"}");
    }

    @Test
    public void fullBuilderJson() {
        // Given
        CastleDevice device = CastleDevice.builder()
            .id("d_id")
            .name("d_name")
            .type("d_type")
            .manufacturer("d_manufacturer")
            .model("d_model")
            .build();

        // When
        String payloadJson = model.getGson().toJson(device);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"id\":\"d_id\",\"manufacturer\":\"d_manufacturer\",\"model\":\"d_model\",\"name\":\"d_name\",\"type\":\"d_type\"}");
    }
}
