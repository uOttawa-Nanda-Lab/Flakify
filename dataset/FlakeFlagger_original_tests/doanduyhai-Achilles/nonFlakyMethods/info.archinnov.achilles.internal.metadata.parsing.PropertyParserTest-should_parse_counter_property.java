@Test public void should_parse_counter_property() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private Counter counter;
    public Counter getCounter(){
      return counter;
    }
    public void setCounter(    Counter counter){
      this.counter=counter;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("counter"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.type()).isEqualTo(PropertyType.COUNTER);
  assertThat(meta.getCounterProperties()).isNotNull();
  assertThat(meta.getCounterProperties().getFqcn()).isEqualTo(Test.class.getCanonicalName());
  assertThat(context.getCounterMetas().get(0)).isSameAs(meta);
}
