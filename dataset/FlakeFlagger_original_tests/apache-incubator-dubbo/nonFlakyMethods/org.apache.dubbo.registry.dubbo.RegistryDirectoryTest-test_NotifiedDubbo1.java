@Test public void test_NotifiedDubbo1(){
  URL errorPathUrl=URL.valueOf("notsupport:/" + "xxx" + "?refer="+ URL.encode("interface=" + service));
  RegistryDirectory registryDirectory=getRegistryDirectory(errorPathUrl);
  List<URL> serviceUrls=new ArrayList<URL>();
  URL Dubbo1URL=URL.valueOf("dubbo://127.0.0.1:9098?lazy=true");
  serviceUrls.add(Dubbo1URL.addParameter("methods","getXXX"));
  registryDirectory.notify(serviceUrls);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  invocation=new RpcInvocation();
  List<Invoker<DemoService>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers.size());
  invocation.setMethodName("getXXX");
  invokers=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers.size());
  Assert.assertEquals(DemoService.class.getName(),invokers.get(0).getUrl().getPath());
}
