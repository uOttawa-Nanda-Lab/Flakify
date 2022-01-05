@Test public void should_throw_exception_if_charset_name_does_not_exist(){
  thrown.expect(IllegalArgumentException.class);
  linesOf(new File("test"),"Klingon");
}
