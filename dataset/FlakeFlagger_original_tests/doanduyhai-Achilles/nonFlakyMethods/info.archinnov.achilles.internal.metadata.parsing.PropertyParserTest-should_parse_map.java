@SuppressWarnings({"rawtypes","unchecked"}) @Test public void should_parse_map() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private Map<Integer,String> preferences;
    public Map<Integer,String> getPreferences(){
      return preferences;
    }
    public void setPreferences(    Map<Integer,String> preferences){
      this.preferences=preferences;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("preferences"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.getPropertyName()).isEqualTo("preferences");
  assertThat(meta.<String>getValueClass()).isEqualTo(String.class);
  assertThat(meta.type()).isEqualTo(PropertyType.MAP);
  assertThat(meta.<Integer>getKeyClass()).isEqualTo(Integer.class);
  assertThat(meta.getGetter().getName()).isEqualTo("getPreferences");
  assertThat((Class<Map>)meta.getGetter().getReturnType()).isEqualTo(Map.class);
  assertThat(meta.getSetter().getName()).isEqualTo("setPreferences");
  assertThat((Class<Map>)meta.getSetter().getParameterTypes()[0]).isEqualTo(Map.class);
}
