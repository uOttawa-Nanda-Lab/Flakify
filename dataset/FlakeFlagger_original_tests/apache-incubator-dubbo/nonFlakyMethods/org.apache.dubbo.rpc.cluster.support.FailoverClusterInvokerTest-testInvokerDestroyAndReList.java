/** 
 * When invokers in directory changes after a failed request but just before a retry effort, then we should reselect from the latest invokers before retry.
 */
@Test public void testInvokerDestroyAndReList(){
  final URL url=URL.valueOf("test://localhost/" + Demo.class.getName() + "?loadbalance=roundrobin&retries="+ retries);
  RpcException exception=new RpcException(RpcException.TIMEOUT_EXCEPTION);
  MockInvoker<Demo> invoker1=new MockInvoker<Demo>(Demo.class,url);
  invoker1.setException(exception);
  MockInvoker<Demo> invoker2=new MockInvoker<Demo>(Demo.class,url);
  invoker2.setException(exception);
  final List<Invoker<Demo>> invokers=new ArrayList<Invoker<Demo>>();
  invokers.add(invoker1);
  invokers.add(invoker2);
  Callable<Object> callable=new Callable<Object>(){
    public Object call() throws Exception {
      for (      Invoker<Demo> invoker : invokers) {
        invoker.destroy();
      }
      invokers.clear();
      MockInvoker<Demo> invoker3=new MockInvoker<Demo>(Demo.class,url);
      invokers.add(invoker3);
      return null;
    }
  }
;
  invoker1.setCallable(callable);
  invoker2.setCallable(callable);
  RpcInvocation inv=new RpcInvocation();
  inv.setMethodName("test");
  Directory<Demo> dic=new MockDirectory<Demo>(url,invokers);
  FailoverClusterInvoker<Demo> clusterinvoker=new FailoverClusterInvoker<Demo>(dic);
  clusterinvoker.invoke(inv);
}
