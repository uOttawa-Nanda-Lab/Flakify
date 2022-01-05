@Test public void should_parse_index() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column @Index private String firstname;
    public String getFirstname(){
      return firstname;
    }
    public void setFirstname(    String firstname){
      this.firstname=firstname;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("firstname"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.isIndexed()).isTrue();
}
