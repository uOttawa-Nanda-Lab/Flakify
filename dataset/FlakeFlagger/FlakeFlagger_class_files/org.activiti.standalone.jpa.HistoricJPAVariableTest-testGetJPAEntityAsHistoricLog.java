package org.activiti.standalone.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricData;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.impl.test.AbstractActivitiTestCase;
import org.activiti.engine.impl.variable.EntityManagerSession;
import org.activiti.engine.impl.variable.EntityManagerSessionFactory;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class HistoricJPAVariableTest extends AbstractActivitiTestCase {
	
	protected static ProcessEngine cachedProcessEngine;

	private static EntityManagerFactory entityManagerFactory;
	
	private static FieldAccessJPAEntity simpleEntityFieldAccess;
	private static boolean entitiesInitialized = false;
	
	protected String processInstanceId;

	@Deployment public void testGetJPAEntityAsHistoricLog(){setupJPAEntities();Map<String, Object> variables=new HashMap<String, Object>();variables.put("simpleEntityFieldAccess",simpleEntityFieldAccess);this.processInstanceId=runtimeService.startProcessInstanceByKey("JPAVariableProcess",variables).getId();for (Task task:taskService.createTaskQuery().includeTaskLocalVariables().list()){taskService.complete(task.getId());}ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeVariables().singleResult();List<HistoricData> events=log.getHistoricData();for (HistoricData event:events){Object value=((HistoricVariableInstanceEntity)event).getValue();assertTrue(value instanceof FieldAccessJPAEntity);assertEquals(((FieldAccessJPAEntity)value).getValue(),simpleEntityFieldAccess.getValue());}}
}