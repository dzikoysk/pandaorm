/*
 * Copyright (c) 2019-2020 Dzikoysk
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

package org.panda_lang.orm.repository;

import org.panda_lang.orm.entity.DataEntity;
import org.panda_lang.orm.properties.GenerationStrategy;
import org.panda_lang.orm.query.DataQuery;
import org.panda_lang.orm.transaction.DataTransactionResult;

public interface DataHandler<ENTITY extends DataEntity<ENTITY>> {

    ENTITY create(Object[] constructorArguments) throws Exception;

    <GENERATED> GENERATED generate(Class<GENERATED> requestedType, GenerationStrategy strategy) throws Exception;

    void save(DataTransactionResult<ENTITY> transaction) throws Exception;

    <QUERY> QUERY find(DataQuery<QUERY> query, Object[] queryValues) throws Exception;

    void delete(ENTITY entity) throws Exception;

    void handleException(Exception e);

    Class<ENTITY> getDataType();

    String getIdentifier();

}
