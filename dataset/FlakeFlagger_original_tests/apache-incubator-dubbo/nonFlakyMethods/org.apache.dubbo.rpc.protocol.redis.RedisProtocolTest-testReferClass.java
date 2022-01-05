@Test public void testReferClass(){
  Invoker<IDemoService> refer=protocol.refer(IDemoService.class,registryUrl);
  Class<IDemoService> serviceClass=refer.getInterface();
  assertThat(serviceClass.getName(),is("org.apache.dubbo.rpc.protocol.redis.IDemoService"));
}
