@Test public void should_parse_primary_key() throws Exception {
@SuppressWarnings("unused") class Test {
    @Id private Long id;
    public Long getId(){
      return id;
    }
    public void setId(    Long id){
      this.id=id;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("id"));
  context.setPrimaryKey(true);
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.getPropertyName()).isEqualTo("id");
  assertThat(meta.<Long>getValueClass()).isEqualTo(Long.class);
  assertThat(context.getPropertyMetas()).hasSize(1);
}
