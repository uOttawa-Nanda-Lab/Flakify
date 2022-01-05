@Test public void should_parse_primitive_property() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private boolean active;
    public boolean isActive(){
      return active;
    }
    public void setActive(    boolean active){
      this.active=active;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("active"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.<Boolean>getValueClass()).isEqualTo(boolean.class);
}
