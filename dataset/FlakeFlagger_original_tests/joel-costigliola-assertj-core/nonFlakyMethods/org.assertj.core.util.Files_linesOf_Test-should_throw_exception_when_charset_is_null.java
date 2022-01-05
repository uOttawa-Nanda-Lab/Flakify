@Test public void should_throw_exception_when_charset_is_null(){
  Charset charset=null;
  thrown.expect(NullPointerException.class);
  linesOf(SAMPLE_UNIX_FILE,charset);
}
