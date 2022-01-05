@Test public void test_Map_List_pojo() throws Exception {
  Map<String,List<Object>> map=new HashMap<String,List<Object>>();
  List<Object> list=new ArrayList<Object>();
  list.add(new Person());
  list.add(new SerializablePerson());
  map.put("k",list);
  Object generalize=PojoUtils.generalize(map);
  Object realize=PojoUtils.realize(generalize,Map.class);
  assertEquals(map,realize);
}
