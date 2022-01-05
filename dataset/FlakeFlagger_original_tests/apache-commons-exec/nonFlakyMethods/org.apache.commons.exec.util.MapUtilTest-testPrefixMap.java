/** 
 * Test prefixing of map
 */
public void testPrefixMap() throws Exception {
  HashMap procEnvironment=new HashMap();
  procEnvironment.put("JAVA_HOME","/usr/opt/java");
  Map result=MapUtils.prefix(procEnvironment,"env");
  assertTrue(procEnvironment.size() == result.size());
  assertEquals("/usr/opt/java",result.get("env.JAVA_HOME"));
}
