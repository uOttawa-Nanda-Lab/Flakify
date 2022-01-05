@Test(expected=RuntimeException.class) public void testRegFail(){
  ServiceClassHolder.getInstance().pushServiceClass(DemoService.class);
  URL nettyUrl=exportUrl.addParameter(Constants.EXTENSION_KEY,"com.not.existing.Filter");
  protocol.export(proxy.getInvoker(new DemoService(),IDemoService.class,nettyUrl));
}
