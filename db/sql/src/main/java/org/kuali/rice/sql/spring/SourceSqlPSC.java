/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.sql.spring;

import java.util.List;

import com.google.common.collect.Lists;
import org.kuali.common.jdbc.project.spring.JdbcPropertyLocationsConfig;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.properties.spring.DefaultPropertiesServiceConfig;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

/**
 * Holds the property source for all of the different properties needed for the database reset process.
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
@Configuration
@Import({ SourceSqlProjectConfig.class, SourceSqlPropertyLocationsConfig.class, JdbcPropertyLocationsConfig.class, DefaultPropertiesServiceConfig.class })
public class SourceSqlPSC implements PropertySourceConfig {

    /**
     * The general JDBC property locations.
     */
	@Autowired
	JdbcPropertyLocationsConfig jdbcConfig;

    /**
     * The Rice property locations for the database reset process.
     */
	@Autowired
	SourceSqlPropertyLocationsConfig sourceSqlConfig;

    /**
     * The property locator.
     */
	@Autowired
	PropertiesService service;

    /**
     * {@inheritDoc}
     *
     * <p>
     * Here we combine all properties, making sure that the Rice project properties go in last.
     * </p>
     */
	@Override
	@Bean
	public PropertySource<?> propertySource() {
		List<Location> locations = Lists.newArrayList();

        locations.addAll(jdbcConfig.jdbcPropertyLocations());
		locations.addAll(sourceSqlConfig.riceSourceSqlPropertyLocations());

		return PropertySourceUtils.getPropertySource(service, locations);
	}

}