@Test public void testAuthRedis(){
  Invoker<IDemoService> refer=protocol.refer(IDemoService.class,registryUrl.addParameter("max.idle",10).addParameter("max.active",20));
  IDemoService demoService=this.proxy.getProxy(refer);
  String value=demoService.get("key");
  assertThat(value,is(nullValue()));
  demoService.set("key","newValue");
  value=demoService.get("key");
  assertThat(value,is("newValue"));
  demoService.delete("key");
  value=demoService.get("key");
  assertThat(value,is(nullValue()));
  refer.destroy();
  String password="123456";
  int database=1;
  this.registryUrl=this.registryUrl.setPassword(password).addParameter("db.index",database);
  refer=protocol.refer(IDemoService.class,registryUrl.addParameter("max.idle",10).addParameter("max.active",20));
  demoService=this.proxy.getProxy(refer);
  demoService.set("key","newValue");
  value=demoService.get("key");
  assertThat(value,is("newValue"));
  JedisPool pool=new JedisPool(new GenericObjectPoolConfig(),"localhost",registryUrl.getPort(),2000,password,database,(String)null);
  Jedis jedis=null;
  try {
    jedis=pool.getResource();
    byte[] valueByte=jedis.get("key".getBytes());
    Serialization serialization=ExtensionLoader.getExtensionLoader(Serialization.class).getExtension(this.registryUrl.getParameter(Constants.SERIALIZATION_KEY,"java"));
    ObjectInput oin=serialization.deserialize(this.registryUrl,new ByteArrayInputStream(valueByte));
    String actual=(String)oin.readObject();
    assertThat(value,is(actual));
  }
 catch (  Exception e) {
    Assert.fail("jedis gets the result comparison is error!");
  }
 finally {
    if (jedis != null) {
      jedis.close();
    }
    pool.destroy();
  }
  demoService.delete("key");
  value=demoService.get("key");
  assertThat(value,is(nullValue()));
  refer.destroy();
}
