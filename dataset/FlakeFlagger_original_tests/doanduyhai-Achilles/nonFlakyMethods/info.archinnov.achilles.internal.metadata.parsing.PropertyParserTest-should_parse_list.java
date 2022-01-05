@SuppressWarnings({"rawtypes","unchecked"}) @Test public void should_parse_list() throws Exception {
@SuppressWarnings("unused") class Test {
    @EmptyCollectionIfNull @Column private List<String> friends;
    @NotNull @Column private List<String> mates;
    public List<String> getFriends(){
      return friends;
    }
    public void setFriends(    List<String> friends){
      this.friends=friends;
    }
    public List<String> getMates(){
      return mates;
    }
    public void setMates(    List<String> mates){
      this.mates=mates;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("friends"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.getPropertyName()).isEqualTo("friends");
  assertThat(meta.<String>getValueClass()).isEqualTo(String.class);
  assertThat(meta.getGetter().getName()).isEqualTo("getFriends");
  assertThat((Class<List>)meta.getGetter().getReturnType()).isEqualTo(List.class);
  assertThat(meta.getSetter().getName()).isEqualTo("setFriends");
  assertThat((Class<List>)meta.getSetter().getParameterTypes()[0]).isEqualTo(List.class);
  assertThat(meta.type()).isEqualTo(PropertyType.LIST);
  assertThat(meta.nullValueForCollectionAndMap()).isNotNull().isInstanceOf(List.class);
  PropertyParsingContext context2=newContext(Test.class,Test.class.getDeclaredField("mates"));
  PropertyMeta meta2=parser.parse(context2);
  assertThat(meta2.type()).isEqualTo(PropertyType.LIST);
  assertThat(meta2.nullValueForCollectionAndMap()).isNotNull().isInstanceOf(List.class);
}
