/*
 * Copyright 2017-2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazon.dtasdk.v3.messages;

import com.amazon.dtasdk.serializer.JacksonSerializer;
import com.amazon.dtasdk.serializer.SerializationException;
import com.amazon.dtasdk.v3.serialization.messages.GetUserIdSerializableRequest;
import com.amazon.dtasdk.base.InstantAccessOperationValue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstantAccessRequestTest {
    private JacksonSerializer serializer = new JacksonSerializer();

    @Test
    public void testSerialize() throws SerializationException {
        GetUserIdSerializableRequest request = new GetUserIdSerializableRequest();
        request.setOperation(InstantAccessOperationValue.GETUSERID);
        request.setInfoField1("nobody@amazon.com");
        request.setInfoField2("AMZN");

        String requestString = serializer.encode(request);

        assertEquals("{\"operation\":\"GetUserId\",\"infoField1\":\"nobody@amazon.com\",\"infoField2\":\"AMZN\"}",
                requestString);
    }

    @Test
    public void testDeserialize() throws SerializationException {
        String str = "{\"operation\" : \"GetUserId\", \"infoField1\" : \"nobody@amazon.com\",\"infoField2\" : \"AMZN\"}";
        GetUserIdSerializableRequest request = serializer.decode(str, GetUserIdSerializableRequest.class);

        assertEquals(InstantAccessOperationValue.GETUSERID, request.getOperation());
        assertEquals("nobody@amazon.com", request.getInfoField1());
        assertEquals("AMZN", request.getInfoField2());
    }

    @Test
    public void testDeserializeExtraFields() throws SerializationException {
        String str = "{\"operation\" : \"GetUserId\", \"infoField1\" : \"nobody@amazon.com\",\"infoField2\" : \"AMZN\", \"newField\":\"newValue\"}";
        GetUserIdSerializableRequest request = serializer.decode(str, GetUserIdSerializableRequest.class);

        assertEquals(InstantAccessOperationValue.GETUSERID, request.getOperation());
        assertEquals("nobody@amazon.com", request.getInfoField1());
        assertEquals("AMZN", request.getInfoField2());
    }
}
