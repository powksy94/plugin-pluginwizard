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

import fr.paris.lutece.plugins.pluginwizard.business.model.BusinessClass;
import fr.paris.lutece.plugins.pluginwizard.business.model.Configuration;
import fr.paris.lutece.plugins.pluginwizard.business.model.PluginModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * The Task form generator generates all the html forms files needed for workflow task creation
 */
public class TaskFormsGenerator extends AbstractGenerator
{
	private static final String PATH = "webapp/WEB-INF/templates/admin/plugins/workflow/modules/{plugin_name}/";
	private static final String EXT_HTML = ".html";
	private List<String> _suffix;
	
	
	/**
	 * 
	 */
	private void setSuffixList( PluginModel pluginModel ) 
	{
		_suffix = new ArrayList<>( );	
		_suffix.add( "_information" );
		
    	if( StringUtils.equals( pluginModel.getConfiguration( ).getWorkflowFormConfigRequired( ), "1" ) )
    	{  		
    		_suffix.add( "_config" );
    	}
    	
    	if( StringUtils.equals( pluginModel.getConfiguration( ).getWorkflowFormTaskRequired( ), "1" ) )
    	{	
    		_suffix.add( "_form" );
    	}
	}
	
    /**
     * {@inheritDoc }
     * 
     */
	@Override
	public Map<String, String> generate( PluginModel pluginModel, String generationSchemeName ) 
	{
	
		setSuffixList( pluginModel );
		
		HashMap map = new HashMap( );
	    String strTaskName = pluginModel.getConfiguration( ).getWorkflowTaskName( ).toLowerCase( );
	    if( strTaskName.length( ) > 4 )
	    {
	    	strTaskName = strTaskName.substring( 0, strTaskName.length( ) - 4 );
	    }
	    
        for ( String strSuffix : _suffix )
        {
                String strFormFile = "task_" + strTaskName + strSuffix + EXT_HTML;
                String strPath = getFilePath( pluginModel, PATH, strFormFile );
                String strTemplateType = strSuffix.startsWith("_") ? strSuffix.substring( 1 ) : strSuffix;
                
                String strSourceCode = getFormFile( pluginModel.getConfiguration( ), pluginModel.getBusinessClasses( ).get( 0 ), strTemplateType, pluginModel.getPluginName( ), this.getTemplate( ) );
                strSourceCode = strSourceCode.replace( "&lt;", "<" );
                strSourceCode = strSourceCode.replace( "&gt;", ">" );
                
                map.put( strPath, strSourceCode.replace( "@@", "#" ) );
        }

	        return map;
	}

    /**
     * Produces text content of html form file used to build a workflow task
     * 
     * @param configuration
     *            The instance of a workflow task configuration
     * @param businessClass
     *            The instance of the businessClass attached to workflow task (MyTaskConfig class)
     * @param strTemplateType
     *            the Template type ( form, config or information)
     * @param strPluginName
     *            The plugin name
     * @param strTemplate
     *            The template of form file
     * @return The content of the form file
     */
    private String getFormFile( Configuration configuration, BusinessClass businessClass, String strTemplateType, String strPluginName, String strTemplate )
    {
        Map<String, Object> model = new HashMap<>( );
        
        model.put( Markers.MARK_CONFIGURATION, configuration );
        model.put( Markers.MARK_BUSINESS_CLASS, businessClass );
        model.put( Markers.MARK_TASK_NAME, configuration.getWorkflowTaskName( ) );
        model.put( Markers.MARK_TEMPLATE_TYPE, strTemplateType );
        model.put( Markers.MARK_PLUGIN_NAME, strPluginName );
        
        model.put( Markers.MARK_I18N_BRACKETS_OPEN, "@@i18n{" );
        model.put( Markers.MARK_I18N_BRACKETS_CLOSE, "}" );
        model.put( Markers.MARK_MACRO, "@" );
        model.put( Markers.MARK_VARIABLE, "#" );
        model.put( Markers.MARK_BRACKETS_OPEN, "${" );
        model.put( Markers.MARK_BRACKETS_CLOSE, "}" );
        model.put( Markers.MARK_INCLUDE, "@@include" );

        return build( strTemplate, model );
    }
}
