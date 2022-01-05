@Override @Test public void should_have_internal_effects(){
  thrown.expectNullPointerException("The charset should not be null");
  assertions.usingCharset((Charset)null);
}
