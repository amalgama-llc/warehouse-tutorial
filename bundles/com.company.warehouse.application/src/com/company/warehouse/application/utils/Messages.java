package com.company.warehouse.application.utils;


import org.eclipse.e4.core.services.nls.Message;
import org.eclipse.e4.core.services.nls.Message.ReferenceType;


@Message (contributionURI="platform:/plugin/com.company.warehouse.application/OSGI-INF/l10n", referenceType=ReferenceType.NONE)
public class Messages {
	
	public String APP_VERSION;
	
	public String VALIDATION__error_column_not_filled;
	public String VALIDATION__error_cannot_be_empty;
	public String VALIDATION__error_begin_date_after_end_date;
	public String VALIDATION__error_source_equal_dest;
	public String VALIDATION__error_non_negative;
	public String VALIDATION__error_positive_number;
	public String VALIDATION__error_latitude;
	public String VALIDATION__error_longitude;
	
	public String obj_ARC;
	public String obj_ARC_col_ID;
	public String obj_ARC_col_ID_excel;
	public String obj_ARC_col_NAME;
	public String obj_ARC_col_NAME_excel;
	public String obj_ARC_col_SOURCE;
	public String obj_ARC_col_SOURCE_excel;
	public String obj_ARC_col_DEST;
	public String obj_ARC_col_DEST_excel;

	
	public String obj_NODE;
	public String obj_NODE_col_ID;
	public String obj_NODE_col_ID_excel;
	public String obj_NODE_col_NAME;
	public String obj_NODE_col_NAME_excel;
	public String obj_NODE_col_Y;
	public String obj_NODE_col_Y_excel;
	public String obj_NODE_col_X;
	public String obj_NODE_col_X_excel;
	
	public String obj_POINT;
	public String obj_POINT_col_ARC;
	public String obj_POINT_col_ARC_excel;
	public String obj_POINT_col_X;
	public String obj_POINT_col_X_excel;
	public String obj_POINT_col_Y;
	public String obj_POINT_col_Y_excel;
	
	public String obj_SCENARIO;
	public String obj_SCENARIO_col_BEGIN_DATE;
	public String obj_SCENARIO_col_BEGIN_DATE_excel;
	public String obj_SCENARIO_col_END_DATE;
	public String obj_SCENARIO_col_END_DATE_excel;
	public String obj_SCENARIO_col_NAME;
	public String obj_SCENARIO_col_NAME_excel;
	
	public String title_resource_errors_view_dialog;
	public String title_exit;
	public String title_error;
	public String title_open_scenario;
	public String title_save_scenario;
	public String title_check_data;
	
	public String message_create_new_scenario;
	public String message_resource_errors_view_dialog;
	public String message_save_old_scenario;
	public String message_error_check_filepath;
	public String message_scenario_saving_error;
	public String message_check_data_error;
	public String message_check_data_ok;
	public String message_error_begin_date_after_end_date;
	public String message_about;
	
	public String label_set_language;
	public String label_language_set_to;
	public String label_unable_to_save_language_file;
	public String label_restart;
	public String label_begin_date;
	public String label_end_date;
	public String label_memory_usage;
	public String label_of;
	public String label_mb;
	public String label_error;
	public String label_warning;
	
	public String error_type_12;
	public String error_type_18;
	public String object_scenario;

	public String tree_element_scenario;
	public String tree_element_arcs;
	public String tree_element_nodes;
	public String tree_element_agents;
	public String tree_element_network;

	public String tab_general;
	public String tab_bendpoint;

	public String column_description;
	public String column_object;
	public String column_agent;
	public String column_agent_id;
	public String column_agent_name;
	public String column_agent_included;
	public String column_agent_base_position;
	public String column_agent_velocity;
	
	public String object_for_select_dialog_node_source;
	public String object_for_select_dialog_node_dest;
	public String object_for_select_dialog_base_position;
	
	public String toolbar_map;
	public String toolbar_centering;
	public String toolbar_update;
	
	public String SIMULATION_STATUS_MODELLED_DURATION;
	public String SIMULATION_STATS_obj_INDICATOR_col_NAME;
	public String SIMULATION_STATS_obj_INDICATOR_col_VALUE;
	public String INDICATOR_obj_UNKNOWN;
	public String INDICATOR_ARCS;
	public String INDICATOR_NODES;
	
	public String button_simulation;
	public String button_planning;
	public String button_editor;
	public String button_reset;
	public String button_add;
	public String button_remove;
	public String button_copy;
	
}

