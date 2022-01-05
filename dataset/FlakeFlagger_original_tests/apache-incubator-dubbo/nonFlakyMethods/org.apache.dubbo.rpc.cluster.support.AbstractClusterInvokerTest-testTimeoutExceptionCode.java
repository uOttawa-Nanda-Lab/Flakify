@Test() public void testTimeoutExceptionCode(){
  List<Invoker<DemoService>> invokers=new ArrayList<Invoker<DemoService>>();
  invokers.add(new Invoker<DemoService>(){
    @Override public Class<DemoService> getInterface(){
      return DemoService.class;
    }
    public URL getUrl(){
      return URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/"+ DemoService.class.getName());
    }
    @Override public boolean isAvailable(){
      return false;
    }
    public Result invoke(    Invocation invocation) throws RpcException {
      throw new RpcException(RpcException.TIMEOUT_EXCEPTION,"test timeout");
    }
    @Override public void destroy(){
    }
  }
);
  Directory<DemoService> directory=new StaticDirectory<DemoService>(invokers);
  FailoverClusterInvoker<DemoService> failoverClusterInvoker=new FailoverClusterInvoker<DemoService>(directory);
  try {
    failoverClusterInvoker.invoke(new RpcInvocation("sayHello",new Class<?>[0],new Object[0]));
    Assert.fail();
  }
 catch (  RpcException e) {
    Assert.assertEquals(RpcException.TIMEOUT_EXCEPTION,e.getCode());
  }
  ForkingClusterInvoker<DemoService> forkingClusterInvoker=new ForkingClusterInvoker<DemoService>(directory);
  try {
    forkingClusterInvoker.invoke(new RpcInvocation("sayHello",new Class<?>[0],new Object[0]));
    Assert.fail();
  }
 catch (  RpcException e) {
    Assert.assertEquals(RpcException.TIMEOUT_EXCEPTION,e.getCode());
  }
  FailfastClusterInvoker<DemoService> failfastClusterInvoker=new FailfastClusterInvoker<DemoService>(directory);
  try {
    failfastClusterInvoker.invoke(new RpcInvocation("sayHello",new Class<?>[0],new Object[0]));
    Assert.fail();
  }
 catch (  RpcException e) {
    Assert.assertEquals(RpcException.TIMEOUT_EXCEPTION,e.getCode());
  }
}
