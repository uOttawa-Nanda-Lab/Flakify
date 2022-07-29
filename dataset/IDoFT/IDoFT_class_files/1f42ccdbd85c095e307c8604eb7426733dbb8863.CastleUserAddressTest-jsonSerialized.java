package io.castle.client.model;

import io.castle.client.internal.json.CastleGsonModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CastleUserAddressTest {

    private CastleGsonModel model = new CastleGsonModel();

    @Test
    public void jsonSerialized() {
        // Given
        CastleUserAddress address = new CastleUserAddress();
        address.setCity("city");
        address.setCountry("country");
        address.setPostalCode("12345");
        address.setRegion("region");
        address.setStreet("street 1");

        // When
        String payloadJson = model.getGson().toJson(address);

        // Then
        Assertions.assertThat(payloadJson).isEqualTo("{\"street\":\"street 1\",\"city\":\"city\",\"postal_code\":\"12345\",\"region\":\"region\",\"country\":\"country\"}");
    }
}
