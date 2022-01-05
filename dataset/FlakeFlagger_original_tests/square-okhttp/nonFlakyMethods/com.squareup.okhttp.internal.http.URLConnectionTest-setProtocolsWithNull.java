@Test public void setProtocolsWithNull() throws Exception {
  try {
    client.setProtocols(Arrays.asList(Protocol.HTTP_11,null));
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
