@Test public void testMessageLengthExceedPayloadLimitWhenEncode() throws Exception {
  Request request=new Request(1L);
  request.setData("hello");
  ChannelBuffer encodeBuffer=ChannelBuffers.dynamicBuffer(512);
  AbstractMockChannel channel=getCliendSideChannel(url.addParameter(Constants.PAYLOAD_KEY,4));
  try {
    codec.encode(channel,encodeBuffer,request);
    Assert.fail();
  }
 catch (  IOException e) {
    Assert.assertTrue(e.getMessage().startsWith("Data length too large: " + 6));
  }
  Response response=new Response(1L);
  response.setResult("hello");
  encodeBuffer=ChannelBuffers.dynamicBuffer(512);
  channel=getServerSideChannel(url.addParameter(Constants.PAYLOAD_KEY,4));
  codec.encode(channel,encodeBuffer,response);
  Assert.assertTrue(channel.getReceivedMessage() instanceof Response);
  Response receiveMessage=(Response)channel.getReceivedMessage();
  Assert.assertEquals(Response.BAD_RESPONSE,receiveMessage.getStatus());
  Assert.assertTrue(receiveMessage.getErrorMessage().contains("Data length too large: "));
}
