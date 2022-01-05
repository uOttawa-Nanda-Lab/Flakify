@Test public void testArguments() throws Exception {
  MethodConfig method=new MethodConfig();
  ArgumentConfig argument=new ArgumentConfig();
  method.setArguments(Collections.singletonList(argument));
  assertThat(method.getArguments(),contains(argument));
  assertThat(method.getArguments(),Matchers.<org.apache.dubbo.config.ArgumentConfig>hasSize(1));
}
