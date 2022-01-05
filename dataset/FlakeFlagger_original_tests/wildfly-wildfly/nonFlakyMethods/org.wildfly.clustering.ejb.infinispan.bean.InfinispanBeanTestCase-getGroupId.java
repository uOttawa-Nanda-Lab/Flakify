@Test public void getGroupId(){
  String groupId="group";
  when(this.entry.getGroupId()).thenReturn(groupId);
  String result=this.bean.getGroupId();
  Assert.assertSame(groupId,result);
}
