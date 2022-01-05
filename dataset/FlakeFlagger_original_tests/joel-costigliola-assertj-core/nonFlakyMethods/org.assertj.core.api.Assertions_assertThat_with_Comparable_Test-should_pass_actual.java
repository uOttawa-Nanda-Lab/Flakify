@Test public void should_pass_actual(){
  SomeComparable comparable=new SomeComparable();
  AbstractComparableAssert<?,SomeComparable> assertions=Assertions.assertThat(comparable);
  assertSame(comparable,assertions.actual);
}
