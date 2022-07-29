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


import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.Codec2;
import org.apache.dubbo.remoting.buffer.ChannelBuffer;
import org.apache.dubbo.remoting.buffer.ChannelBuffers;
import org.apache.dubbo.remoting.telnet.codec.TelnetCodec;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class TelnetCodecTest {
    protected Codec2 codec;
    byte[] UP = new byte[]{27, 91, 65};
    byte[] DOWN = new byte[]{27, 91, 66};
    //======================================================
    URL url = URL.valueOf("dubbo://10.20.30.40:20880");

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        codec = new TelnetCodec();
    }

    protected AbstractMockChannel getServerSideChannel(URL url) {
        url = url.addParameter(AbstractMockChannel.LOCAL_ADDRESS, url.getAddress())
                .addParameter(AbstractMockChannel.REMOTE_ADDRESS, "127.0.0.1:12345");
        AbstractMockChannel channel = new AbstractMockChannel(url);
        return channel;
    }

    protected AbstractMockChannel getCliendSideChannel(URL url) {
        url = url.addParameter(AbstractMockChannel.LOCAL_ADDRESS, "127.0.0.1:12345")
                .addParameter(AbstractMockChannel.REMOTE_ADDRESS, url.getAddress());
        AbstractMockChannel channel = new AbstractMockChannel(url);
        return channel;
    }

    protected byte[] join(byte[] in1, byte[] in2) {
        byte[] ret = new byte[in1.length + in2.length];
        System.arraycopy(in1, 0, ret, 0, in1.length);
        System.arraycopy(in2, 0, ret, in1.length, in2.length);
        return ret;
    }

    protected byte[] objectToByte(Object obj) {
        byte[] bytes;
        if (obj instanceof String) {
            bytes = ((String) obj).getBytes();
        } else if (obj instanceof byte[]) {
            bytes = (byte[]) obj;
        } else {
            try {
                //object to bytearray
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                ObjectOutputStream oo = new ObjectOutputStream(bo);
                oo.writeObject(obj);
                bytes = bo.toByteArray();
                bo.close();
                oo.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return (bytes);
    }

    protected Object byteToObject(byte[] objBytes) throws Exception {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

    protected void testDecode_assertEquals(byte[] request, Object ret, boolean isServerside) throws IOException {
        //init channel
        Channel channel = isServerside ? getServerSideChannel(url) : getCliendSideChannel(url);
        //init request string
        ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(request);

        //decode
        Object obj = codec.decode(channel, buffer);
        Assert.assertEquals(ret, obj);
    }


    protected void testDecode_assertEquals(Object request, Object ret) throws IOException {
        testDecode_assertEquals(request, ret, null);
    }

    private void testDecode_assertEquals(Object request, Object ret, Object channelReceive) throws IOException {
        testDecode_assertEquals(null, request, ret, channelReceive);
    }

    private void testDecode_assertEquals(AbstractMockChannel channel, Object request, Object expectret, Object channelReceive) throws IOException {
        //init channel
        if (channel == null) {
            channel = getServerSideChannel(url);
        }

        byte[] buf = objectToByte(request);
        ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(buf);

        //decode
        Object obj = codec.decode(channel, buffer);
        Assert.assertEquals(expectret, obj);
        Assert.assertEquals(channelReceive, channel.getReceivedMessage());
    }

    @Test() public void testDecode_History_UP() throws IOException{AbstractMockChannel channel=getServerSideChannel(url);testDecode_assertEquals(channel,UP,Codec2.DecodeResult.NEED_MORE_INPUT,null);String request1="aaa\n";Object expected1="aaa";testDecode_assertEquals(channel,request1,expected1,null);testDecode_assertEquals(channel,UP,Codec2.DecodeResult.NEED_MORE_INPUT,expected1);}

    
    
    /*@Test()
    public void testDecode_History_UP_DOWN_MULTI() throws IOException{
        AbstractMockChannel channel = getServerSideChannel(url);
        
        String request1 = "aaa\n"; 
        Object expected1 = request1.replace("\n", "");
        //init history 
        testDecode_assertEquals(channel, request1, expected1, null);
        
        String request2 = "bbb\n"; 
        Object expected2 = request2.replace("\n", "");
        //init history 
        testDecode_assertEquals(channel, request2, expected2, null);
        
        String request3 = "ccc\n"; 
        Object expected3= request3.replace("\n", "");
        //init history 
        testDecode_assertEquals(channel, request3, expected3, null);
        
        byte[] UP = new byte[] {27, 91, 65};
        byte[] DOWN = new byte[] {27, 91, 66};
        //history[aaa,bbb,ccc]
        testDecode_assertEquals(channel, UP, Codec.NEED_MORE_INPUT, expected3);
        testDecode_assertEquals(channel, DOWN, Codec.NEED_MORE_INPUT, expected3);
        testDecode_assertEquals(channel, UP, Codec.NEED_MORE_INPUT, expected2);
        testDecode_assertEquals(channel, UP, Codec.NEED_MORE_INPUT, expected1);
        testDecode_assertEquals(channel, UP, Codec.NEED_MORE_INPUT, expected1);
        testDecode_assertEquals(channel, UP, Codec.NEED_MORE_INPUT, expected1);
        testDecode_assertEquals(channel, DOWN, Codec.NEED_MORE_INPUT, expected2);
        testDecode_assertEquals(channel, DOWN, Codec.NEED_MORE_INPUT, expected3);
        testDecode_assertEquals(channel, DOWN, Codec.NEED_MORE_INPUT, expected3);
        testDecode_assertEquals(channel, DOWN, Codec.NEED_MORE_INPUT, expected3);
        testDecode_assertEquals(channel, UP, Codec.NEED_MORE_INPUT, expected2);
    }*/

    //======================================================
    public static class Person implements Serializable {
        private static final long serialVersionUID = 3362088148941547337L;
        public String name;
        public String sex;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((sex == null) ? 0 : sex.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Person other = (Person) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (sex == null) {
                if (other.sex != null)
                    return false;
            } else if (!sex.equals(other.sex))
                return false;
            return true;
        }

    }

}
