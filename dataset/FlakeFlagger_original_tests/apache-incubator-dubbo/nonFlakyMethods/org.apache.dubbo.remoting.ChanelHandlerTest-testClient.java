@Test public void testClient() throws Throwable {
  if (PerformanceUtils.getProperty("server",null) == null) {
    logger.warn("Please set -Dserver=127.0.0.1:9911");
    return;
  }
  final String server=System.getProperty("server","127.0.0.1:9911");
  final String transporter=PerformanceUtils.getProperty(Constants.TRANSPORTER_KEY,Constants.DEFAULT_TRANSPORTER);
  final String serialization=PerformanceUtils.getProperty(Constants.SERIALIZATION_KEY,Constants.DEFAULT_REMOTING_SERIALIZATION);
  final int timeout=PerformanceUtils.getIntProperty(Constants.TIMEOUT_KEY,Constants.DEFAULT_TIMEOUT);
  int sleep=PerformanceUtils.getIntProperty("sleep",60 * 1000 * 60);
  final String url="exchange://" + server + "?transporter="+ transporter+ "&serialization="+ serialization+ "&timeout="+ timeout;
  ExchangeClient exchangeClient=initClient(url);
  Thread.sleep(sleep);
  closeClient(exchangeClient);
}
