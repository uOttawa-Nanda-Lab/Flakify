/** 
 * Test merging of maps
 */
public void testMergeMap() throws Exception {
  Map procEnvironment=EnvironmentUtil.getProcEnvironment();
  HashMap applicationEnvironment=new HashMap();
  applicationEnvironment.put("appMainClass","foo.bar.Main");
  Map result=MapUtils.merge(procEnvironment,applicationEnvironment);
  assertTrue((procEnvironment.size() + applicationEnvironment.size()) == result.size());
  assertEquals("foo.bar.Main",result.get("appMainClass"));
}
