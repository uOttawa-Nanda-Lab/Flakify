@Test public void testInvocation(){
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
}
