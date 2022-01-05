@SuppressWarnings("unchecked") @Test public void testMockInvokerFromOverride_Invoke_check_ListPojo(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getUsers.mock","force:return [{id:1, name:\"hi1\"}, {id:2, name:\"hi2\"}]").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getUsers");
  Result ret=cluster.invoke(invocation);
  List<User> rl=(List<User>)ret.getValue();
  System.out.println(rl);
  Assert.assertEquals(2,rl.size());
  Assert.assertEquals("hi1",((User)rl.get(0)).getName());
}
