/*
 * Copyright (c) 2002-2025, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

package fr.paris.lutece.plugins.pluginwizard.service.generator;

import fr.paris.lutece.plugins.pluginwizard.business.model.PluginModel;

import java.util.HashMap;
import java.util.Map;

/**
* 
* This class generates service class (naming convention: [TaskName]Service, Located in the service directory.)
* that implements the business logic for a specific workflow task.
*/
public class TaskServiceGenerator extends AbstractGenerator
{
	
    private static final String PATH = "src/java/fr/paris/lutece/plugins/workflow/modules/{plugin_name}/service/";
    private static final String EXT = "Service.java";
    
    /**
     * {@inheritDoc }
     */
	@Override
	public Map<String, String> generate( PluginModel pm, String generationSchemeName )
	{
		
		HashMap<String, String> map = new HashMap<>( );
		
		String strFileName = pm.getConfiguration( ).getWorkflowTaskName( ) + EXT;
		String strPath = getFilePath( pm, PATH.replace( "{plugin_name}",pm.getPluginNameForRessource( ) ), strFileName );
		
		String strSourceCode = getCode( pm );
		
		map.put( strPath, strSourceCode );
		
		return map;
	}
	

    /**
     * Produces the file code in a map
     *
     * @param pm
     * 
     * @return the map
     */
    protected String getCode( PluginModel pm )
    {
    	Map<String, Object> model = new HashMap<>( );
    	model.put( Markers.MARK_TASK_NAME, pm.getConfiguration().getWorkflowTaskName( ) );
        model.put( Markers.MARK_RADICAL_PACKAGE, pm.getPluginNameAsRadicalPackage( ) );
        
        return build( model );
    }

}