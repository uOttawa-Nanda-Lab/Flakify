package io.castle.client.model;

import io.castle.client.internal.json.CastleGsonModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;

public class CastleUserTest {

    private CastleGsonModel model = new CastleGsonModel();

    @Test
    public void jsonSerialized() {
        // Given
        CastleUser user = new CastleUser();
        user.setId("userId");
        user.setName("name");
        user.setEmail("test@example.com");
        user.setDevicesCount(2);
        user.setLeaksCount(4);
        user.setLastSeenAt("d_model");
        user.setCustomAttributes(new HashMap<String, String>());
        user.setUpdatedAt("2019-03-26T11:31:19.968Z");
        user.setCreatedAt("2019-03-26T11:31:19.968Z");
        user.setFlaggedAt("2019-03-26T11:31:19.968Z");
        user.setPhone("12345");
        user.setRisk(1.1);
        user.setUsername("username");

        // When
        String payloadJson = model.getGson().toJson(user);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"id\":\"userId\",\"created_at\":\"2019-03-26T11:31:19.968Z\",\"updated_at\":\"2019-03-26T11:31:19.968Z\",\"last_seen_at\":\"d_model\",\"flagged_at\":\"2019-03-26T11:31:19.968Z\",\"risk\":1.1,\"leaks_count\":4,\"devices_count\":2,\"email\":\"test@example.com\",\"name\":\"name\",\"username\":\"username\",\"phone\":\"12345\",\"custom_attributes\":{}}");
    }
}
