@Test public void testOninvokeMethod() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setOninvokeMethod("on-invoke-method");
  assertThat(method.getOninvokeMethod(),equalTo("on-invoke-method"));
  Map<Object,Object> attribute=new HashMap<Object,Object>();
  MethodConfig.appendAttributes(attribute,method);
  assertThat(attribute,hasEntry((Object)Constants.ON_INVOKE_METHOD_KEY,(Object)"on-invoke-method"));
  Map<String,String> parameters=new HashMap<String,String>();
  MethodConfig.appendParameters(parameters,method);
  assertThat(parameters.size(),is(0));
}
