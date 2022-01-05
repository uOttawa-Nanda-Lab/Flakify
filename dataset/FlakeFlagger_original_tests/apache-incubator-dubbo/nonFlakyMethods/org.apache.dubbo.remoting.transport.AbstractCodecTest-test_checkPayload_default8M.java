public void test_checkPayload_default8M() throws Exception {
  Channel channel=mock(Channel.class);
  given(channel.getUrl()).willReturn(URL.valueOf("dubbo://1.1.1.1"));
  AbstractCodec.checkPayload(channel,1 * 1024 * 1024);
  try {
    AbstractCodec.checkPayload(channel,15 * 1024 * 1024);
  }
 catch (  IOException expected) {
    assertThat(expected.getMessage(),allOf(CoreMatchers.containsString("Data length too large: "),CoreMatchers.containsString("max payload: " + 8 * 1024 * 1024)));
  }
  verify(channel,VerificationModeFactory.atLeastOnce()).getUrl();
}
