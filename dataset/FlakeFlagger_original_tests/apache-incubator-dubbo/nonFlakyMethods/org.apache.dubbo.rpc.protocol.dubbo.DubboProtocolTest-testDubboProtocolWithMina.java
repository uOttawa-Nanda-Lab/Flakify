@Test public void testDubboProtocolWithMina() throws Exception {
  DemoService service=new DemoServiceImpl();
  protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName()).addParameter(Constants.SERVER_KEY,"mina")));
  service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName()).addParameter(Constants.CLIENT_KEY,"mina")));
  for (int i=0; i < 10; i++) {
    assertEquals(service.enumlength(new Type[]{}),Type.Lower);
    assertEquals(service.getSize(null),-1);
    assertEquals(service.getSize(new String[]{"","",""}),3);
  }
  Map<String,String> map=new HashMap<String,String>();
  map.put("aa","bb");
  for (int i=0; i < 10; i++) {
    Set<String> set=service.keys(map);
    assertEquals(set.size(),1);
    assertEquals(set.iterator().next(),"aa");
    service.invoke("dubbo://127.0.0.1:9010/" + DemoService.class.getName() + "","invoke");
  }
  service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName() + "?client=mina")));
  StringBuffer buf=new StringBuffer();
  for (int i=0; i < 1024 * 32 + 32; i++)   buf.append('A');
  System.out.println(service.stringLength(buf.toString()));
  EchoService echo=proxy.getProxy(protocol.refer(EchoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName() + "?client=mina")));
  for (int i=0; i < 10; i++) {
    assertEquals(echo.$echo(buf.toString()),buf.toString());
    assertEquals(echo.$echo("test"),"test");
    assertEquals(echo.$echo("abcdefg"),"abcdefg");
    assertEquals(echo.$echo(1234),1234);
  }
}
