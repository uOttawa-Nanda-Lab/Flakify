@Test public void testGetServerShutdownTimeoutFromShutdownWaitSeconds() throws Exception {
  System.setProperty(Constants.SHUTDOWN_WAIT_SECONDS_KEY,"1234");
  try {
    assertThat(ConfigUtils.getServerShutdownTimeout(),equalTo(1234 * 1000));
  }
  finally {
    System.clearProperty(Constants.SHUTDOWN_WAIT_SECONDS_KEY);
  }
}
