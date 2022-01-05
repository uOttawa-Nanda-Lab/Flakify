@Test public void testOnreturnMethod() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setOnreturnMethod("on-return-method");
  assertThat(method.getOnreturnMethod(),equalTo("on-return-method"));
  Map<Object,Object> attribute=new HashMap<Object,Object>();
  MethodConfig.appendAttributes(attribute,method);
  assertThat(attribute,hasEntry((Object)Constants.ON_RETURN_METHOD_KEY,(Object)"on-return-method"));
  Map<String,String> parameters=new HashMap<String,String>();
  MethodConfig.appendParameters(parameters,method);
  assertThat(parameters.size(),is(0));
}
