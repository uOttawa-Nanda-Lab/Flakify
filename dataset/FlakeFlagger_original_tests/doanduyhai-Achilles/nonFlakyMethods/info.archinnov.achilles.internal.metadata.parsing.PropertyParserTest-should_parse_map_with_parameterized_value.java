@SuppressWarnings({"rawtypes","unchecked"}) @Test public void should_parse_map_with_parameterized_value() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private Map<Integer,List<String>> map;
    public Map<Integer,List<String>> getMap(){
      return map;
    }
    public void setMap(    Map<Integer,List<String>> map){
      this.map=map;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("map"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.getPropertyName()).isEqualTo("map");
  assertThat((Class)meta.getValueClass()).isEqualTo(List.class);
  assertThat(meta.type()).isEqualTo(PropertyType.MAP);
  assertThat(meta.<Integer>getKeyClass()).isEqualTo(Integer.class);
  assertThat(meta.getGetter().getName()).isEqualTo("getMap");
  assertThat((Class<Map>)meta.getGetter().getReturnType()).isEqualTo(Map.class);
  assertThat(meta.getSetter().getName()).isEqualTo("setMap");
  assertThat((Class<Map>)meta.getSetter().getParameterTypes()[0]).isEqualTo(Map.class);
}
