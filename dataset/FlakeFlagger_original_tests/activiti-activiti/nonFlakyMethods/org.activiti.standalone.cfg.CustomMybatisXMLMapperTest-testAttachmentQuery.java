public void testAttachmentQuery(){
  String taskId=createTask("task1",null,null,0);
  Authentication.setAuthenticatedUserId("kermit");
  String attachmentId=taskService.createAttachment("image/png",taskId,null,"attachment1","","http://activiti.org/").getId();
  taskService.createAttachment("image/jpeg",taskId,null,"attachment2","Attachment Description","http://activiti.org/");
  Authentication.setAuthenticatedUserId("gonzo");
  taskService.createAttachment("image/png",taskId,null,"zattachment3","Attachment Description","http://activiti.org/");
  Authentication.setAuthenticatedUserId("fozzie");
  for (int i=0; i < 15; i++) {
    taskService.createAttachment(null,createTask(i + "",null,null,0),null,"attachmentName" + i,"","http://activiti.org/" + i);
  }
  assertEquals(attachmentId,new AttachmentQuery(managementService).attachmentId(attachmentId).singleResult().getId());
  assertEquals("attachment1",new AttachmentQuery(managementService).attachmentName("attachment1").singleResult().getName());
  assertEquals(18,new AttachmentQuery(managementService).count());
  List<Attachment> attachments=new AttachmentQuery(managementService).list();
  assertEquals(18,attachments.size());
  attachments=new AttachmentQuery(managementService).listPage(0,10);
  assertEquals(10,attachments.size());
  assertEquals(3,new AttachmentQuery(managementService).taskId(taskId).count());
  attachments=new AttachmentQuery(managementService).taskId(taskId).list();
  assertEquals(3,attachments.size());
  assertEquals(2,new AttachmentQuery(managementService).userId("kermit").count());
  attachments=new AttachmentQuery(managementService).userId("kermit").list();
  assertEquals(2,attachments.size());
  assertEquals(1,new AttachmentQuery(managementService).attachmentType("image/jpeg").count());
  attachments=new AttachmentQuery(managementService).attachmentType("image/jpeg").list();
  assertEquals(1,attachments.size());
  assertEquals("zattachment3",new AttachmentQuery(managementService).orderByAttachmentName().desc().list().get(0).getName());
  deleteTasks(taskService.createTaskQuery().list());
}
