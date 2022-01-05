@Test public void should_parse_allowed_type_property() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private UUID uuid;
    public UUID getUuid(){
      return uuid;
    }
    public void setUuid(    UUID uuid){
      this.uuid=uuid;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("uuid"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.<UUID>getValueClass()).isEqualTo(UUID.class);
}
