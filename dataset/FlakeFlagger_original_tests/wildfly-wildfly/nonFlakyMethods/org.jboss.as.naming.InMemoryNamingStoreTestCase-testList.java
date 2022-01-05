@Test public void testList() throws Exception {
  final Name name=new CompositeName("test");
  final Object object=new Object();
  nameStore.bind(name,object,Object.class);
  final Name nameTwo=new CompositeName("testTwo");
  final Object objectTwo=new Object();
  nameStore.bind(nameTwo,objectTwo,Object.class);
  final Name nameThree=new CompositeName("testThree");
  final Object objectThree=new Object();
  nameStore.bind(nameThree,objectThree,Object.class);
  nameStore.bind(new CompositeName("testContext/test"),"test");
  final List<NameClassPair> results=nameStore.list(new CompositeName());
  assertEquals(4,results.size());
  final Set<String> expected=new HashSet<String>(Arrays.asList("test","testTwo","testThree","testContext"));
  for (  NameClassPair result : results) {
    final String resultName=result.getName();
    if ("test".equals(resultName) || "testTwo".equals(resultName) || "testThree".equals(resultName)) {
      assertEquals(Object.class.getName(),result.getClassName());
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
