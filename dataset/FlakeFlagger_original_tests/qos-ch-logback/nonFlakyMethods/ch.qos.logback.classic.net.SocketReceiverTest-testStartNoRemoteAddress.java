@Test public void testStartNoRemoteAddress() throws Exception {
  receiver.start();
  assertFalse(receiver.isStarted());
  int count=lc.getStatusManager().getCount();
  Status status=lc.getStatusManager().getCopyOfStatusList().get(count - 1);
  assertTrue(status.getMessage().contains("host"));
}
