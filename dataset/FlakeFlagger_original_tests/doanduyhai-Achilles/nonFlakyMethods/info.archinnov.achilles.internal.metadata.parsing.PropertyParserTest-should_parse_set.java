@SuppressWarnings({"rawtypes","unchecked"}) @Test public void should_parse_set() throws Exception {
@SuppressWarnings("unused") class Test {
    @Column private Set<Long> followers;
    public Set<Long> getFollowers(){
      return followers;
    }
    public void setFollowers(    Set<Long> followers){
      this.followers=followers;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("followers"));
  PropertyMeta meta=parser.parse(context);
  assertThat(meta.getPropertyName()).isEqualTo("followers");
  assertThat(meta.<Long>getValueClass()).isEqualTo(Long.class);
  assertThat(meta.getGetter().getName()).isEqualTo("getFollowers");
  assertThat((Class<Set>)meta.getGetter().getReturnType()).isEqualTo(Set.class);
  assertThat(meta.getSetter().getName()).isEqualTo("setFollowers");
  assertThat((Class<Set>)meta.getSetter().getParameterTypes()[0]).isEqualTo(Set.class);
  assertThat(meta.type()).isEqualTo(PropertyType.SET);
}
