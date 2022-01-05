@Test public void setProtocolsWithoutHttp11() throws Exception {
  try {
    client.setProtocols(Arrays.asList(Protocol.SPDY_3));
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
