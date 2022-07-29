package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Decode_MigicCodec_Contain_ExchangeHeader() throws IOException {
  byte[] header=new byte[]{0,0,MAGIC_HIGH,MAGIC_LOW,0,0,0,0,0,0,0,0,0};
  Channel channel=getServerSideChannel(url);
  ChannelBuffer buffer=ChannelBuffers.wrappedBuffer(header);
  Object obj=codec.decode(channel,buffer);
  Assert.assertEquals(TelnetCodec.DecodeResult.NEED_MORE_INPUT,obj);
  Assert.assertEquals(2,buffer.readerIndex());
}

}