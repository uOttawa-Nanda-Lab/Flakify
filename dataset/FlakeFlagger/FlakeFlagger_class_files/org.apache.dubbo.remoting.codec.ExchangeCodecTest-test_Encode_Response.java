/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.remoting.codec;


import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.Version;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.io.Bytes;
import org.apache.dubbo.common.io.UnsafeByteArrayOutputStream;
import org.apache.dubbo.common.serialize.ObjectOutput;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.buffer.ChannelBuffer;
import org.apache.dubbo.remoting.buffer.ChannelBuffers;
import org.apache.dubbo.remoting.exchange.Request;
import org.apache.dubbo.remoting.exchange.Response;
import org.apache.dubbo.remoting.exchange.codec.ExchangeCodec;
import org.apache.dubbo.remoting.telnet.codec.TelnetCodec;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.fail;

/**
 *
 *         byte 16
 *         0-1 magic code
 *         2 flag
 *         8 - 1-request/0-response
 *         7 - two way
 *         6 - heartbeat
 *         1-5 serialization id
 *         3 status
 *         20 ok
 *         90 error?
 *         4-11 id (long)
 *         12 -15 datalength
 */
public class ExchangeCodecTest extends TelnetCodecTest {
    // magic header.
    private static final short MAGIC = (short) 0xdabb;
    private static final byte MAGIC_HIGH = (byte) Bytes.short2bytes(MAGIC)[0];
    private static final byte MAGIC_LOW = (byte) Bytes.short2bytes(MAGIC)[1];
    Serialization serialization = getSerialization(Constants.DEFAULT_REMOTING_SERIALIZATION);

    private static Serialization getSerialization(String name) {
        Serialization serialization = ExtensionLoader.getExtensionLoader(Serialization.class).getExtension(name);
        return serialization;
    }

    private Object decode(byte[] request) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(request);
        AbstractMockChannel channel = getServerSideChannel(url);
        //decode
        Object obj = codec.decode(channel, buffer);
        return obj;
    }

    private byte[] getRequestBytes(Object obj, byte[] header) throws IOException {
        // encode request data.
        UnsafeByteArrayOutputStream bos = new UnsafeByteArrayOutputStream(1024);
        ObjectOutput out = serialization.serialize(url, bos);
        out.writeObject(obj);

        out.flushBuffer();
        bos.flush();
        bos.close();
        byte[] data = bos.toByteArray();
        byte[] len = Bytes.int2bytes(data.length);
        System.arraycopy(len, 0, header, 12, 4);
        byte[] request = join(header, data);
        return request;
    }

    private byte[] assemblyDataProtocol(byte[] header) {
        Person request = new Person();
        byte[] newbuf = join(header, objectToByte(request));
        return newbuf;
    }
    //===================================================================================

    @Before
    public void setUp() throws Exception {
        codec = new ExchangeCodec();
    }

    @Test public void test_Encode_Response() throws IOException{ChannelBuffer encodeBuffer=ChannelBuffers.dynamicBuffer(1024);Channel channel=getCliendSideChannel(url);Response response=new Response();response.setHeartbeat(true);response.setId(1001l);response.setStatus((byte)20);response.setVersion("11");Person person=new Person();response.setResult(person);codec.encode(channel,encodeBuffer,response);byte[] data=new byte[encodeBuffer.writerIndex()];encodeBuffer.readBytes(data);ChannelBuffer decodeBuffer=ChannelBuffers.wrappedBuffer(data);Response obj=(Response)codec.decode(channel,decodeBuffer);Assert.assertEquals(response.getId(),obj.getId());Assert.assertEquals(response.getStatus(),obj.getStatus());Assert.assertEquals(response.isHeartbeat(),obj.isHeartbeat());Assert.assertEquals(person,obj.getResult());}
}
