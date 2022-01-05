/** 
 * Reconnect log check, when the time is not enough for shutdown time, there is no error log, but there must be a warn log
 */
@Test public void testReconnectWarnLog() throws RemotingException, InterruptedException {
  int port=NetUtils.getAvailablePort();
  DubboAppender.doStart();
  String url="exchange://127.0.0.1:" + port + "/client.reconnect.test?check=false&client=netty3&"+ Constants.RECONNECT_KEY+ "="+ 1;
  try {
    Exchangers.connect(url);
  }
 catch (  Exception e) {
  }
  Thread.sleep(1500);
  Assert.assertEquals("no error message ",0,LogUtil.findMessage(Level.ERROR,"client reconnect to "));
  Assert.assertEquals("must have one warn message ",1,LogUtil.findMessage(Level.WARN,"client reconnect to "));
  DubboAppender.doStop();
}
