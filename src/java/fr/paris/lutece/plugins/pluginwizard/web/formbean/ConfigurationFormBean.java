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
package fr.paris.lutece.plugins.pluginwizard.web.formbean;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Configuration FormBean
 */
public class ConfigurationFormBean implements Serializable
{
    // Variables declarations
    private int _nIdPlugin;
    @NotEmpty( message = "pluginwizard.error.workflow.configuration.workflowTaskName.notEmpty" )
    @Pattern( regexp = "^[A-Z][A-Za-z]*Task$", message = "pluginwizard.error.workflow.configuration.workflowTaskName.pattern" )
    private String _strWorkflowTaskName;
    @Pattern( regexp = "^0|1$", message = "pluginwizard.error.workflow.configuration.workflowFormConfigRequired.pattern" )    
    private String _strWorkflowFormConfigRequired;
    @Pattern( regexp = "^0|1$", message = "pluginwizard.error.workflow.configuration.workflowFormTaskRequired.pattern" )
    private String _strWorkflowFormTaskRequired;
    @Pattern( regexp = "^0|1$", message = "pluginwizard.error.workflow.configuration.workflowTaskForAutomaticAction.pattern" )
    private String _strWorkflowTaskForAutomaticAction;   

    /**
     *
     */
    public ConfigurationFormBean( )
    {
    }

    /**
     * Returns the IdPlugin
     * 
     * @return The IdPlugin
     */
    public int getIdPlugin( )
    {
        return _nIdPlugin;
    }

    /**
     * Sets the IdPlugin
     * 
     * @param nIdPlugin
     *            The IdPlugin
     */
    public void setIdPlugin( int nIdPlugin )
    {
        _nIdPlugin = nIdPlugin;
    }

    /**
     * Returns WorkflowFormConfigRequired
     * 
     * @return The WorkflowFormConfigRequired
     */
    public String getWorkflowFormConfigRequired( )
    {
        return _strWorkflowFormConfigRequired;
    }

    /**
     * Sets the WorkflowFormConfigRequired
     * 
     * @param strWorkflowFormConfigRequired
     *            The WorkflowFormConfigRequired
     */
    public void setWorkflowFormConfigRequired( String strWorkflowFormConfigRequired )
    {
    	_strWorkflowFormConfigRequired = strWorkflowFormConfigRequired;
    }

    /**
     * Returns the WorkflowFormTaskRequired
     * 
     * @return The WorkflowFormTaskRequired
     */
    public String getWorkflowFormTaskRequired( )
    {
        return _strWorkflowFormTaskRequired;
    }

    /**
     * Sets the WorkflowFormTaskRequired
     * 
     * @param strWorkflowFormTaskRequired
     *            The WorkflowFormTaskRequired
     */
    public void setWorkflowFormTaskRequired( String strWorkflowFormTaskRequired )
    {
        _strWorkflowFormTaskRequired = strWorkflowFormTaskRequired;
    }

    /**
     * Returns the WorkflowTaskForAutomaticAction
     * 
     * @return The WorkflowTaskForAutomaticAction
     */
    public String getWorkflowTaskForAutomaticAction( )
    {
        return _strWorkflowTaskForAutomaticAction;
    }

    /**
     * Sets the WorkflowTaskForAutomaticAction
     * 
     * @param strWorkflowTaskForAutomaticAction
     *            The WorkflowTaskForAutomaticAction
     */
    public void setWorkflowTaskForAutomaticAction( String strWorkflowTaskForAutomaticAction )
    {
        _strWorkflowTaskForAutomaticAction = strWorkflowTaskForAutomaticAction;
    }
    
    /**
     * Returns the WorkflowTaskName
     * 
     * @return The WorkflowTaskName
     */
    public String getWorkflowTaskName( )
    {
        return _strWorkflowTaskName;
    }

    /**
     * Sets the WorkflowTaskName
     * 
     * @param strWorkflowTaskName
     *            The WorkflowTaskName
     */
    public void setWorkflowTaskName( String strWorkflowTaskName )
    {
        _strWorkflowTaskName = strWorkflowTaskName;
    }
}