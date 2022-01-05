@Test public void should_return_this(){
  DateAssert returned=assertionInvocationWithOneIntArg();
  assertSame(assertions,returned);
}
