/*
 * Copyright (c) 2002-2022, City of Paris
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
package fr.paris.lutece.plugins.pluginwizard.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.pluginwizard.business.model.Application;
import fr.paris.lutece.plugins.pluginwizard.business.model.Attribute;
import fr.paris.lutece.plugins.pluginwizard.business.model.BusinessClass;
import fr.paris.lutece.plugins.pluginwizard.business.model.Feature;
import fr.paris.lutece.plugins.pluginwizard.business.model.Portlet;
import fr.paris.lutece.plugins.pluginwizard.web.formbean.BusinessClassFormBean;

/**
 * Model Service provides all plugin'model manipulations QualityService checks if the values ​​entered in the pluginwizard forms are already used
 */
public final class QualityService
{
    // Validation for business class
    public static boolean bValidUniqueBusinessClassName;
    public static boolean bValidUniquePluralBusinessClassName;
    public static boolean bValidUniqueTableName;

    // Validation for attribute
    public static boolean bValidUniqueAttributeName;

    // Validation for admin feature
    public static boolean bValideUniqueFeatureRight;
    public static boolean bValideUniqueFeatureTitle;
    public static boolean bValideUniqueFeatureTechName;

    // Validation for XPage
    public static boolean bValideUniqueAdministrationName;
    public static boolean bValideUniqueAdministrationClass;

    // Validation for portlet
    public static boolean bValideUniquePortletClassName;
    public static boolean bValideUniquePortletType;
    public static boolean bValideUniquePortletJspName;

    //////////////////////////////////// Validation for admin feature///////////////////////////////////////

