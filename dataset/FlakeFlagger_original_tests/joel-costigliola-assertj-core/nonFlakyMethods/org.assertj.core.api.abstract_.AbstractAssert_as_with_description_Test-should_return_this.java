@Test public void should_return_this(){
  ConcreteAssert descriptable=assertions.as(d);
  assertSame(assertions,descriptable);
}
