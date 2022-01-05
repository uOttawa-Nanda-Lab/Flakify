@Test public void should_return_this(){
  DateAssert returned=assertions.isInThePast();
  assertSame(assertions,returned);
}
