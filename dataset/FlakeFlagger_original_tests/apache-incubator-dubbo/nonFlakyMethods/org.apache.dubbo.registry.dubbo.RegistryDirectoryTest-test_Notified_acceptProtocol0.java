@Test public void test_Notified_acceptProtocol0(){
  URL errorPathUrl=URL.valueOf("notsupport:/xxx?refer=" + URL.encode("interface=" + service));
  RegistryDirectory registryDirectory=getRegistryDirectory(errorPathUrl);
  List<URL> serviceUrls=new ArrayList<URL>();
  URL dubbo1URL=URL.valueOf("dubbo://127.0.0.1:9098?lazy=true&methods=getXXX");
  URL dubbo2URL=URL.valueOf("injvm://127.0.0.1:9099?lazy=true&methods=getXXX");
  serviceUrls.add(dubbo1URL);
  serviceUrls.add(dubbo2URL);
  registryDirectory.notify(serviceUrls);
  invocation=new RpcInvocation();
  List<Invoker<DemoService>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
}
