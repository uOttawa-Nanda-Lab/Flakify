@Test public void testGetContext(){
  RpcContext rpcContext=RpcContext.getContext();
  Assert.assertNotNull(rpcContext);
  RpcContext.removeContext();
  Assert.assertNotNull(RpcContext.getContext());
  Assert.assertNotEquals(rpcContext,RpcContext.getContext());
  RpcContext serverRpcContext=RpcContext.getServerContext();
  Assert.assertNotNull(serverRpcContext);
  RpcContext.removeServerContext();
  Assert.assertNotEquals(serverRpcContext,RpcContext.getServerContext());
}
