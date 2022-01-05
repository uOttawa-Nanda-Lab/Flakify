@Test public void should_return_rows_for_native_query() throws Exception {
  CompleteBean entity1=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").version(CounterBuilder.incr(15L)).buid();
  CompleteBean entity2=builder().randomId().name("John DOO").age(35L).addFriends("qux","twix").addFollowers("Isaac","Lara").addPreference(1,"US").addPreference(2,"NewYork").version(CounterBuilder.incr(17L)).buid();
  manager.persist(entity1);
  manager.persist(entity2);
  String nativeQuery="SELECT name,age_in_years,friends,followers,preferences FROM CompleteBean WHERE id IN(" + entity1.getId() + ","+ entity2.getId()+ ")";
  List<TypedMap> actual=manager.nativeQuery(nativeQuery).get();
  assertThat(actual).hasSize(2);
  TypedMap row1=actual.get(0);
  TypedMap row2=actual.get(1);
  assertThat(row1.get("name")).isEqualTo("DuyHai");
  assertThat(row1.get("age_in_years")).isEqualTo(35L);
  assertThat(row1.<List<String>>getTyped("friends")).containsExactly("foo","bar");
  assertThat(row1.<Set<String>>getTyped("followers")).contains("George","Paul");
  Map<Integer,String> preferences1=row1.getTyped("preferences");
  assertThat(preferences1.get(1)).isEqualTo("FR");
  assertThat(preferences1.get(2)).isEqualTo("Paris");
  assertThat(preferences1.get(3)).isEqualTo("75014");
  assertThat(row2.get("name")).isEqualTo("John DOO");
  assertThat(row2.get("age_in_years")).isEqualTo(35L);
  assertThat(row2.<List<String>>getTyped("friends")).containsExactly("qux","twix");
  assertThat(row2.<Set<String>>getTyped("followers")).contains("Isaac","Lara");
  Map<Integer,String> preferences2=row2.getTyped("preferences");
  assertThat(preferences2.get(1)).isEqualTo("US");
  assertThat(preferences2.get(2)).isEqualTo("NewYork");
}
