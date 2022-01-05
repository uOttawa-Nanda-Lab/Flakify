public void testCreation(){
  ControlLogger xyz=clc.getLogger("x.y.z");
  assertEquals("x.y.z",xyz.getName());
  assertEquals("x.y",xyz.parent.getName());
  assertEquals("x",xyz.parent.parent.getName());
  assertEquals("root",xyz.parent.parent.parent.getName());
  ControlLogger xyz_=clc.exists("x.y.z");
  assertEquals("x.y.z",xyz_.getName());
}