    /**
     * Check if the feature title already used
     * 
     * @param feature
     *            Feature to check.
     * @param features
     *            The list of features in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateFeatureTitle( Feature feature, List<Feature> features )
    {

        for ( Feature item : features )
        {
            if ( item.getFeatureTitle( ).equals( feature.getFeatureTitle( ) ) && item.getId( ) != feature.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the feature tech name already used
     * 
     * @param feature
     *            Feature to check.
     * @param features
     *            The list of features in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateFeatureTechName( Feature feature, List<Feature> features )
    {
        for ( Feature item : features )
        {
            if ( item.getFeatureName( ).equals( feature.getFeatureName( ) ) && item.getId( ) != feature.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the feature right already used
     * 
     * @param feature
     *            Feature to check.
     * @param features
     *            The list of features in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateFeatureRight( Feature feature, List<Feature> features )
    {
        for ( Feature item : features )
        {
            if ( item.getFeatureRight( ).equals( feature.getFeatureRight( ) ) && item.getId( ) != feature.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the form feature entries already used
     * 
     * @param feature
     *            Feature to check.
     * @param features
     *            The list of features in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateFeatureFields( Feature feature, List<Feature> features )
    {
        bValideUniqueFeatureRight = !existsDuplicateFeatureRight( feature, features );
        bValideUniqueFeatureTitle = !existsDuplicateFeatureTitle( feature, features );
        bValideUniqueFeatureTechName = !existsDuplicateFeatureTechName( feature, features );

        return !( bValideUniqueFeatureRight && bValideUniqueFeatureTitle && bValideUniqueFeatureTechName );
    }

    //////////////////////////////////// Validation for Business Class ///////////////////////////////////////

    /**
     * Check if Business Class Name is already used
     * 
     * @param businessClass
     *            business class to check.
     * @param businessClasses
     *            The list of business class in the plugin model
     * @param bCreate
     *            Boolean to know if it's a creation or a modification of business class
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateBusinessClassName( BusinessClassFormBean businessClass, List<BusinessClass> businessClasses, boolean bCreate )
    {

        for ( BusinessClass item : businessClasses )
        {
            if ( StringUtils.equals( StringUtils.lowerCase( item.getBusinessClass( ) ), StringUtils.lowerCase( businessClass.getBusinessClass( ) ) )
                    && ( item.getId( ) != businessClass.getId( ) || bCreate ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if Business Class Plural Name is already used
     * 
     * @param businessClass
     *            business class to check.
     * @param businessClasses
     *            The list of business class in the plugin model
     * @param bCreate
     *            Boolean to know if it's a creation or a modification of business class
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicatePluralBusinessClassName( BusinessClassFormBean businessClass, List<BusinessClass> businessClasses, boolean bCreate )
    {

        for ( BusinessClass item : businessClasses )
        {
            if ( StringUtils.equals( StringUtils.lowerCase( item.getPluralBusinessClass( ) ), StringUtils.lowerCase( businessClass.getPluralBusinessClass( ) ) )
                    && ( item.getId( ) != businessClass.getId( ) || bCreate ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if Business Class Table Name is already used
     * 
     * @param businessClass
     *            business class to check.
     * @param businessClasses
     *            The list of business class in the plugin model
     * @param bCreate
     *            Boolean to know if it's a creation or a modification of business class
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateTableName( BusinessClassFormBean businessClass, List<BusinessClass> businessClasses, boolean bCreate )
    {

        for ( BusinessClass item : businessClasses )
        {
            if ( StringUtils.equals( StringUtils.lowerCase( item.getBusinessTableName( ) ), StringUtils.lowerCase( businessClass.getBusinessTableName( ) ) )
                    && ( item.getId( ) != businessClass.getId( ) || bCreate ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the form business Class entries already used
     * 
     * @param businessClass
     *            business class to check.
     * @param businessClasses
     *            The list of business class in the plugin model
     * @param bCreate
     *            Boolean to know if it's a creation or a modification of business class
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateBusinessClassFields( BusinessClassFormBean businessClass, List<BusinessClass> businessClasses, boolean bCreate )
    {

        bValidUniqueBusinessClassName = !existsDuplicateBusinessClassName( businessClass, businessClasses, bCreate );
        bValidUniquePluralBusinessClassName = !existsDuplicatePluralBusinessClassName( businessClass, businessClasses, bCreate );
        bValidUniqueTableName = !existsDuplicateTableName( businessClass, businessClasses, bCreate );

        return !( bValidUniqueBusinessClassName && bValidUniquePluralBusinessClassName && bValidUniqueTableName );
    }

    //////////////////////////////////// Validation for Business Class ///////////////////////////////////////

    /**
     * Check if attribute Name entry is already used in a specific business class
     * 
     * @param attribute
     *            attribute to check.
     * @param attributes
     *            The list of attributes in a specific business class
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateAttributeName( Attribute attribute, List<Attribute> attributes )
    {

        for ( Attribute item : attributes )
        {
            if ( StringUtils.equals( StringUtils.lowerCase( item.getAttributeName( ) ), StringUtils.lowerCase( attribute.getAttributeName( ) ) )
                    && item.getId( ) != attribute.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the form attribute Name entries already used
     * 
     * @param attribute
     *            attribute to check.
     * @param attributes
     *            The list of attributes in a specific business class
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateAttributeFields( Attribute attribute, List<Attribute> attributes )
    {

        bValidUniqueAttributeName = !existsDuplicateAttributeName( attribute, attributes );

        return !bValidUniqueAttributeName;
    }

    //////////////////////////////////// Validation for Application ///////////////////////////////////////

    /**
     * Check if application Name entry is already used
     * 
     * @param application
     *            application to check.
     * @param applications
     *            The list of applications
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateAttributeName( Application application, List<Application> applications )
    {

        for ( Application item : applications )
        {
            if ( StringUtils.equals( StringUtils.lowerCase( item.getApplicationName( ) ), StringUtils.lowerCase( application.getApplicationName( ) ) )
                    && item.getId( ) != application.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if application Class entry is already used
     * 
     * @param application
     *            application to check.
     * @param applications
     *            The list of applications
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateAttributeClass( Application application, List<Application> applications )
    {

        for ( Application item : applications )
        {
            if ( StringUtils.equals( StringUtils.lowerCase( item.getApplicationClass( ) ), StringUtils.lowerCase( application.getApplicationClass( ) ) )
                    && item.getId( ) != application.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the application form entries already used
     * 
     * @param application
     *            application to check.
     * @param applications
     *            The list of applications in a specific business class
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicateApplicationFields( Application application, List<Application> applications )
    {

        bValideUniqueAdministrationName = !existsDuplicateAttributeName( application, applications );
        bValideUniqueAdministrationClass = !existsDuplicateAttributeClass( application, applications );

        return !( bValideUniqueAdministrationName && bValideUniqueAdministrationClass );
    }

    //////////////////////////////////// Validation for portlet///////////////////////////////////////

    /**
     * Check if the portlet class name is already used
     * 
     * @param portlet
     *            Portlet to check.
     * @param portlets
     *            The list of portlet in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicatePortletClassName( Portlet portlet, List<Portlet> portlets )
    {

        for ( Portlet item : portlets )
        {
            if ( item.getPortletClass( ).equals( portlet.getPortletClass( ) ) && item.getId( ) != portlet.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the portlet type already used
     * 
     * @param portlet
     *            Portlet to check.
     * @param portlets
     *            The list of portlet in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicatePortletType( Portlet portlet, List<Portlet> portlets )
    {
        for ( Portlet item : portlets )
        {
            if ( item.getPortletTypeName( ).equals( portlet.getPortletTypeName( ) ) && item.getId( ) != portlet.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the portlet jsp name already used
     * 
     * @param portlet
     *            Portlet to check.
     * @param portlets
     *            The list of portlet in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicatePortletJspName( Portlet portlet, List<Portlet> portlets )
    {
        for ( Portlet item : portlets )
        {
            if ( item.getJspBaseName( ).equals( portlet.getJspBaseName( ) ) && item.getId( ) != portlet.getId( ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the form feature entries already used
     * 
     * @param portlet
     *            Portlet to check.
     * @param portlets
     *            The list of portlet in the plugin model
     * @return true if it already used, false otherwise.
     */
    public static boolean existsDuplicatePortletFields( Portlet portlet, List<Portlet> portlets )
    {
        bValideUniquePortletClassName = !existsDuplicatePortletClassName( portlet, portlets );
        bValideUniquePortletType = !existsDuplicatePortletType( portlet, portlets );
        bValideUniquePortletJspName = !existsDuplicatePortletJspName( portlet, portlets );

        return !( bValideUniquePortletClassName && bValideUniquePortletType && bValideUniquePortletJspName );
    }

}
