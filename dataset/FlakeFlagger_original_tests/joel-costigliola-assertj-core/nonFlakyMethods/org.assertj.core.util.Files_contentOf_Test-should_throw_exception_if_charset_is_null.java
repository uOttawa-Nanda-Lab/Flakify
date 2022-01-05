@Test public void should_throw_exception_if_charset_is_null(){
  Charset charset=null;
  thrown.expect(NullPointerException.class);
  Files.contentOf(new File("test"),charset);
}
