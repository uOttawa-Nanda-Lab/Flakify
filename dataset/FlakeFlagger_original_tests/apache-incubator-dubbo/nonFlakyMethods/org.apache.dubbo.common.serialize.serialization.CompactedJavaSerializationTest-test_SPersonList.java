@Test public void test_SPersonList() throws Exception {
  List<SerializablePerson> args=new ArrayList<SerializablePerson>();
  args.add(new SerializablePerson());
  assertObject(args);
}
