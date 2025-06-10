 const listProjectType = ['plugin','module','workflowtask'];
 
/* Function to automatically change help message div according to the project type selected
(In first page : pluginwizard_create_plugin.html) */
function switchTypeProject(dictProject) 
{	
	//Get the project type selected
	const radios = document.querySelectorAll('input[name="type"]');
	const selectedRadio = Array.from(radios).find(radio => radio.checked); // Find selected radio
	const projectType = selectedRadio ? selectedRadio.value.toLowerCase() : '';
	
	//Get help message div and name input
	const helpMessageDiv = document.getElementById('projectName-help-message-div');
	const projectNameInput = document.getElementById('projectName');
	   
	//Update innput state
	if (projectNameInput && helpMessageDiv) 
	{
		projectNameInput.value = '';
		projectNameInput.placeholder = '';
		cleanClassListInput(projectNameInput);
		helpMessageDiv.innerHTML = '';		
		
 		if (listProjectType.includes(projectType))
 		{	
			projectNameInput.placeholder = dictProject[projectType]['placeholder'];
			switchDisplaySpan(projectType,dictProject,helpMessageDiv);
	   	}
	}
	
	switchInteractiveValidationMessages(projectNameInput);
}

/*Create specific spans associated to project type*/
function switchDisplaySpan(projectType,dictProject,divMessageHelp)
{
	dictProject[projectType]['span'].forEach( 
		function(featureSpan) {
			
			var span = document.createElement('span');
			var icon = document.createElement('i');
			
			//add classes
			icon.classList.add('ti', 'ti-info-circle', 'mx-1'); 
			span.classList.add('text-muted');
			
			//add id
			icon.id = featureSpan['name']+'-help-icon';
			span.id = featureSpan['name'];
			
			//add rule
			var strRule = featureSpan['regex']?String(featureSpan['regex']):'';
			span.setAttribute( 'rule', strRule );
			
			//create span text
			var text = document.createTextNode(featureSpan['message']);

			//append features to span
			span.appendChild(icon);
			span.appendChild(text);

			//append span to div
			divMessageHelp.appendChild(span);
		}
	);		
}

/* Function to auto fill the form of business class (In Business section : pluginwizard_create_business_class.html) */
function autoFillBusinessClassForm()
{
	
	const MAX_SQL = 64;
	
	// Select plugin name value
    const strPluginName = document.getElementById('plugin_name').value.replaceAll('-','_');
	
    // Select form inputs		
	const businessClassNameInput = document.getElementById('businessClassName');
    const pluralBusinessClassNameInput = document.getElementById('pluralBusinessClassName');
    const businesstableNameInput = document.getElementById('businessTableName');
    
	//erase forbidden caracters
	var strCleanBusinessClassName = businessClassNameInput.value.replace(/[^a-zA-Z _]/g,"");
	
	//Formated names
	var strBusinessClassNameFormated = strCleanBusinessClassName.replace(/[_\s]+(.)/g, (_, letter) => letter.toUpperCase()).replace(/[ _]/g,"");
	strBusinessClassNameFormated = strBusinessClassNameFormated.charAt(0).toUpperCase() + strBusinessClassNameFormated.slice(1)
	
	//Assign values ​​to form inputs
	businessClassNameInput.value = strBusinessClassNameFormated;
	pluralBusinessClassNameInput.value = pluralize(strBusinessClassNameFormated);
	businesstableNameInput.value = (strPluginName+strBusinessClassNameFormated).replace(/([A-Z])/g, '_$1').toLowerCase().slice(0,MAX_SQL);
	
}


