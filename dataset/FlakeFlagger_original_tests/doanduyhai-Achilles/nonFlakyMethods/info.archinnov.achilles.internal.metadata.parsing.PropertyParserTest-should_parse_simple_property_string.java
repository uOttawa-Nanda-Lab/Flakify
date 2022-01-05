@SuppressWarnings("unchecked") @Test public void should_parse_simple_property_string() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private String name;
    public String getName(){
      return name;
    }
    public void setName(    String name){
      this.name=name;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("name"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.getPropertyName()).isEqualTo("name");
  assertThat(meta.<String>getValueClass()).isEqualTo(String.class);
  assertThat(meta.getGetter().getName()).isEqualTo("getName");
  assertThat((Class<String>)meta.getGetter().getReturnType()).isEqualTo(String.class);
  assertThat(meta.getSetter().getName()).isEqualTo("setName");
  assertThat((Class<String>)meta.getSetter().getParameterTypes()[0]).isEqualTo(String.class);
  assertThat(meta.type()).isEqualTo(PropertyType.SIMPLE);
  assertThat(context.getPropertyMetas().get("name")).isSameAs(meta);
}
