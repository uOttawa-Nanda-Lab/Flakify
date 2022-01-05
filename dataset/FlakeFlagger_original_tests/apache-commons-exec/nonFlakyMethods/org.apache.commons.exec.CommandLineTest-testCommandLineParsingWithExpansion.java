/** 
 * Test expanding the command line based on a user-supplied map.
 */
public void testCommandLineParsingWithExpansion(){
  CommandLine cmdl=null;
  HashMap substitutionMap=new HashMap();
  substitutionMap.put("JAVA_HOME","/usr/local/java");
  substitutionMap.put("appMainClass","foo.bar.Main");
  HashMap incompleteMap=new HashMap();
  incompleteMap.put("JAVA_HOME","/usr/local/java");
  cmdl=CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}");
  assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") == 0);
  assertEquals(new String[]{"${appMainClass}"},cmdl.getArguments());
  cmdl=CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}",new HashMap());
  assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") == 0);
  assertEquals(new String[]{"${appMainClass}"},cmdl.getArguments());
  cmdl=CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}",substitutionMap);
  assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") < 0);
  assertTrue(cmdl.getExecutable().indexOf("local") > 0);
  assertEquals(new String[]{"foo.bar.Main"},cmdl.getArguments());
  cmdl=CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}",incompleteMap);
  assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") < 0);
  assertTrue(cmdl.getExecutable().indexOf("local") > 0);
  assertEquals(new String[]{"${appMainClass}"},cmdl.getArguments());
}
