public void testWriteJson() throws Exception {
  StringWriter w=new StringWriter();
  JSONWriter writer=new JSONWriter(w);
  writer.valueNull();
  assertEquals(w.getBuffer().toString(),"null");
  w.getBuffer().setLength(0);
  writer.arrayBegin().valueNull().valueBoolean(false).valueInt(16).arrayEnd();
  assertEquals(w.getBuffer().toString(),"[null,false,16]");
  w.getBuffer().setLength(0);
  writer.objectBegin().objectItem("type").valueString("org.apache.dubbo.TestService").objectItem("version").valueString("1.1.0").objectEnd();
  assertEquals(w.getBuffer().toString(),"{\"type\":\"org.apache.dubbo.TestService\",\"version\":\"1.1.0\"}");
  w.getBuffer().setLength(0);
  writer.objectBegin();
  writer.objectItem("name").objectItem("displayName");
  writer.objectItem("emptyList").arrayBegin().arrayEnd();
  writer.objectItem("list").arrayBegin().valueNull().valueBoolean(false).valueInt(16).valueString("stri'''ng").arrayEnd();
  writer.objectItem("service").objectBegin().objectItem("type").valueString("org.apache.dubbo.TestService").objectItem("version").valueString("1.1.0").objectEnd();
  writer.objectEnd();
}
