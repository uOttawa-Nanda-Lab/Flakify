@Test public void should_return_this(){
  ConcreteAssert descriptable=assertions.describedAs(description);
  assertSame(assertions,descriptable);
}
