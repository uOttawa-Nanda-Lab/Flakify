@Test public void assertGetCause(){
  IOException cause=new IOException();
  assertThat(new HostException(cause).getCause(),Is.<Throwable>is(cause));
}
