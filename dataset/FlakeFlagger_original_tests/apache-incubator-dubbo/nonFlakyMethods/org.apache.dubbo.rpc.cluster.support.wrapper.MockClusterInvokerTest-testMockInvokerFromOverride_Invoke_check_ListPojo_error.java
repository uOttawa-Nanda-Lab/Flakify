@Test public void testMockInvokerFromOverride_Invoke_check_ListPojo_error(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getUsers.mock","force:return [{id:x, name:\"hi1\"}]").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getUsers");
  try {
    cluster.invoke(invocation);
  }
 catch (  RpcException e) {
  }
}
