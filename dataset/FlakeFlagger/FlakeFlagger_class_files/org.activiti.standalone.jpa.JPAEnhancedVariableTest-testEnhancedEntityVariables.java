package org.activiti.standalone.jpa;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.test.AbstractActivitiTestCase;
import org.activiti.engine.impl.variable.EntityManagerSession;
import org.activiti.engine.impl.variable.EntityManagerSessionFactory;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for JPA enhancement support
 * 

 */
public class JPAEnhancedVariableTest extends AbstractActivitiTestCase {

  private static final Logger logger = LoggerFactory.getLogger(JPAEnhancedVariableTest.class);
  private static EntityManagerFactory entityManagerFactory;
  protected static ProcessEngine cachedProcessEngine;

  private static FieldAccessJPAEntity fieldEntity;
  private static FieldAccessJPAEntity fieldEntity2;
  private static PropertyAccessJPAEntity propertyEntity;

  private static void setupJPAVariables() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    fieldEntity = new FieldAccessJPAEntity();
    fieldEntity.setId(1L);
    fieldEntity.setValue("fieldEntity");
    em.persist(fieldEntity);

    propertyEntity = new PropertyAccessJPAEntity();
    propertyEntity.setId(1L);
    propertyEntity.setValue("propertyEntity");
    em.persist(propertyEntity);

    em.flush();
    em.getTransaction().commit();
    em.close();

    // load enhanced versions of entities
    em = entityManagerFactory.createEntityManager();

    fieldEntity = em.find(FieldAccessJPAEntity.class, fieldEntity.getId());
    propertyEntity = em.find(PropertyAccessJPAEntity.class, propertyEntity.getId());

    em.getTransaction().begin();

    fieldEntity2 = new FieldAccessJPAEntity();
    fieldEntity2.setId(2L);
    fieldEntity2.setValue("fieldEntity2");
    em.persist(fieldEntity2);

    em.flush();
    em.getTransaction().commit();
    em.close();
  }

  private Task getTask(ProcessInstance instance) {
    return processEngine.getTaskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).includeProcessVariables().singleResult();
  }

  @Deployment(resources={"org/activiti/standalone/jpa/JPAVariableTest.testStoreJPAEntityAsVariable.bpmn20.xml"}) public void testEnhancedEntityVariables() throws Exception{if (FieldAccessJPAEntity.class == fieldEntity.getClass() || PropertyAccessJPAEntity.class == propertyEntity.getClass()){logger.warn("Entity enhancement is not used");return;}Map<String, Object> params=new HashMap<String, Object>();params.put("fieldEntity",fieldEntity);params.put("propertyEntity",propertyEntity);ProcessInstance instance=processEngine.getRuntimeService().startProcessInstanceByKey("JPAVariableProcess",params);Task task=getTask(instance);for (Map.Entry<String, Object> entry:task.getProcessVariables().entrySet()){String name=entry.getKey();Object value=entry.getValue();if (name.equals("fieldEntity")){assertTrue(value instanceof FieldAccessJPAEntity);} else if (name.equals("propertyEntity")){assertTrue(value instanceof PropertyAccessJPAEntity);} else {fail();}}}
}