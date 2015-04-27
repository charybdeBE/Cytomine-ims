package cytomine.web

/*
 * Copyright (c) 2009-2015. Authors: see NOTICE file.
 *
 * Licensed under the GNU Lesser General Public License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class SecurityFilters {
    def springSecurityService

    def filters = {
        all(uri:'/**') {
            before = {
                //println "Before security"
                //tryAPIAuthentification(request,response)
            }
            after = {
                //println "After security"

            }
            afterView = {

            }
        }
    }



}


