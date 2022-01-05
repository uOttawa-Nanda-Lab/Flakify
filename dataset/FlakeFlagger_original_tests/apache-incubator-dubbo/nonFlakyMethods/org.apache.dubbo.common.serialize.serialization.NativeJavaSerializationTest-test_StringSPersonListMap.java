@Test public void test_StringSPersonListMap() throws Exception {
  Map<String,List<SerializablePerson>> args=new HashMap<String,List<SerializablePerson>>();
  List<SerializablePerson> sublist=new ArrayList<SerializablePerson>();
  sublist.add(new SerializablePerson());
  args.put("1",sublist);
  assertObject(args);
}
