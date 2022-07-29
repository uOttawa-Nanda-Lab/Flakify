package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Encode_Request() throws IOException {
  ChannelBuffer encodeBuffer=ChannelBuffers.dynamicBuffer(2014);
  Channel channel=getCliendSideChannel(url);
  Request request=new Request();
  Person person=new Person();
  request.setData(person);
  codec.encode(channel,encodeBuffer,request);
  byte[] data=new byte[encodeBuffer.writerIndex()];
  encodeBuffer.readBytes(data);
  ChannelBuffer decodeBuffer=ChannelBuffers.wrappedBuffer(data);
  Request obj=(Request)codec.decode(channel,decodeBuffer);
  Assert.assertEquals(request.isBroken(),obj.isBroken());
  Assert.assertEquals(request.isHeartbeat(),obj.isHeartbeat());
  Assert.assertEquals(request.isTwoWay(),obj.isTwoWay());
  Assert.assertEquals(person,obj.getData());
}

}