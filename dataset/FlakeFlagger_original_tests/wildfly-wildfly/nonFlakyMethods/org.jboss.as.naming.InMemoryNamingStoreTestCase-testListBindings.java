@Test public void testListBindings() throws Exception {
  final Name name=new CompositeName("test");
  final Object object=new Object();
  nameStore.bind(name,object);
  final Name nameTwo=new CompositeName("testTwo");
  final Object objectTwo=new Object();
  nameStore.bind(nameTwo,objectTwo);
  final Name nameThree=new CompositeName("testThree");
  final Object objectThree=new Object();
  nameStore.bind(nameThree,objectThree);
  nameStore.bind(new CompositeName("testContext/test"),"test");
  final List<Binding> results=nameStore.listBindings(new CompositeName());
  assertEquals(4,results.size());
  final Set<String> expected=new HashSet<String>(Arrays.asList("test","testTwo","testThree","testContext"));
  for (  Binding result : results) {
    final String resultName=result.getName();
    if ("test".equals(resultName)) {
      assertEquals(Object.class.getName(),result.getClassName());
      assertEquals(object,result.getObject());
    }
 else     if ("testTwo".equals(resultName)) {
      assertEquals(Object.class.getName(),result.getClassName());
      assertEquals(objectTwo,result.getObject());
    }
 else     if ("testThree".equals(resultName)) {
      assertEquals(Object.class.getName(),result.getClassName());
      assertEquals(objectThree,result.getObject());
    }
 else     if ("testContext".equals(resultName)) {
      assertEquals(Context.class.getName(),result.getClassName());
    }
 else {
      fail("Unknown result name: " + resultName);
    }
    expected.remove(resultName);
  }
  assertTrue("Not all expected results were returned",expected.isEmpty());
}
