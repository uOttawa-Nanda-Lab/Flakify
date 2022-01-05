@Test public void testParametersMerge(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  URL regurl=noMeaningUrl.addParameter("test","reg").addParameterAndEncoded(Constants.REFER_KEY,"key=query&" + Constants.LOADBALANCE_KEY + "="+ LeastActiveLoadBalance.NAME);
  RegistryDirectory<RegistryDirectoryTest> registryDirectory2=new RegistryDirectory(RegistryDirectoryTest.class,regurl);
  registryDirectory2.setProtocol(protocol);
  List<URL> serviceUrls=new ArrayList<URL>();
{
    serviceUrls.clear();
    serviceUrls.add(SERVICEURL.addParameter("methods","getXXX1"));
    registryDirectory.notify(serviceUrls);
    invocation=new RpcInvocation();
    List invokers=registryDirectory.list(invocation);
    Invoker invoker=(Invoker)invokers.get(0);
    URL url=invoker.getUrl();
    Assert.assertEquals(null,url.getParameter("key"));
  }
{
    serviceUrls.clear();
    serviceUrls.add(SERVICEURL.addParameter("methods","getXXX2").addParameter("key","provider"));
    registryDirectory.notify(serviceUrls);
    invocation=new RpcInvocation();
    List invokers=registryDirectory.list(invocation);
    Invoker invoker=(Invoker)invokers.get(0);
    URL url=invoker.getUrl();
    Assert.assertEquals("provider",url.getParameter("key"));
  }
{
    serviceUrls.clear();
    serviceUrls.add(SERVICEURL.addParameter("methods","getXXX3").addParameter("key","provider"));
    registryDirectory2.notify(serviceUrls);
    invocation=new RpcInvocation();
    List invokers=registryDirectory2.list(invocation);
    Invoker invoker=(Invoker)invokers.get(0);
    URL url=invoker.getUrl();
    Assert.assertEquals("query",url.getParameter("key"));
  }
{
    serviceUrls.clear();
    serviceUrls.add(SERVICEURL.addParameter("methods","getXXX1"));
    registryDirectory.notify(serviceUrls);
    invocation=new RpcInvocation();
    List invokers=registryDirectory.list(invocation);
    Invoker invoker=(Invoker)invokers.get(0);
    URL url=invoker.getUrl();
    Assert.assertEquals(false,url.getParameter(Constants.CHECK_KEY,false));
  }
{
    serviceUrls.clear();
    serviceUrls.add(SERVICEURL.addParameter(Constants.LOADBALANCE_KEY,RoundRobinLoadBalance.NAME));
    registryDirectory2.notify(serviceUrls);
    invocation=new RpcInvocation();
    invocation.setMethodName("get");
    List invokers=registryDirectory2.list(invocation);
    Invoker invoker=(Invoker)invokers.get(0);
    URL url=invoker.getUrl();
    Assert.assertEquals(LeastActiveLoadBalance.NAME,url.getMethodParameter("get",Constants.LOADBALANCE_KEY));
  }
{
    Assert.assertEquals(null,registryDirectory2.getUrl().getParameter("mock"));
    serviceUrls.clear();
    serviceUrls.add(SERVICEURL.addParameter(Constants.MOCK_KEY,"true"));
    registryDirectory2.notify(serviceUrls);
    Assert.assertEquals("true",registryDirectory2.getUrl().getParameter("mock"));
  }
}
