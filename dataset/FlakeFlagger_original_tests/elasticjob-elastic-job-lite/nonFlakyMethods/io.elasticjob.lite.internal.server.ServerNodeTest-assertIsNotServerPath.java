@Test public void assertIsNotServerPath(){
  assertFalse(serverNode.isServerPath("/test_job/servers/255.255.255.256"));
}
