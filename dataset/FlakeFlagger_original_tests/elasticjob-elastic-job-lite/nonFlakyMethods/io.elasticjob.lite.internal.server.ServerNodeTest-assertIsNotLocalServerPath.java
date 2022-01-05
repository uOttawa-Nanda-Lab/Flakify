@Test public void assertIsNotLocalServerPath(){
  assertFalse(serverNode.isLocalServerPath("/test_job/servers/127.0.0.2"));
}
