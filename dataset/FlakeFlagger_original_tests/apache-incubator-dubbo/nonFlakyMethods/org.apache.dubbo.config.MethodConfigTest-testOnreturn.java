@Test public void testOnreturn() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setOnreturn("on-return-object");
  assertThat(method.getOnreturn(),equalTo((Object)"on-return-object"));
  Map<Object,Object> attribute=new HashMap<Object,Object>();
  MethodConfig.appendAttributes(attribute,method);
  assertThat(attribute,hasEntry((Object)Constants.ON_RETURN_INSTANCE_KEY,(Object)"on-return-object"));
  Map<String,String> parameters=new HashMap<String,String>();
  MethodConfig.appendParameters(parameters,method);
  assertThat(parameters.size(),is(0));
}
