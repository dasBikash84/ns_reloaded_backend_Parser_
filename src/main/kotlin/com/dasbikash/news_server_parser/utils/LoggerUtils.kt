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

package com.dasbikash.news_server_parser.utils

import com.dasbikash.news_server_parser.database.DatabaseUtils
import com.dasbikash.news_server_parser.model.ErrorLog
import com.dasbikash.news_server_parser.model.GeneralLog
import org.hibernate.Session
import java.util.*

object LoggerUtils {

    fun logOnDb(message: String, session: Session) {
        DatabaseUtils.runDbTransection(session) {
            session.save(GeneralLog(message))
        }
    }

    fun logOnConsole(message: String) {
        println("${Date()}: ${message}")
    }

    fun logError( exception: Throwable,session: Session){
        DatabaseUtils.runDbTransection(session) {
            session.save(ErrorLog(exception))
        }
    }
}
