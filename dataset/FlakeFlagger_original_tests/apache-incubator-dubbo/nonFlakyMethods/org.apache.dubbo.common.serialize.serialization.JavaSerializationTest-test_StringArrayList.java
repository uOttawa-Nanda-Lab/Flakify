@Test public void test_StringArrayList() throws Exception {
  List<String> args=new ArrayList<String>(Arrays.asList(new String[]{"1","b"}));
  assertObject(args);
}
