@Test public void configurationTest(){
  OrbitContainer container=new OrbitContainer();
  container.setProperties(Collections.singletonMap("hello.prefix","something"));
  container.add(HelloWorld.class);
  container.add(HelloHandler.class);
  container.start();
  final HelloHandler helloHandler=container.get(HelloHandler.class);
  assertEquals("something.hello",helloHandler.prefixedHello());
  container.stop();
}
