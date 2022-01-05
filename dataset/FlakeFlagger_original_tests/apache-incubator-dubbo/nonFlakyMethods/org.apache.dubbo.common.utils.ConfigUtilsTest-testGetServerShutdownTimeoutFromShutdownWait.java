@Test public void testGetServerShutdownTimeoutFromShutdownWait() throws Exception {
  System.setProperty(Constants.SHUTDOWN_WAIT_KEY,"1234");
  try {
    assertThat(ConfigUtils.getServerShutdownTimeout(),equalTo(1234));
  }
  finally {
    System.clearProperty(Constants.SHUTDOWN_WAIT_KEY);
  }
}
