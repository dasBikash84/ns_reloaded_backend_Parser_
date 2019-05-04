/*
 * Copyright 2019 das.bikash.dev@gmail.com. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dasbikash.news_server_parser.database

import com.dasbikash.news_server_parser.model.*
import com.dasbikash.news_server_parser.utils.LoggerUtils
import org.hibernate.Session

object DatabaseUtils {

    private val DB_WRITE_MAX_RETRY = 3

    fun runDbTransection(session: Session, operation: () -> Unit): Boolean {

        var retryLimit = DB_WRITE_MAX_RETRY;

        var exception:java.lang.Exception

        do {
            try {
                if (!session.transaction.isActive) {
                    session.beginTransaction()
                }
                operation()
                session.transaction.commit()
                return true
            } catch (ex: Exception) {
                ex.printStackTrace()
                exception = ex
            }
        }while (--retryLimit>0)

        val stackTrace = mutableListOf<StackTraceElement>()
        exception.stackTrace.toCollection(stackTrace)

        try {
            if (!session.transaction.isActive) {
                session.beginTransaction()
            }
            LoggerUtils.logMessage("Message: ${exception.message} Cause: ${exception.cause?.message} StackTrace: ${stackTrace}", session)
            session.transaction.commit()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return false
    }

    fun getAllActiveNewspapers(session: Session): List<Newspaper>{
        val hql = "FROM ${EntityClassNames.NEWSPAPER} where active=true"
        val query = session.createQuery(hql, Newspaper::class.java)
        return query.list() as List<Newspaper>
    }

    fun findArticleById(session: Session,id:String): Article?{
        val hql = "FROM ${EntityClassNames.ARTICLE} where id='${id}'"
        val query = session.createQuery(hql, Article::class.java)
        val resultList = query.list() as List<Article>
        if (resultList.size> 0){
            return resultList.get(0)
        }
        return null
    }

    /*fun getArticleCountForPage(session: Session, pageId:String):Int{
        val sql = "SELECT COUNT(*) FROM ${DatabaseTableNames.ARTICLE_TABLE_NAME} WHERE pageId='${pageId}' and articleText is not null"
        val result = session.createNativeQuery(sql, Article::class.java).resultList as List<Int>
        if (result.size > 0){
            return result.get(0)
        }
        return 0
    }*/
}


//val hql = "SELECT * FROM ${DatabaseTableNames.ARTICLE_TABLE_NAME} WHERE pageId='${page.id}' and articleText is not null"
//return session.createNativeQuery(hql, Article::class.java).resultList as List<Article>