@Test public void should_parse_simple_property_of_time_uuid_type() throws Exception {
@SuppressWarnings("unused") class Test {
    @TimeUUID @Column private UUID date;
    public UUID getDate(){
      return date;
    }
    public void setDate(    UUID date){
      this.date=date;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("date"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.isTimeUUID()).isTrue();
}
