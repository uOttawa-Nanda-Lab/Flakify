@Test public void testArguments() throws Exception {
  ArgumentConfig argument=new ArgumentConfig();
  argument.setIndex(1);
  argument.setType("int");
  argument.setCallback(true);
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractServiceConfig.appendParameters(parameters,argument);
  assertThat(parameters,hasEntry("callback","true"));
  assertThat(parameters.size(),is(1));
}
