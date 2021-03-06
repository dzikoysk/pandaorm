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

package org.panda_lang.orm.sql.queries;

import org.panda_lang.utilities.commons.StringUtils;
import org.panda_lang.utilities.commons.function.ThrowingConsumer;
import org.panda_lang.utilities.commons.text.ContentJoiner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.function.Function;

public final class SqlUtils {

    private static final String IDENTIFIER = "`";
    private static final String VALUE = "'";
    private static final String NULL = "NULL";

    private SqlUtils() { }

    public static void consume(Connection connection, String statement, ThrowingConsumer<ResultSet, SQLException> resultSetConsumer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            resultSetConsumer.accept(result);
        }
    }

    public static String generateValues(int amount) {
        String content = StringUtils.repeated(amount, "?, ");

        if (!content.isEmpty()) {
            content = content.substring(0, content.length() - 2);
        }

        return content;
    }

    public static String toIdentifier(Object value) {
        return value == null ? NULL : IDENTIFIER + value + IDENTIFIER;
    }

    public static String toValue(Object value) {
        return toQuoted(value, VALUE);
    }

    public static String toQuoted(Object value, String operator) {
        return value == null ? NULL : operator + value + operator;
    }

    public static String toIdentifierList(Collection<?> elements) {
        return toIdentifierList(elements, SqlUtils::toIdentifier);
    }

    public static <T> String toIdentifierList(Collection<T> elements, Function<T, String> function) {
        return ContentJoiner.on(", ").join(elements, value -> toIdentifier(function.apply(value))).toString();
    }

    public static <T> String toCustomList(Collection<T> elements, Function<T, String> function) {
        return ContentJoiner.on(", ").join(elements, function::apply).toString();
    }

    public static String toValueList(Collection<?> elements) {
        return ContentJoiner.on(", ").join(elements, SqlUtils::toValue).toString();
    }

}
