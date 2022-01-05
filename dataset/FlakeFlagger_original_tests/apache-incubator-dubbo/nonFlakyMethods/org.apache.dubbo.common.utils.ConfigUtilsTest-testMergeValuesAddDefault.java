@Test public void testMergeValuesAddDefault(){
  List<String> merged=ConfigUtils.mergeValues(ThreadPool.class,"aaa,bbb,default,zzz",asList("fixed","default.limited","cached"));
  assertEquals(asList("aaa","bbb","fixed","cached","zzz"),merged);
}
