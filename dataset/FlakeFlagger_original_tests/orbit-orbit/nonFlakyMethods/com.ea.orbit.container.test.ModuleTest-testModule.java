@Test public void testModule(){
  final Module1 module=new Module1();
  assertNotNull(module.getClasses());
  assertEquals(2,module.getClasses().size());
  assertTrue(module.getClasses().contains(Class1.class));
  assertTrue(module.getClasses().contains(Class2.class));
}
