/** 
 * test counter won't count down incorrectly when invoker is destroyed for multiple times
 */
@Test public void test_multi_destory(){
  init(0);
  DubboAppender.doStart();
  DubboAppender.clear();
  demoServiceInvoker.destroy();
  demoServiceInvoker.destroy();
  Assert.assertEquals("hello",helloService.hello());
  Assert.assertEquals("should not  warning message",0,LogUtil.findMessage(errorMsg));
  LogUtil.checkNoError();
  DubboAppender.doStop();
  destoy();
}
