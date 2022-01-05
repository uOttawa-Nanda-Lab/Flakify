@Test public void should_parse_enum_property() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private PropertyType type;
    public PropertyType getType(){
      return type;
    }
    public void setType(    PropertyType type){
      this.type=type;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("type"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.<PropertyType>getValueClass()).isEqualTo(PropertyType.class);
}
