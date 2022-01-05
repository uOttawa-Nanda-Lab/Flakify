@Test public void test_StringSPersonMap() throws Exception {
  Map<String,SerializablePerson> args=new HashMap<String,SerializablePerson>();
  args.put("1",new SerializablePerson());
  assertObject(args);
}
