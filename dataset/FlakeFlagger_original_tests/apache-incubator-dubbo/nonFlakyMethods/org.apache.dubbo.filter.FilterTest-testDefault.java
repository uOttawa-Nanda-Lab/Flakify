@Test public void testDefault(){
  Invoker<FilterTest> invoker=new MyInvoker<FilterTest>(null);
  Invocation invocation=new MockInvocation("bbb");
  Result res=myFilter.invoke(invoker,invocation);
  System.out.println(res);
}
