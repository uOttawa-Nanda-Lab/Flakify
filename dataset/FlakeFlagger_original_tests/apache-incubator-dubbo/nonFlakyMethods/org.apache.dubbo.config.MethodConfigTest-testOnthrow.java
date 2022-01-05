@Test public void testOnthrow() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setOnthrow("on-throw-object");
  assertThat(method.getOnthrow(),equalTo((Object)"on-throw-object"));
  Map<Object,Object> attribute=new HashMap<Object,Object>();
  MethodConfig.appendAttributes(attribute,method);
  assertThat(attribute,hasEntry((Object)Constants.ON_THROW_INSTANCE_KEY,(Object)"on-throw-object"));
  Map<String,String> parameters=new HashMap<String,String>();
  MethodConfig.appendParameters(parameters,method);
  assertThat(parameters.size(),is(0));
}
