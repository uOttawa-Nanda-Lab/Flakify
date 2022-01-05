@Test public void testOnthrowMethod() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setOnthrowMethod("on-throw-method");
  assertThat(method.getOnthrowMethod(),equalTo("on-throw-method"));
  Map<Object,Object> attribute=new HashMap<Object,Object>();
  MethodConfig.appendAttributes(attribute,method);
  assertThat(attribute,hasEntry((Object)Constants.ON_THROW_METHOD_KEY,(Object)"on-throw-method"));
  Map<String,String> parameters=new HashMap<String,String>();
  MethodConfig.appendParameters(parameters,method);
  assertThat(parameters.size(),is(0));
}
