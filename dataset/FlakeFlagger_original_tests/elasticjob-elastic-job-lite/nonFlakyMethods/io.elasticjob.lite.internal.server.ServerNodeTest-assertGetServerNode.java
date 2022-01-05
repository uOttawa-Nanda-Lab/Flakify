@Test public void assertGetServerNode(){
  assertThat(serverNode.getServerNode("127.0.0.1"),is("servers/127.0.0.1"));
}
