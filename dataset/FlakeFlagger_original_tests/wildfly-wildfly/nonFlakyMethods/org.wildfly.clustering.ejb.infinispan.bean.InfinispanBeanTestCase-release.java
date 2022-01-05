@Test public void release(){
  this.bean.release();
  verify(this.group).releaseBean(this.id,this.listener);
}
