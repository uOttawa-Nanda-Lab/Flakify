@Test public void helloTest(){
  OrbitContainer container=new OrbitContainer();
  container.add(HelloWorld.class);
  container.start();
  final HelloWorld hello=container.get(HelloWorld.class);
  assertEquals("hello",hello.sayHello());
  container.stop();
}
