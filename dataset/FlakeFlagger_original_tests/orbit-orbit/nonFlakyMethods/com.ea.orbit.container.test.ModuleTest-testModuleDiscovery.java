@Test public void testModuleDiscovery(){
  final Module1 module=new Module1();
  final OrbitContainer container=new OrbitContainer();
  container.start();
  final Class2 c2=container.get(Class2.class);
  assertTrue(c2.started);
  assertSame(c2,container.get(Class2.class));
  assertNotSame(container.get(Class1.class),container.get(Class1.class));
}
