@Test public void should_return_this(){
  DateAssert returned=assertions.isToday();
  assertSame(assertions,returned);
}
