@Test public void should_parse_simple_property_and_override_name() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column(name="firstname") private String name;
    public String getName(){
      return name;
    }
    public void setName(    String name){
      this.name=name;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("name"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.getPropertyName()).isEqualTo("firstname");
}
