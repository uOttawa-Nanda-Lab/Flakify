@Test public void test() throws IOException {
  final ClassPath cp=ClassPath.get();
  assertNotNull(cp.getAllResources());
  assertTrue(cp.getAllResources().size() > 0);
  final long count=cp.getAllResources().stream().filter(r -> r.getResourceName().equals(ClassPathTest.class.getName().replace(".","/") + ".class")).count();
  assertEquals(1,count);
}
