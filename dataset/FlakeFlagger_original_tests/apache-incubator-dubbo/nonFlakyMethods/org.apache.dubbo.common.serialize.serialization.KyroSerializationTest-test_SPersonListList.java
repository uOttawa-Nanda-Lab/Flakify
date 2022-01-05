@Test public void test_SPersonListList() throws Exception {
  List<List<SerializablePerson>> args=new ArrayList<List<SerializablePerson>>();
  List<SerializablePerson> sublist=new ArrayList<SerializablePerson>();
  sublist.add(new SerializablePerson());
  args.add(sublist);
  assertObject(args);
}
