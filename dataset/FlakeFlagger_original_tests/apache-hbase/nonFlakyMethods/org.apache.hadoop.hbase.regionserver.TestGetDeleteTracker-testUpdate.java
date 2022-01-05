public void testUpdate(){
  List<Delete> dels1=new ArrayList<Delete>();
  dels1.add(delQf10);
  dels1.add(del21);
  List<Delete> dels2=new ArrayList<Delete>();
  dels2.add(delFam10);
  dels2.add(del30);
  dels2.add(delQf20);
  List<Delete> res=new ArrayList<Delete>();
  res.add(del30);
  res.add(delQf20);
  res.add(del21);
  for (  Delete del : dels1) {
    dt.add(del.buffer,del.qualifierOffset,del.qualifierLength,del.timestamp,del.type);
  }
  dt.update();
  List<Delete> delList=dt.deletes;
  assertEquals(dels1.size(),delList.size());
  for (int i=0; i < dels1.size(); i++) {
    assertEquals(0,Bytes.compareTo(dels1.get(i).buffer,delList.get(i).buffer));
    assertEquals(dels1.get(i).qualifierOffset,delList.get(i).qualifierOffset);
    assertEquals(dels1.get(i).qualifierLength,delList.get(i).qualifierLength);
    assertEquals(dels1.get(i).timestamp,delList.get(i).timestamp);
    assertEquals(dels1.get(i).type,delList.get(i).type);
  }
  for (  Delete del : dels2) {
    dt.add(del.buffer,del.qualifierOffset,del.qualifierLength,del.timestamp,del.type);
  }
  dt.update();
  delList=dt.deletes;
  for (int i=0; i < res.size(); i++) {
    assertEquals(0,Bytes.compareTo(res.get(i).buffer,delList.get(i).buffer));
    assertEquals(res.get(i).qualifierOffset,delList.get(i).qualifierOffset);
    assertEquals(res.get(i).qualifierLength,delList.get(i).qualifierLength);
    assertEquals(res.get(i).timestamp,delList.get(i).timestamp);
    assertEquals(res.get(i).type,delList.get(i).type);
    if (PRINT) {
      System.out.println("Qf " + new String(delList.get(i).buffer) + ", timestamp, "+ delList.get(i).timestamp+ ", type "+ KeyValue.Type.codeToType(delList.get(i).type));
    }
  }
}
