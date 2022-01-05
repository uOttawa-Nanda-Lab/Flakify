@Test public void should_exception_when_setting_id() throws Throwable {
  exception.expect(IllegalAccessException.class);
  exception.expectMessage("Cannot change primary key value for existing entity ");
  interceptor.intercept(null,idMeta.getSetter(),args,null);
}
