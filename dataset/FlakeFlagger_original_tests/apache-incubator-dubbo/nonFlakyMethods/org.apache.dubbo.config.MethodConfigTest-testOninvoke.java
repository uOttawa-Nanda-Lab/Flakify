@Test public void testOninvoke() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setOninvoke("on-invoke-object");
  assertThat(method.getOninvoke(),equalTo((Object)"on-invoke-object"));
  Map<Object,Object> attribute=new HashMap<Object,Object>();
  MethodConfig.appendAttributes(attribute,method);
  assertThat(attribute,hasEntry((Object)Constants.ON_INVOKE_INSTANCE_KEY,(Object)"on-invoke-object"));
  Map<String,String> parameters=new HashMap<String,String>();
  MethodConfig.appendParameters(parameters,method);
  assertThat(parameters.size(),is(0));
}
