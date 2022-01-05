/** 
 * The user configures -default, which will delete all the default parameters
 */
@Test public void testMergeValuesDelete(){
  List<String> merged=ConfigUtils.mergeValues(ThreadPool.class,"-fixed,aaa",asList("fixed","default.limited","cached"));
  assertEquals(asList("cached","aaa"),merged);
}
