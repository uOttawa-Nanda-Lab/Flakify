@Test public void testMergeValuesDeleteDefault_2(){
  List<String> merged=ConfigUtils.mergeValues(ThreadPool.class,"-default,aaa",asList("fixed","default.limited","cached"));
  assertEquals(asList("aaa"),merged);
}
