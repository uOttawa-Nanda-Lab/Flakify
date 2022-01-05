@Test public void testServer() throws Exception {
  if (PerformanceUtils.getProperty("port",null) == null) {
    logger.warn("Please set -Dport=9911");
    return;
  }
  final int port=PerformanceUtils.getIntProperty("port",9911);
  final boolean telnet=PerformanceUtils.getBooleanProperty("telnet",true);
  if (telnet)   statTelnetServer(port + 1);
  server=statServer();
synchronized (PerformanceServerTest.class) {
    while (true) {
      try {
        PerformanceServerTest.class.wait();
      }
 catch (      InterruptedException e) {
      }
    }
  }
}
