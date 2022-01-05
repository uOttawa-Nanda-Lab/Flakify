@Test public void should_parse_counter_property_with_consistency_level() throws Exception {
@SuppressWarnings("unused") class Test {
    @Consistency(read=ONE,write=ALL) @Column private Counter counter;
    public Counter getCounter(){
      return counter;
    }
    public void setCounter(    Counter counter){
      this.counter=counter;
    }
  }
  entityContext=new EntityParsingContext(configContext,Test.class);
  entityContext.setCurrentConsistencyLevels(Pair.create(TWO,THREE));
  PropertyParsingContext context=entityContext.newPropertyContext(Test.class.getDeclaredField("counter"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.type()).isEqualTo(PropertyType.COUNTER);
  assertThat(meta.getReadConsistencyLevel()).isEqualTo(ONE);
  assertThat(meta.getWriteConsistencyLevel()).isEqualTo(ALL);
}
