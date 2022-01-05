@Test public void testSecurity(){
  assertEquals(new JndiPermission("-",Integer.MAX_VALUE).getActionBits(),JndiPermission.ACTION_ALL);
  assertEquals(new JndiPermission("-",Integer.MAX_VALUE),new JndiPermission("-","*"));
}
