@Test public void testMain() throws Exception {
  Proxy proxy=Proxy.getProxy(ITest.class,ITest.class);
  ITest instance=(ITest)proxy.newInstance(new InvocationHandler(){
    public Object invoke(    Object proxy,    Method method,    Object[] args) throws Throwable {
      if ("getName".equals(method.getName())) {
        assertEquals(args.length,0);
      }
 else       if ("setName".equals(method.getName())) {
        assertEquals(args.length,2);
        assertEquals(args[0],"qianlei");
        assertEquals(args[1],"hello");
      }
      return null;
    }
  }
);
  assertNull(instance.getName());
  instance.setName("qianlei","hello");
}
