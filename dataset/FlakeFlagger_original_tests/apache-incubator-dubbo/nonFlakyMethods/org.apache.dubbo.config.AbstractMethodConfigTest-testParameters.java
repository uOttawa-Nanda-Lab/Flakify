@Test public void testParameters() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put("key","value");
  methodConfig.setParameters(parameters);
  assertThat(methodConfig.getParameters(),sameInstance(parameters));
}
