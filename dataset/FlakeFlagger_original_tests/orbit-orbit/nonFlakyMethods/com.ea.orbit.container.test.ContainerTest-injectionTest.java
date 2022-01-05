@Test public void injectionTest(){
  OrbitContainer container=new OrbitContainer();
  container.add(HelloWorld.class);
  container.add(HelloHandler.class);
  container.start();
  final HelloHandler helloHandler=container.get(HelloHandler.class);
  assertEquals("hello",helloHandler.sayHello());
  container.stop();
}
