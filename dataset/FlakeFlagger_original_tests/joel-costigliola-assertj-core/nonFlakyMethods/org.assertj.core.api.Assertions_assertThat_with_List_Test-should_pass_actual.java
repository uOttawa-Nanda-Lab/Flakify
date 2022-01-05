@Test public void should_pass_actual(){
  List<String> names=singletonList("Luke");
  AbstractListAssert<?,? extends List<? extends String>,String> assertions=Assertions.assertThat(names);
  assertSame(names,assertions.actual);
}
