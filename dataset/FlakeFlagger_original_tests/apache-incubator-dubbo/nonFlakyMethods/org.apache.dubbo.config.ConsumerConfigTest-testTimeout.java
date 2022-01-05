@Test public void testTimeout() throws Exception {
  try {
    System.clearProperty("sun.rmi.transport.tcp.responseTimeout");
    ConsumerConfig consumer=new ConsumerConfig();
    consumer.setTimeout(10);
    assertThat(consumer.getTimeout(),is(10));
    assertThat(System.getProperty("sun.rmi.transport.tcp.responseTimeout"),equalTo("10"));
  }
  finally {
    System.clearProperty("sun.rmi.transport.tcp.responseTimeout");
  }
}
