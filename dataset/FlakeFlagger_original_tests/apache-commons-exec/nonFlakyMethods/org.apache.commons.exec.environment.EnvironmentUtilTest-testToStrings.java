public void testToStrings() throws IOException {
  Map env=new HashMap();
  env.put("foo2","bar2");
  env.put("foo","bar");
  String[] envStrings=EnvironmentUtil.toStrings(env);
  String[] expected=new String[]{"foo=bar","foo2=bar2"};
  TestUtil.assertEquals(expected,envStrings,false);
}
