@Test public void should_have_internal_effects(){
  invoke_api_method();
  verify_internal_effects();
}
