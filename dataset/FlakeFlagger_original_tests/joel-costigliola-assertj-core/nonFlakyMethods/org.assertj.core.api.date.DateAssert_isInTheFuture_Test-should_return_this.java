@Test public void should_return_this(){
  DateAssert returned=assertions.isInTheFuture();
  assertSame(assertions,returned);
}
