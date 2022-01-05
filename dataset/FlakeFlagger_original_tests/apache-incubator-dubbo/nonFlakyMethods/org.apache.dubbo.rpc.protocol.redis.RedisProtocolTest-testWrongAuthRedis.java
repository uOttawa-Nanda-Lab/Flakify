@Test public void testWrongAuthRedis(){
  String password="1234567";
  this.registryUrl=this.registryUrl.setPassword(password);
  Invoker<IDemoService> refer=protocol.refer(IDemoService.class,registryUrl.addParameter("max.idle",10).addParameter("max.active",20));
  IDemoService demoService=this.proxy.getProxy(refer);
  try {
    String value=demoService.get("key");
    assertThat(value,is(nullValue()));
  }
 catch (  RpcException e) {
    if (e.getCause() instanceof JedisConnectionException && e.getCause().getCause() instanceof JedisDataException) {
      Assert.assertEquals("ERR invalid password",e.getCause().getCause().getMessage());
    }
 else {
      Assert.fail("no invalid password exception!");
    }
  }
  refer.destroy();
}
