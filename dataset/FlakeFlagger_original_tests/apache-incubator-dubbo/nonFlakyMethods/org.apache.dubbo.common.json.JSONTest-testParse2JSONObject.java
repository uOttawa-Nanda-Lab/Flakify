@Test public void testParse2JSONObject() throws Exception {
  JSONObject jo=(JSONObject)JSON.parse("{name:'qianlei',array:[1,2,3,4,98.123],b1:TRUE,$1:NULL,$2:FALSE,__3:NULL}");
  assertEquals(jo.getString("name"),"qianlei");
  assertEquals(jo.getArray("array").length(),5);
  assertEquals(jo.get("$2"),Boolean.FALSE);
  assertEquals(jo.get("__3"),null);
  for (int i=0; i < 10000; i++)   JSON.parse("{\"name\":\"qianlei\",\"array\":[1,2,3,4,98.123],\"displayName\":\"钱磊\"}");
  long now=System.currentTimeMillis();
  for (int i=0; i < 10000; i++)   JSON.parse("{\"name\":\"qianlei\",\"array\":[1,2,3,4,98.123],\"displayName\":\"钱磊\"}");
  System.out.println("parse to JSONObject 10000 times in: " + (System.currentTimeMillis() - now));
}
