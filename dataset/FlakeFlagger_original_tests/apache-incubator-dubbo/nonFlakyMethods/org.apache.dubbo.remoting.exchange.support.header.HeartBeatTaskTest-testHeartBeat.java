@Test public void testHeartBeat() throws Exception {
  url=url.addParameter(Constants.DUBBO_VERSION_KEY,"2.1.1");
  channel.setAttribute(HeaderExchangeHandler.KEY_READ_TIMESTAMP,System.currentTimeMillis());
  channel.setAttribute(HeaderExchangeHandler.KEY_WRITE_TIMESTAMP,System.currentTimeMillis());
  Thread.sleep(2000L);
  task.run();
  List<Object> objects=channel.getSentObjects();
  Assert.assertTrue(objects.size() > 0);
  Object obj=objects.get(0);
  Assert.assertTrue(obj instanceof Request);
  Request request=(Request)obj;
  Assert.assertTrue(request.isHeartbeat());
}
