@Test public void should_return_this(){
  DateAssert returned=assertionInvocationWithDateArg();
  assertSame(assertions,returned);
}
