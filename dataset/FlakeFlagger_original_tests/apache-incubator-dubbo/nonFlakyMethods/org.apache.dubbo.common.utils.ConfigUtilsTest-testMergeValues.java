@Test public void testMergeValues(){
  List<String> merged=ConfigUtils.mergeValues(ThreadPool.class,"aaa,bbb,default.custom",asList("fixed","default.limited","cached"));
  assertEquals(asList("fixed","cached","aaa","bbb","default.custom"),merged);
}
