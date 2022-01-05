/** 
 * When the first arg of a method is String or Enum, Registry server can do parameter-value-based routing.
 */
@Test public void testParmeterRoute(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List<URL> serviceUrls=new ArrayList<URL>();
  serviceUrls.add(SERVICEURL.addParameter("methods","getXXX1.napoli"));
  serviceUrls.add(SERVICEURL2.addParameter("methods","getXXX1.MORGAN,getXXX2"));
  serviceUrls.add(SERVICEURL3.addParameter("methods","getXXX1.morgan,getXXX2,getXXX3"));
  registryDirectory.notify(serviceUrls);
  invocation=new RpcInvocation(Constants.$INVOKE,new Class[]{String.class,String[].class,Object[].class},new Object[]{"getXXX1",new String[]{"Enum"},new Object[]{Param.MORGAN}});
  List invokers=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers.size());
}
