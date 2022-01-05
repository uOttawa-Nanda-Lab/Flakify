@Override @Test public void should_have_internal_effects(){
  thrown.expectIllegalArgumentException("Charset:<'Klingon'> is not supported on this system");
  assertions.usingCharset("Klingon");
}
