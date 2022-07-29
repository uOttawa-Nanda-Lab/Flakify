/*
 * Copyright (C) 2012-2014 DuyHai DOAN
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package info.archinnov.achilles.test.integration.tests;

import static info.archinnov.achilles.configuration.ConfigurationParameters.BEAN_VALIDATION_ENABLE;
import static info.archinnov.achilles.configuration.ConfigurationParameters.ENTITIES_LIST;
import static info.archinnov.achilles.configuration.ConfigurationParameters.EVENT_INTERCEPTORS;
import static info.archinnov.achilles.configuration.ConfigurationParameters.KEYSPACE_NAME;
import static info.archinnov.achilles.configuration.ConfigurationParameters.NATIVE_SESSION;
import static org.fest.assertions.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import info.archinnov.achilles.configuration.ConfigurationParameters;
import info.archinnov.achilles.exception.AchillesBeanValidationException;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.persistence.PersistenceManagerFactory;
import info.archinnov.achilles.persistence.PersistenceManagerFactory.PersistenceManagerFactoryBuilder;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.EntityWithClassLevelConstraint;
import info.archinnov.achilles.test.integration.entity.EntityWithFieldLevelConstraint;
import info.archinnov.achilles.test.integration.entity.EntityWithGroupConstraint;
import info.archinnov.achilles.test.integration.entity.EntityWithGroupConstraint.CustomValidationInterceptor;
import info.archinnov.achilles.test.integration.entity.EntityWithPropertyLevelConstraint;

public class BeanValidationIT {

    @Rule
    public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(
            EntityWithClassLevelConstraint.TABLE_NAME, EntityWithFieldLevelConstraint.TABLE_NAME,
            EntityWithPropertyLevelConstraint.TABLE_NAME);

    private PersistenceManager manager = resource.getPersistenceManager();

    @Test public void should_error_on_invalid_class_persist() throws Exception{boolean exceptionRaised=false;Long id=RandomUtils.nextLong();EntityWithClassLevelConstraint entity=new EntityWithClassLevelConstraint();entity.setId(id);entity.setFirstname("fn");StringBuilder errorMessage=new StringBuilder("Bean validation error : \n");errorMessage.append("\tfirstname and lastname should not be blank for class '");errorMessage.append(EntityWithClassLevelConstraint.class.getCanonicalName()).append("'");try {manager.persist(entity);} catch (AchillesBeanValidationException ex){assertThat(ex.getMessage()).contains(errorMessage.toString());exceptionRaised=true;}assertThat(exceptionRaised).isTrue();}
}
