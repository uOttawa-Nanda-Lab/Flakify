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
package org.apache.dubbo.common.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BytesTest {
    private final byte[] b1 = "adpfioha;eoh;aldfadl;kfadslkfdajfio123431241235123davas;odvwe;lmzcoqpwoewqogineopwqihwqetup\n\tejqf;lajsfd中文字符0da0gsaofdsf==adfasdfs".getBytes();
    private final String C64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="; //default base64.
    private byte[] bytes1 = {3, 12, 14, 41, 12, 2, 3, 12, 4, 67, 23};
    private byte[] bytes2 = {3, 12, 14, 41, 12, 2, 3, 12, 4, 67};

    @Test
    public void testMain() throws Exception {
        short s = (short) 0xabcd;
        assertThat(s, is(Bytes.bytes2short(Bytes.short2bytes(s))));

        int i = 198284;
        assertThat(i, is(Bytes.bytes2int(Bytes.int2bytes(i))));

        long l = 13841747174L;
        assertThat(l, is(Bytes.bytes2long(Bytes.long2bytes(l))));

        float f = 1.3f;
        assertThat(f, is(Bytes.bytes2float(Bytes.float2bytes(f))));

        double d = 11213.3;
        assertThat(d, is(Bytes.bytes2double(Bytes.double2bytes(d))));

        assertThat(Bytes.int2bytes(i), is(int2bytes(i)));
        assertThat(Bytes.long2bytes(l), is(long2bytes(l)));

        String str = Bytes.bytes2base64("dubbo".getBytes());
        byte[] bytes = Bytes.base642bytes(str, 0, str.length());
        assertThat(bytes, is("dubbo".getBytes()));

        byte[] bytesWithC64 = Bytes.base642bytes(str, C64);
        assertThat(bytesWithC64, is(bytes));
    }

    private byte[] int2bytes(int x) {
        byte[] bb = new byte[4];
        bb[0] = (byte) (x >> 24);
        bb[1] = (byte) (x >> 16);
        bb[2] = (byte) (x >> 8);
        bb[3] = (byte) (x >> 0);
        return bb;
    }

    private byte[] long2bytes(long x) {
        byte[] bb = new byte[8];
        bb[0] = (byte) (x >> 56);
        bb[1] = (byte) (x >> 48);
        bb[2] = (byte) (x >> 40);
        bb[3] = (byte) (x >> 32);
        bb[4] = (byte) (x >> 24);
        bb[5] = (byte) (x >> 16);
        bb[6] = (byte) (x >> 8);
        bb[7] = (byte) (x >> 0);
        return bb;
    }
}