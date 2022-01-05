@Test public void should_invoke_methods_without_arguments() throws Exception {
  Object result=MethodSupport.methodResultFor(batman,"archenemy");
  assertThat(result).isEqualTo(joker);
}
