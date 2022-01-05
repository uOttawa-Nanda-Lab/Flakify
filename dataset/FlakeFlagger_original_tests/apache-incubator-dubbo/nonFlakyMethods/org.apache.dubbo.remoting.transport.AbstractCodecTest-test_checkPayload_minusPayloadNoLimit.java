public void test_checkPayload_minusPayloadNoLimit() throws Exception {
  Channel channel=mock(Channel.class);
  given(channel.getUrl()).willReturn(URL.valueOf("dubbo://1.1.1.1?payload=-1"));
  AbstractCodec.checkPayload(channel,15 * 1024 * 1024);
  verify(channel,VerificationModeFactory.atLeastOnce()).getUrl();
}
