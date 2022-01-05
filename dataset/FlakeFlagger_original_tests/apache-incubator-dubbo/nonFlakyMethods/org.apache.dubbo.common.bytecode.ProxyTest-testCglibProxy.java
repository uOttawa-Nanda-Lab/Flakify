@Test public void testCglibProxy() throws Exception {
  ITest test=(ITest)Proxy.getProxy(ITest.class).newInstance(new InvocationHandler(){
    public Object invoke(    Object proxy,    Method method,    Object[] args) throws Throwable {
      System.out.println(method.getName());
      return null;
    }
  }
);
  Enhancer enhancer=new Enhancer();
  enhancer.setSuperclass(test.getClass());
  enhancer.setCallback(new MethodInterceptor(){
    public Object intercept(    Object obj,    Method method,    Object[] args,    MethodProxy proxy) throws Throwable {
      return null;
    }
  }
);
  try {
    enhancer.create();
  }
 catch (  IllegalArgumentException e) {
    e.printStackTrace();
    Assert.fail();
  }
}
