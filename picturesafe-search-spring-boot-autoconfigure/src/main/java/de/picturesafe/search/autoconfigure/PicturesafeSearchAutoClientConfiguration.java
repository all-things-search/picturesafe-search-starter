/*
 * Copyright 2020 picturesafe media/data/bank GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.picturesafe.search.autoconfigure;

import de.picturesafe.search.elasticsearch.ElasticsearchService;
import de.picturesafe.search.elasticsearch.config.RestClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ElasticsearchService.class)
public class PicturesafeSearchAutoClientConfiguration {

    @Value("${elasticsearch.hosts:localhost:9200}")
    private String elasticsearchHosts;

    @Value("${elasticsearch.sniffer.enabled:false}")
    private boolean snifferEnabled;

    @Bean
    @ConditionalOnMissingBean
    public RestClientConfiguration restClientConfiguration() {
        final RestClientConfiguration rcc = new RestClientConfiguration(elasticsearchHosts);
        rcc.setSnifferEnabled(snifferEnabled);
        return rcc;
    }
}
