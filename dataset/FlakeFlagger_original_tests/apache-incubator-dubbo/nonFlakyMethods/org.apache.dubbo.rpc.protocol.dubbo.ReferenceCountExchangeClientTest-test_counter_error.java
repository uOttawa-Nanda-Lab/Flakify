/** 
 * Test against invocation still succeed even if counter has error
 */
@Test public void test_counter_error(){
  init(0);
  DubboAppender.doStart();
  DubboAppender.clear();
  ReferenceCountExchangeClient client=getReferenceClient(helloServiceInvoker);
  client.close();
  Assert.assertEquals("hello",helloService.hello());
  Assert.assertEquals("should not warning message",0,LogUtil.findMessage(errorMsg));
  client.close();
  try {
    Thread.sleep(1000);
  }
 catch (  InterruptedException e) {
    Assert.fail();
  }
  Assert.assertEquals("hello",helloService.hello());
  Assert.assertEquals("should warning message",1,LogUtil.findMessage(errorMsg));
  Assert.assertEquals("hello",helloService.hello());
  Assert.assertEquals("should warning message",1,LogUtil.findMessage(errorMsg));
  DubboAppender.doStop();
  Assert.assertEquals("client status available",true,helloServiceInvoker.isAvailable());
  client.close();
  Assert.assertEquals("client status close",false,client.isClosed());
  Assert.assertEquals("client status close",false,helloServiceInvoker.isAvailable());
  destoy();
}
