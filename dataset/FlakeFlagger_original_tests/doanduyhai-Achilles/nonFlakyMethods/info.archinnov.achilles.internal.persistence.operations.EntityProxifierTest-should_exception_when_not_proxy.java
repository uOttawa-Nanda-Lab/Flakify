@Test public void should_exception_when_not_proxy() throws Exception {
  CompleteBean proxy=new CompleteBean();
  exception.expect(IllegalStateException.class);
  exception.expectMessage("The entity '" + proxy + "' is not in 'managed' state.");
  proxifier.ensureProxy(proxy);
}
