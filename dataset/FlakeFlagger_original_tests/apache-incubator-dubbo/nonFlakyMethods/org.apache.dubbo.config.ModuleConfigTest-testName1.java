@Test(expected=IllegalStateException.class) public void testName1() throws Exception {
  ModuleConfig module=new ModuleConfig();
  Map<String,String> parameters=new HashMap<String,String>();
  ModuleConfig.appendParameters(parameters,module);
}
