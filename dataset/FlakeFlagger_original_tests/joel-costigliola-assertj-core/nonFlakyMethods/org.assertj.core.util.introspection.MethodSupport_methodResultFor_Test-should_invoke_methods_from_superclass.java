@Test public void should_invoke_methods_from_superclass() throws Exception {
  Object result=MethodSupport.methodResultFor(batman,"getName");
  assertThat(result).isEqualTo("Batman");
}
