/** 
 * Test copying of map
 */
public void testCopyMap() throws Exception {
  HashMap procEnvironment=new HashMap();
  procEnvironment.put("JAVA_HOME","/usr/opt/java");
  Map result=MapUtils.copy(procEnvironment);
  assertTrue(result.size() == 1);
  assertTrue(procEnvironment.size() == 1);
  assertEquals("/usr/opt/java",result.get("JAVA_HOME"));
  result.remove("JAVA_HOME");
  assertTrue(result.size() == 0);
  assertTrue(procEnvironment.size() == 1);
}
