@SuppressWarnings("unchecked") @Test public void testMockInvokerFromOverride_Invoke_check_ListPojo_empty(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getUsers.mock","force:return empty").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getUsers");
  Result ret=cluster.invoke(invocation);
  Assert.assertEquals(0,((List<User>)ret.getValue()).size());
}
