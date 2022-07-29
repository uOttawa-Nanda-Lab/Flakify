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
package org.apache.dubbo.rpc.protocol.thrift;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.buffer.ChannelBuffer;
import org.apache.dubbo.remoting.buffer.ChannelBuffers;
import org.apache.dubbo.remoting.exchange.Request;
import org.apache.dubbo.remoting.exchange.Response;
import org.apache.dubbo.remoting.exchange.support.DefaultFuture;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.RpcResult;
import org.apache.dubbo.rpc.gen.thrift.Demo;
import org.apache.dubbo.rpc.protocol.thrift.io.RandomAccessByteArrayOutputStream;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class ThriftCodecTest {

    private ThriftCodec codec = new ThriftCodec();
    private Channel channel = new MockedChannel(URL.valueOf("thrift://127.0.0.1"));

    static byte[] encodeFrame(byte[] content) {
        byte[] result = new byte[4 + content.length];
        TFramedTransport.encodeFrameSize(content.length, result);
        System.arraycopy(content, 0, result, 4, content.length);
        return result;
    }

    @Test public void testDecodeExceptionResponse() throws Exception{URL url=URL.valueOf(ThriftProtocol.NAME + "://127.0.0.1:40880/" + Demo.class.getName());Channel channel=new MockedChannel(url);RandomAccessByteArrayOutputStream bos=new RandomAccessByteArrayOutputStream(128);Request request=createRequest();DefaultFuture future=DefaultFuture.newFuture(channel,request,10);TMessage message=new TMessage("echoString",TMessageType.EXCEPTION,ThriftCodec.getSeqId());TTransport transport=new TIOStreamTransport(bos);TBinaryProtocol protocol=new TBinaryProtocol(transport);TApplicationException exception=new TApplicationException();int messageLength,headerLength;protocol.writeI16(ThriftCodec.MAGIC);protocol.writeI32(Integer.MAX_VALUE);protocol.writeI16(Short.MAX_VALUE);protocol.writeByte(ThriftCodec.VERSION);protocol.writeString(Demo.class.getName());protocol.writeI64(request.getId());protocol.getTransport().flush();headerLength=bos.size();protocol.writeMessageBegin(message);exception.write(protocol);protocol.writeMessageEnd();protocol.getTransport().flush();int oldIndex=messageLength=bos.size();try {bos.setWriteIndex(ThriftCodec.MESSAGE_LENGTH_INDEX);protocol.writeI32(messageLength);bos.setWriteIndex(ThriftCodec.MESSAGE_HEADER_LENGTH_INDEX);protocol.writeI16((short)(0xffff & headerLength));}  finally {bos.setWriteIndex(oldIndex);}ChannelBuffer bis=ChannelBuffers.wrappedBuffer(encodeFrame(bos.toByteArray()));Object obj=codec.decode((Channel)null,bis);Assert.assertNotNull(obj);Assert.assertTrue(obj instanceof Response);Response response=(Response)obj;Assert.assertTrue(response.getResult() instanceof RpcResult);RpcResult result=(RpcResult)response.getResult();Assert.assertTrue(result.hasException());Assert.assertTrue(result.getException() instanceof RpcException);}

    private Request createRequest() {

        RpcInvocation invocation = new RpcInvocation();

        invocation.setMethodName("echoString");

        invocation.setArguments(new Object[]{"Hello, World!"});

        invocation.setParameterTypes(new Class<?>[]{String.class});

        invocation.setAttachment(Constants.INTERFACE_KEY, Demo.Iface.class.getName());

        Request request = new Request(1L);

        request.setData(invocation);

        return request;

    }

}