/* Function to auto fill the form of configuration class (In Business section : pluginwizard_create_configuration_class.html) */
function autoFillConfigurationClassForm(strConfigurationClassFormId)
{
	
	const MAX_SQL = 64; 
	const suffixClass="Config";
	
	// Select plugin name value
	const strTaskName = document.getElementById('taskName').value;
	const strWorkflowTask = "workflow_task_";
	
    // Select form inputs		
	const configurationClassNameInput = document.getElementById('configurationClassName');
    const pluralConfigurationClassNameInput = document.getElementById('pluralConfigurationClassName');
    const configurationTableNameInput = document.getElementById('configurationTableName');
    
	//erase forbidden caracters
	var strCleanTaskName = strTaskName.replace(/[^a-zA-Z _]/g,""); 
	
	//Formated names
	var strConfigurationClassNameFormated = strCleanTaskName.replace(/[_\s]+(.)/g, (_, letter) => letter.toUpperCase()).replace(/[ _]/g,"");
	strConfigurationClassNameFormated = strConfigurationClassNameFormated.charAt(0).toUpperCase() + strConfigurationClassNameFormated.slice(1);
	strConfigurationClassNameFormated = strConfigurationClassNameFormated;
	
	//Assign values ​​to form inputs
	configurationClassNameInput.value = strConfigurationClassNameFormated+suffixClass;
	pluralConfigurationClassNameInput.value = pluralize(strConfigurationClassNameFormated+suffixClass);
	configurationTableNameInput.value = (strWorkflowTask+strConfigurationClassNameFormated.slice(0, -4)+'_'+suffixClass).toLowerCase().slice(0,MAX_SQL);
	
}


/* Function to auto fill the form of the admin feature (in Administration section : pluginwizard_create_admin_feature.html) */
function autoFillAdminFeatureForm()
{
	
	const MAX_LENGTH_INPUT_DEFAULT = 255;
	    
	// Select inputs
    const featureTitleInput = document.querySelector('[name="feature_title"]');
    const featureNameInput = document.querySelector('[name="feature_name"]');
    const featureRightInput = document.querySelector('[name="feature_right"]');

 	// Select plugin name value
    const pluginName = document.querySelector('[name="plugin_name"]').value.replaceAll('-','_');
 
    //Get maxLength when it exist
    const max_length_featureName = featureNameInput.hasAttribute('maxLength')?featureNameInput.maxLength:MAX_LENGTH_INPUT_DEFAULT;
    const max_length_RightInput = featureRightInput.hasAttribute('maxLength')?featureRightInput.maxLength:MAX_LENGTH_INPUT_DEFAULT;
    
	// Clean and format the feature title
    const cleanFeatureTitleInput = featureTitleInput.value.toLowerCase().replace(/[^a-z _]/g, "").split(/[\s,"_"]+/).map((x=>x.charAt(0).toUpperCase()+x.slice(1))).join('');
   	
	//Assign new values ​​to form inputs
    featureNameInput.value = ("Manage"+cleanFeatureTitleInput).slice(0,max_length_featureName);
    featureRightInput.value = (pluginName.toUpperCase()+"_MANAGEMENT"+cleanFeatureTitleInput.replace(/([A-Z])/g, '_$1').toUpperCase()).slice(0,max_length_RightInput);
	
}


function updateDivInfoVisibility(strFormId)
{
	// Sélectionner all text type inputs in form
	const form = document.getElementById(strFormId);
	
	if(form){
		const selects = form.querySelectorAll('select');
	
		selects.forEach(select => {
		    switchDivInfoVisibility(select.id);
		});
	}	
}


/* Switch message when select value change  */
function switchDivInfoVisibility(selectId)
{	
	
	const selectForm = document.getElementById(selectId);
	const selectValue = selectForm.value==0?"False":"True";
	const HelpMessageDivId = selectId+"-help-message-div";

	const helpMessageDiv = document.getElementById(HelpMessageDivId);
	
	
	if(helpMessageDiv){
		
		const helperSpans = helpMessageDiv.querySelectorAll('span');
			
		helperSpans.forEach(span => {
			
			if (span.id.includes(selectValue)) {
				span.classList.remove('d-none');
				span.classList.add('d-block');
			}
			else {
				span.classList.remove('d-block');
				span.classList.add('d-none');
			}
		});
	}
}


/* Switch message when automatic task select value change  */
function switchSelectTaskFormVisibility()
{
	
	const autoTastId='workflowTaskForAutomaticAction';
	const formTaskid='workflowFormTaskRequired';
	
	const autoTaskSelect = document.getElementById(autoTastId);
	const formTaskSelect = document.getElementById(formTaskid);
    
	if(autoTaskSelect.value==1)
	{
		formTaskSelect.value = 0;
		formTaskSelect.disabled = true;	
	}
	else
	{
		formTaskSelect.disabled = false;
	}
	
	switchDivInfoVisibility(formTaskid);

}