@Test(expected=RpcException.class) public void testServletWithoutWebConfig(){
  ServiceClassHolder.getInstance().pushServiceClass(DemoService.class);
  URL servletUrl=exportUrl.addParameter(Constants.SERVER_KEY,"servlet");
  protocol.export(proxy.getInvoker(new DemoService(),IDemoService.class,servletUrl));
}
