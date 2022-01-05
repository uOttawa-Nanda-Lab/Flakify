@SuppressWarnings("unchecked") @Test public void testMockInvokerFromOverride_Invoke_check_ListString(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getListString.mock","force:return [\"hi\",\"hi2\"]").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getListString");
  Result ret=cluster.invoke(invocation);
  List<String> rl=(List<String>)ret.getValue();
  Assert.assertEquals(2,rl.size());
  Assert.assertEquals("hi",rl.get(0));
}
