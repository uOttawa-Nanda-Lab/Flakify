@Test public void testStartNoPort() throws Exception {
  receiver.setRemoteHost(TEST_HOST_NAME);
  receiver.start();
  assertFalse(receiver.isStarted());
  int count=lc.getStatusManager().getCount();
  Status status=lc.getStatusManager().getCopyOfStatusList().get(count - 1);
  assertTrue(status.getMessage().contains("port"));
}
