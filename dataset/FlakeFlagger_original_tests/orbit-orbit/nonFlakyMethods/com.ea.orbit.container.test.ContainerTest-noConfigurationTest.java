@Test public void noConfigurationTest(){
  OrbitContainer container=new OrbitContainer();
  container.add(HelloWorld.class);
  container.add(HelloHandler.class);
  container.start();
  final HelloHandler helloHandler=container.get(HelloHandler.class);
  assertEquals("blah.hello",helloHandler.prefixedHello());
  container.stop();
}
