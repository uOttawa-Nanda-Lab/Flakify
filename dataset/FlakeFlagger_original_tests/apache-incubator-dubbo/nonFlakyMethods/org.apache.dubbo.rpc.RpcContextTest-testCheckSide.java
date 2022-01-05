@Test public void testCheckSide(){
  RpcContext context=RpcContext.getContext();
  context.setUrl(URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1"));
  Assert.assertFalse(context.isConsumerSide());
  Assert.assertTrue(context.isProviderSide());
  context.setUrl(URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&side=consumer"));
  Assert.assertTrue(context.isConsumerSide());
  Assert.assertFalse(context.isProviderSide());
}
