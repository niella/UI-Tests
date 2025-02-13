package com.uitest;

import com.morpheusdata.core.providers.AbstractGenericIntegrationProvider
import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.views.HTMLResponse
import com.morpheusdata.views.ViewModel
import com.morpheusdata.model.AccountIntegration
import com.morpheusdata.model.AccountIntegrationType
import groovy.util.logging.Slf4j

import com.morpheusdata.model.OptionType
import com.morpheusdata.model.Icon

@Slf4j
class UiTestGenericProvider extends AbstractGenericIntegrationProvider {

	public static final String PROVIDER_CODE = 'uiTest.generic'
	public static final String PROVIDER_NAME = 'UI Test Generic Provider'

	protected MorpheusContext context
	protected Plugin plugin

	UiTestGenericProvider(Plugin plugin, MorpheusContext context) {
		this.@plugin = plugin
		this.@context = context
	}

	/**
	 * The category of the integration. This is used to group integrations in the UI. Available categories are defined in {@link AccountIntegration.Category}.
	 * @return
	 */
	@Override
	String getCategory() {
		return "other" //AccountIntegrationType.Category.other
	}

	/**
	 * Returns the Integration logo for display when a user needs to view or add this integration
	 * @return Icon representation of assets stored in the src/assets of the project.
	 */
	@Override
	Icon getIcon() {
		// TODO: change icon paths to correct filenames once added to your project
		return new Icon(path:"morpheus.svg", darkPath: "morpheus.svg")
	}

	/**
	 * Refresh the integration with the latest data from the provider. This method will be called based on the integration refresh interval
	 * configured in the Morpheus Appliance UI settings.
	 * @param accountIntegration the integration to refresh
	 */
	@Override
	void refresh(AccountIntegration accountIntegration) {
		//TODO: data syncing operations
	}

	/**
	 * Provide custom configuration options when creating a new {@link AccountIntegration}
	 * @return a List of OptionType
	 */
	@Override
	List<OptionType> getOptionTypes() {
		OptionType option1 = new OptionType(
			name: 'Option 1',
			code: 'generic.option.one',
			fieldName: 'One',
			displayOrder: 0,
			fieldLabel: 'Option 1',
			required: true,
			inputType: OptionType.InputType.TEXT
		)

		return [option1]
	}

	/**
	 * Integration details provided to your rendering engine
	 * @param integration details of an Instance
	 * @return result of rendering a template
	 */
	@Override
	HTMLResponse renderTemplate(AccountIntegration integration) {
		ViewModel<AccountIntegration> model = new ViewModel<>()
		model.object = integration
		getRenderer().renderTemplate("hbs/uiTestShow", model)
	}

	@Override
	String getCode() {
		return PROVIDER_CODE
	}

	@Override
	String getName() {
		return PROVIDER_NAME
	}

	@Override
	MorpheusContext getMorpheus() {
		return this.@context
	}

	@Override
	Plugin getPlugin() {
		return this.@plugin
	}
}
