@Test public void testMergeValuesDeleteDefault(){
  List<String> merged=ConfigUtils.mergeValues(ThreadPool.class,"-default",asList("fixed","default.limited","cached"));
  assertEquals(asList(),merged);
}
