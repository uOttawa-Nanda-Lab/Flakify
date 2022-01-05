@Test public void should_exception_when_counter_consistency_is_any_for_read() throws Exception {
@SuppressWarnings("unused") class Test {
    @Consistency(read=ANY,write=ALL) @Column private Counter counter;
    public Counter getCounter(){
      return counter;
    }
    public void setCounter(    Counter counter){
      this.counter=counter;
    }
  }
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("Counter field 'counter' of entity 'null' cannot have ANY as read/write consistency level. All consistency levels except ANY are allowed");
  entityContext=new EntityParsingContext(configContext,Test.class);
  entityContext.setCurrentConsistencyLevels(Pair.create(TWO,THREE));
  PropertyParsingContext context=entityContext.newPropertyContext(Test.class.getDeclaredField("counter"));
  parser.parse(context);
}
