package org.kuali.rice.kew.util;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kuali.rice.core.util.RiceConstants;

public class KEWConstants {

    private static final long serialVersionUID = -3897059085433394648L;

    /**
     * Node state key under which rule selector can be specified on a per-nodeinstance basis
     */
    public static final String RULE_SELECTOR_NODE_STATE_KEY = "__RULE_SELECTOR__";
    /**
     * Node state key under which rule nme can be specified on a per-nodeinstance basis
     */
    public static final String RULE_NAME_NODE_STATE_KEY = "__RULE_NAME__";

    public static final String KEW_MESSAGING_ENTITY = "KEW";

    public static final String MESSAGING_MEMORY = "memory";

    public static final String CORE_WORKFLOW_DATASOURCE = "enWorkflowDataSource";
    // not strictly necessary to specify if connection descriptor is configured as default
    public static final String CORE_WORKFLOW_DATASOURCE_JCD_ALIAS = CORE_WORKFLOW_DATASOURCE;

    public static final String PLUGIN_HOT_DEPLOY_MAX_WAIT_TIME = "Plugin.hotDeploy.maxWaitTime";
    public static final String PLUGIN_HOT_DEPLOY_SAFE_TIME = "Plugin.hotDeploy.safeTime";

    public static final String BOOTSTRAP_SPRING_FILE = "kew.bootstrap.spring.file";

    public static final String KEW_URL_HOST = "kew.url.host";
    public static final String HTTP_SERVICE_PORT = "http.service.port";

    public static final String RELOAD_ACTION_LIST = "RELOAD_ACTION_LIST";
    public static final String DELEGATION_WIZARD = "delegationWizard";
    public static final String PERFORM_REALTIME_DOCUMENT_UPGRADE = "Document.PerformRealtimeUpgrade";
    //checkRouteLogAuthentication

    public static final long DEFAULT_CACHE_REQUEUE_WAIT_TIME = 5000;

    public static final int DEFAULT_THREAD_POOL_SIZE = 5;

    public static final String STANDARD_DOC_SEARCH_GENERATOR_CLASS_CONFIG_PARM = "documentSearch.generator.class.name";
    public static final String STANDARD_DOC_SEARCH_RESULT_PROCESSOR_CLASS_CONFIG_PARM = "documentSearch.resultProcessor.class.name";

    public static final String DELEGATE_CHANGE_AR_GENERATION_KEY = "DelegateRuleChange.IsGenerateActionRequests";

    public static final String WORKGROUP_ROUTE_LOG_POPUP_KEY = "Workgroup.IsRouteLogPopup";
    public static final String WORKGROUP_ROUTE_LOG_POPUP_VALUE = "true";

    public static final String WORKGROUP_SEARCH_INSTRUCTION_KEY = "Workgroup.Search.Instruction";
    public static final String SUB_ACCOUNT_SEARCH_INSTRUCTION_KEY = "SubAccount.Search.Instruction";
    public static final String USER_SEARCH_INSTRUCTION_KEY = "User.Search.Instruction";
    public static final String WORKGROUP_CREATE_NEW_INSTRUCTION_KEY = "Workgroup.CreateNew.Instruction";
    public static final String USER_CREATE_NEW_INSTRUCTION_KEY = "User.CreateNew.Instruction";

    public static final String ACTION_LIST_ATTRIBUTE_CLASS_PROPERTY = "customActionListAttributeClassName";
    public static final String EMAIL_ATTRIBUTE_CLASS_PROPERTY = "customEmailAttributeClassName";
    public static final String NOTE_ATTRIBUTE_CLASS_PROPERTY = "customNoteAttributeClassName";

    // Delegation Constants
    public static final String DELEGATION_NONE = null;
    public static final String DELEGATION_PRIMARY = "P";
    public static final String DELEGATION_SECONDARY = "S";

    public static final String FLEX_RM_NAME = "FRM";
    public static final String DOC_HANDLER_REDIRECT_PAGE = "DocHandler.do";
    public static final String DOCUMENT_ROUTING_REPORT_PAGE = "RoutingReport.do";
    public static final String FEEDBACK_URL = "feedback.do";

    // Routing Report constants
    public static final String DOCUMENT_TYPE_NAME_ATTRIBUTE_NAME = "documentTypeParam";
    public static final String INITIATOR_ID_ATTRIBUTE_NAME = "initiatorPrincipalId";
    public static final String DOCUMENT_CONTENT_ATTRIBUTE_NAME = "documentContent";
    public static final String RETURN_URL_ATTRIBUTE_NAME = "backUrl";
    public static final String DISPLAY_CLOSE_BUTTON_ATTRIBUTE_NAME = "showCloseButton";
    public static final String DISPLAY_CLOSE_BUTTON_TRUE_VALUE = "showCloseButton";

    public static final String WORKFLOW_SUPER_USER_WORKGROUP_NAME = "WorkflowAdmin";

    public static final String DAILY_UNIT = "Daily";
    public static final String WEEKLY_UNIT = "Weekly";
    public static final String MONTHLY_UNIT = "Monthly";
    public static final String YEARLY_UNIT = "Yearly";

    public static final String USER_SESSION_KEY = "kewUserSession";
    public static final String SUDS_DATASOURCE = "SUDS";
    public static final String APP_CODE = "en";
    public static final String PROD_DEPLOYMENT_CODE = "prd";

    public static final String YES_LABEL = "Yes";
    public static final String NO_LABEL = "No";
    public static final String INHERITED_CD = "I";
    public static final String INHERITED_LABEL = "Inherited";

    public static final String DISAPPROVE_POLICY = "DISAPPROVE";
    public static final String DISAPPROVE_POLICY_CANCEL_CD = "C";
    public static final String DEFAULT_APPROVE_POLICY = "DEFAULT_APPROVE";
    public static final String PREAPPROVE_POLICY = "PRE_APPROVE";
    public static final String INITIATOR_MUST_ROUTE_POLICY = "INITIATOR_MUST_ROUTE";
    public static final String INITIATOR_MUST_SAVE_POLICY = "INITIATOR_MUST_SAVE";
    public static final String INITIATOR_MUST_CANCEL_POLICY = "INITIATOR_MUST_CANCEL";
    public static final String INITIATOR_MUST_BLANKET_APPROVE_POLICY = "INITIATOR_MUST_BLANKET_APPROVE";
    public static final String USE_KEW_SUPERUSER_DOCHANDLER = "USE_KEW_SUPERUSER_DOCHANDLER";
    public static final String SEND_NOTIFICATION_ON_SU_APPROVE_POLICY = "SEND_NOTIFICATION_ON_SU_APPROVE";

    public static final String DOCUMENT_TYPE_BLANKET_APPROVE_POLICY_NONE = "none";
    public static final String DOCUMENT_TYPE_BLANKET_APPROVE_POLICY_ANY = "any";

    //determines if route log will show the look into the future link
    public static final String LOOK_INTO_FUTURE_POLICY = "LOOK_FUTURE";
    public static final String SUPPORTS_QUICK_INITIATE_POLICY = "SUPPORTS_QUICK_INITIATE";
    public static final String NOTIFY_ON_SAVE_POLICY = "NOTIFY_ON_SAVE";

    public static final String SUPER_USER_CANCEL="SU_CANCEL";
    public static final String SUPER_USER_APPROVE="SU_APPROVE";
    public static final String SUPER_USER_DISAPPROVE="SU_DISAPPROVE";
    public static final String SUPER_USER_ROUTE_LEVEL_APPROVE="SU_ROUTE_LEVEL_APPROVE";
    public static final String SUPER_USER_ACTION_REQUEST_APPROVE="SU_ACTION_REQUEST_APPROVE";
    public static final String SUPER_USER_RETURN_TO_PREVIOUS_ROUTE_LEVEL="SU_RETURN_TO_PREVIOUS_ROUTE_LEVEL";

    /* email notification for action requests left in action list */
    public static final String EMAIL_RMNDR_KEY = "EMAIL_NOTIFICATION";
    public static final String EMAIL_RMNDR_NO_VAL = "no";
    public static final String EMAIL_RMNDR_DAY_VAL = "daily";
    public static final String EMAIL_RMNDR_WEEK_VAL = "weekly";
    public static final String EMAIL_RMNDR_IMMEDIATE = "immediate";
    public static final String EMAIL_LAST_RMNDR_KEY = "EMAIL_LST_RMNDR_DATE_KEY";
    public static final String EMAIL_STYLESHEET_NAME = "kew.email.style";
    /* end email notification constants */

    public static final String PREFERENCES_YES_VAL = "yes";
    public static final String PREFERENCES_NO_VAL = "no";

    public static final String ACTION_LIST_ALL_REQUESTS = "all";
    public static final String ACTION_LIST_DELEGATED_REQUESTS = "delegated";
    public static final String ACTION_LIST_NONDELEGATED_REQUESTS = "nondelegated";
    public static final String DELEGATORS_ON_FILTER_PAGE = "Secondary Delegators only on Filter Page";
    public static final String DELEGATORS_ON_ACTION_LIST_PAGE = "Secondary Delegators on Action List Page";

    public static final Map ACTION_LIST_CONTENT;
    static {
        ACTION_LIST_CONTENT = new HashMap();
        ACTION_LIST_CONTENT.put(KEWConstants.ACTION_LIST_ALL_REQUESTS, "All Requests");
        ACTION_LIST_CONTENT.put(KEWConstants.ACTION_LIST_NONDELEGATED_REQUESTS, "No Delegations");
        ACTION_LIST_CONTENT.put(KEWConstants.ACTION_LIST_DELEGATED_REQUESTS, "Delegations Only");
    }

    public static final String ALL_CODE = "All";
    public static final String NO_FILTERING = "No Filtering";
    public static final String DELEGATION_DEFAULT = "Choose Secondary Delegation";
    public static final String PRIMARY_DELEGATION_DEFAULT = "Choose Primary Delegate";

    public static final String ACTIVE_CD = "Y";
    public static final String ACTIVE_LABEL = "ACTIVE";
    public static final String ACTIVE_LABEL_LOWER = "Active";

    public static final String INACTIVE_CD = "N";
    public static final String INACTIVE_LABEL = "INACTIVE";
    public static final String INACTIVE_LABEL_LOWER = "Inactive";

    public static final String TRUE_CD = "1";
    public static final String TRUE = "T";

    public static final String FALSE_CD = "0";
    public static final String FALSE = "F";

    /** Value for UNAUTHENTICATED when comparing to the principal value */
    public static final String UNAUTHENTICATED = "UNAUTHENTICATED";
    public static final int DEFAULT_RETRY_TIME = 1800;

    public static final int TITLE_MAX_LENGTH = 255;

    public static final Date CURRENT_DATE = new Date(-7);
    public static final Map DOCUMENT_STATUSES;

    static {
        /*
         * see values in RouteHeader inner class; this HashMap is the definitive list used for the Document Route Statuses in ActionList preferences
         */
        DOCUMENT_STATUSES = new HashMap();
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_APPROVED_CD, KEWConstants.ROUTE_HEADER_APPROVED_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_CANCEL_CD, KEWConstants.ROUTE_HEADER_CANCEL_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_CANCEL_DISAPPROVE_CD, KEWConstants.ROUTE_HEADER_CANCEL_DISAPPROVE_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_DISAPPROVED_CD, KEWConstants.ROUTE_HEADER_DISAPPROVED_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_ENROUTE_CD, KEWConstants.ROUTE_HEADER_ENROUTE_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_EXCEPTION_CD, KEWConstants.ROUTE_HEADER_EXCEPTION_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_FINAL_CD, KEWConstants.ROUTE_HEADER_FINAL_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_INITIATED_CD, KEWConstants.ROUTE_HEADER_INITIATED_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_PROCESSED_CD, KEWConstants.ROUTE_HEADER_PROCESSED_LABEL);
        DOCUMENT_STATUSES.put(KEWConstants.ROUTE_HEADER_SAVED_CD, KEWConstants.ROUTE_HEADER_SAVED_LABEL);
    }

    // below must be negative to be 30 days in the past... positive number will push date into future
    public static final Integer DOCUMENT_SEARCH_DOC_TITLE_CREATE_DATE_DAYS_AGO = new Integer(-30);
    public static final Integer DOCUMENT_SEARCH_NO_CRITERIA_CREATE_DATE_DAYS_AGO = new Integer(0);

    public static final Map ACTION_LIST_COLOR_PALETTE;
    static {
        ACTION_LIST_COLOR_PALETTE = new HashMap();
        ACTION_LIST_COLOR_PALETTE.put("white", "#FFFFFF");
        ACTION_LIST_COLOR_PALETTE.put("pink", "#FFDDDE");
        ACTION_LIST_COLOR_PALETTE.put("orange", "#FFCC99");
        ACTION_LIST_COLOR_PALETTE.put("red", "#D99394");
        ACTION_LIST_COLOR_PALETTE.put("yellow", "#FFFF99");
        ACTION_LIST_COLOR_PALETTE.put("green", "#D4FF94");
        ACTION_LIST_COLOR_PALETTE.put("blue", "#BDD8F4");
        ACTION_LIST_COLOR_PALETTE.put("aqua", "#7FFFDF");
        ACTION_LIST_COLOR_PALETTE.put("slate", "#BDDABD");
        ACTION_LIST_COLOR_PALETTE.put("purple", "#DFCAFA");
        ACTION_LIST_COLOR_PALETTE.put("tan", "#E5E5B7");
    }

    public static final String HEADER_TAG = "ROUTE_HEADER";
    public static final String DOCTYPE_TAG = "DOCTYPE";
    public static final String DOC_TYPE_GROUP_DOCUMENT_SHORT_NAME = "EDENSERVICE-DOCS.DocumentTypeGroup";
    public static final String DOC_TYPE_DOCUMENT_SHORT_NAME = "EDENSERVICE-DOCS.DocumentType";
    public static final String WORKFLOW_DOCUMENT_ROOT = "EDENSERVICE-DOCS";

    // order of these is important since this forms a priority according to the codes index into the string
    public static final String REQUEST_CODES = KEWConstants.ACTION_REQUEST_FYI_REQ + KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ + KEWConstants.ACTION_REQUEST_APPROVE_REQ + KEWConstants.ACTION_REQUEST_COMPLETE_REQ;
    public static final String ACTION_CODES = KEWConstants.ACTION_TAKEN_ACKNOWLEDGED_CD + KEWConstants.ACTION_TAKEN_APPROVED_CD + KEWConstants.ACTION_TAKEN_COMPLETED_CD + KEWConstants.ACTION_TAKEN_ROUTED;
    public static final String ROUTE_MANAGER_DRIVER_WRKGRP = "RouteManagerDriverCommandGroup";
    public static final int ROUTE_HEADER_INITIAL_ROUTE_LEVEL = 0;
    /** The initial state of a document. Only state in which a delete is allowed. */
    public static final String ROUTE_HEADER_INITIATED_CD = "I";
    public static final String ROUTE_HEADER_INITIATED_LABEL = "INITIATED";

    /**
     * Default pre-approval policy code. By default pre-approval action requests are not allowed.
     */
    public static final boolean ROUTE_HEADER_DEFAULT_PRE_APPROVAL_POLICY = false;
    /**
     * Default code for the default auto approval policy code. If a document has not approve action requests and has gone through the route template, then by default the document is auto approved.
     */
    public static final boolean ROUTE_HEADER_DEFAULT_AUTO_APPROVAL_POLICY = true;
    public static final String ROUTE_HEADER_DEFAULT_DISAPPROVE_POLICY_LABEL = "DISAPPROVE-CANCEL";
    /** Default disapprove action policy code */
    public static final String ROUTE_HEADER_DEFAULT_DISAPPROVE_POLICY_CD = KEWConstants.ROUTE_HEADER_CANCEL_DISAPPROVE_CD;
    /** Document sent back to the last approver */
    public static final String ROUTE_HEADER_LAST_APPROVER_DISAPPROVE_CD = "A";
    /**
     * Return the document to the initiator with complete request when the document is disapproved. Document must be explicitly canceled or it will remain en_route under this policy.
     */
    public static final String ROUTE_HEADER_RETURN_INIATOR_DISAPPROVE_CD = "I";
    public static final String ROUTE_HEADER_CANCEL_DISAPPROVE_LABEL = "DISAPPROVE-CANCEL";
    /** When document disapproved, take same effect as a cancel but with a different action code */
    public static final String ROUTE_HEADER_CANCEL_DISAPPROVE_CD = "C";
    public static final String ROUTE_HEADER_DISAPPROVED_DEFAULT_COLOR = "yellow";
    public static final String ROUTE_HEADER_DISAPPROVED_LABEL = "DISAPPROVED";
    /** Document has been disapproved */
    public static final String ROUTE_HEADER_DISAPPROVED_CD = "D";
    public static final String ROUTE_HEADER_PROCESSED_LABEL = "PROCESSED";
    /** Document has been processed by the post processor */
    public static final String ROUTE_HEADER_PROCESSED_CD = "P";
    public static final String ROUTE_HEADER_EXCEPTION_DEFAULT_COLOR = "red";
    public static final String ROUTE_HEADER_EXCEPTION_LABEL = "EXCEPTION";
    /** Document has had an exception in routing and needs to be processed */
    public static final String ROUTE_HEADER_EXCEPTION_CD = "E";
    public static final String ROUTE_HEADER_CANCEL_LABEL = "CANCELED";
    /** Document has been canceled and no further action should be taken on it. */
    public static final String ROUTE_HEADER_CANCEL_CD = "X";
    public static final String ROUTE_HEADER_APPROVED_LABEL = "APPROVED";
    /** Document has been approved and is awaiting post processing */
    public static final String ROUTE_HEADER_APPROVED_CD = "A";
    public static final String ROUTE_HEADER_FINAL_LABEL = "FINAL";
    /** Document has finalized and no changes are allowed to take place to it. */
    public static final String ROUTE_HEADER_FINAL_CD = "F";
    public static final String ROUTE_HEADER_SAVED_LABEL = "SAVED";
    /** The document has been saved, but has not started to route. */
    public static final String ROUTE_HEADER_SAVED_CD = "S";
    public static final String ROUTE_HEADER_ENROUTE_LABEL = "ENROUTE";
    /** The document is currently being routed. */
    public static final String ROUTE_HEADER_ENROUTE_CD = "R";

    /** Actions Taken Constants **/
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_ACKNOWLEDGED_CD = "k";
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_ACKNOWLEDGED = "SUPER USER ACTION REQUEST ACKNOWLEDGED";
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_FYI_CD = "f";
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_FYI = "SUPER USER ACTION REQUEST FYI";
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_COMPLETED_CD = "m";
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_COMPLETED = "SUPER USER ACTION REQUEST COMPLETED";
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_APPROVED_CD = "v";
    public static final String ACTION_TAKEN_SU_ACTION_REQUEST_APPROVED = "SUPER USER ACTION REQUEST APPROVED";
    public static final String ACTION_TAKEN_SU_ROUTE_LEVEL_APPROVED_CD = "r";
    public static final String ACTION_TAKEN_SU_ROUTE_LEVEL_APPROVED = "SUPER USER ROUTE LEVEL APPROVED";
    public static final String ACTION_TAKEN_SU_RETURNED_TO_PREVIOUS_CD = "z";
    public static final String ACTION_TAKEN_SU_RETURNED_TO_PREVIOUS = "SUPER USER RETURNED TO PREVIOUS ROUTE LEVEL";
    public static final String ACTION_TAKEN_SU_DISAPPROVED_CD = "d";
    public static final String ACTION_TAKEN_SU_DISAPPROVED = "SUPER USER DISAPPROVED";
    public static final String ACTION_TAKEN_SU_CANCELED_CD = "c";
    public static final String ACTION_TAKEN_SU_CANCELED = "SUPER USER CANCELED";
    public static final String ACTION_TAKEN_SU_APPROVED_CD = "a";
    public static final String ACTION_TAKEN_SU_APPROVED = "SUPER USER APPROVED";
    public static final String ACTION_TAKEN_BLANKET_APPROVE_CD = "B";
    public static final String ACTION_TAKEN_BLANKET_APPROVE = "BLANKET APPROVED";
    public static final String ACTION_TAKEN_FYI_CD = "F";
    public static final String ACTION_TAKEN_FYI = "FYI";
    public static final String ACTION_TAKEN_ADHOC_CD = "H";
    /** User has generated an action request to another user */
    public static final String ACTION_TAKEN_ADHOC = "ADHOC ROUTED";
    public static final String ACTION_TAKEN_ADHOC_REVOKED_CD = "V";
    /** AdHoc Request has been revoked */
    public static final String ACTION_TAKEN_ADHOC_REVOKED = "ADHOC REVOKED";
    public static final String ACTION_TAKEN_SAVED_CD = "S";
    /** Document has been saved by the user for later work */
    public static final String ACTION_TAKEN_SAVED = "SAVED";
    public static final String ACTION_TAKEN_CANCELED_CD = "X";
    /** Document has been canceled. */
    public static final String ACTION_TAKEN_CANCELED = "CANCELED";
    public static final String ACTION_TAKEN_DENIED_CD = "D";
    /** Document has been denied. */
    public static final String ACTION_TAKEN_DENIED = "DISAPPROVED";
    public static final String ACTION_TAKEN_ACKNOWLEDGED_CD = "K";
    /** Document has been opened by the designated recipient. */
    public static final String ACTION_TAKEN_ACKNOWLEDGED = "ACKNOWLEDGED";
    public static final String ACTION_TAKEN_COMPLETED_CD = "C";
    /** Document has been completed as requested. */
    public static final String ACTION_TAKEN_COMPLETED = "COMPLETED";
    public static final String ACTION_TAKEN_ROUTED_CD = "O";
    /** Document has been completed as requested. */
    public static final String ACTION_TAKEN_ROUTED = "ROUTED";
    public static final String ACTION_TAKEN_APPROVED_CD = "A";
    /** The document has been approved. */
    public static final String ACTION_TAKEN_APPROVED = "APPROVED";
    /** The document is being returned to a previous routelevel **/
    public static final String ACTION_TAKEN_RETURNED_TO_PREVIOUS_CD = "Z";
    public static final String ACTION_TAKEN_RETURNED_TO_PREVIOUS = "RETURNED TO PREVIOUS ROUTE LEVEL";
    /** The document has non-routed activity against it that is recorded in the route log **/
    public static final String ACTION_TAKEN_LOG_DOCUMENT_ACTION_CD = "R";
    public static final String ACTION_TAKEN_LOG_DOCUMENT_ACTION = "LOG MESSAGE";
    /** The document is routed to a workgroup and a user in the workgroup wants to take authority from the workgroup **/
    public static final String ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY_CD = "w";
    public static final String ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY = "WORKGROUP AUTHORITY TAKEN";
    /** The person who took workgroup authority is releasing it **/
    public static final String ACTION_TAKEN_RELEASE_WORKGROUP_AUTHORITY_CD = "y";
    public static final String ACTION_TAKEN_RELEASE_WORKGROUP_AUTHORITY = "WORKGROUP AUTHORITY RELEASED";
    /** The document is moved **/
    public static final String ACTION_TAKEN_MOVE_CD = "M";
    public static final String ACTION_TAKEN_MOVE = "MOVED";

    /** Route Level type constants FlexRM or Route Module **/
    public static final String ROUTE_LEVEL_FLEX_RM = "FR";
    public static final String ROUTE_LEVEL_ROUTE_MODULE = "RM";
    public static final String ROUTE_LEVEL_METHOD_NAME_ROUTE_MODULE = "Route Module";
    public static final String ROUTE_LEVEL_METHOD_NAME_FLEX_RM = "Rule Template";
    /** No route module available for this route level * */
    public static final String ROUTE_LEVEL_NO_ROUTE_MODULE = "NONE";
    /**
     * The route level value for the AdHoc route level. AdHoc like Exception route level does not have a route module and is processed directly be the engine.
     */
    public static final int ADHOC_ROUTE_LEVEL = 0;
    public static final String ADHOC_ROUTE_LEVEL_NAME = "Adhoc Routing";
    public static final String PARALLEL_ADHOC_ROUTE_MODULE_NAME = "org.kuali.rice.kew.routemodule.ParallelAdHocRouteModule";

    /**
     * The route level value for the Exception route level. The Exception route level does not have a route module and the core engine processes these requests since they have special rules, such as an exception request can not itself throw an exception request.
     */
    public static final int EXCEPTION_ROUTE_LEVEL = -1;
    public static final String EXCEPTION_ROUTE_MODULE_NAME = "org.kuali.rice.kew.routemodule.ExceptionRouteModule";
    public static final String EXCEPTION_ROUTE_LEVEL_NAME = "Exception Routing";
    public static final int INVALID_ROUTE_LEVEL = -2;

    /** Routing should process the associated ActionRequests in sequence */
    public static final String ROUTE_LEVEL_SEQUENCE = "S";
    public static final String ROUTE_LEVEL_SEQUENTIAL_NAME = "Sequential";
    public static final String ROUTE_LEVEL_SEQUENCE_LABEL = "SEQUENCE";

    /** Routing should process the associated ActionRequests in parallel */
    public static final String ROUTE_LEVEL_PARALLEL = "P";
    public static final String ROUTE_LEVEL_PARALLEL_NAME = "Parallel";
    public static final String ROUTE_LEVEL_PARALLEL_LABEL = "PARALLEL";

    public static final String APPROVE_POLICY_FIRST_APPROVE = "F";
    public static final String APPROVE_POLICY_ALL_APPROVE = "A";

    public static final String APPROVE_POLICY_ALL_APPROVE_LABEL = "ALL";
    public static final String APPROVE_POLICY_FIRST_APPROVE_LABEL = "FIRST";

    public static final boolean ACTION_REQUEST_IGNORE_PREV_ACTION = true;
    public static final boolean ACTION_REQUEST_PREV_ACTION_AWARE = false;
    /** Action Request is for a workgroup. */
    public static final String ACTION_REQUEST_GROUP_RECIPIENT_CD = "W";
    /** Action Request is for a user only */
    public static final String ACTION_REQUEST_USER_RECIPIENT_CD = "U";
    /** Action Request is for a role */
    public static final String ACTION_REQUEST_ROLE_RECIPIENT_CD = "R";
    /** Priority used if no priority is specified */
    public static final int ACTION_REQUEST_DEFAULT_PRIORITY = 1;

    /** Values for Rule template options   */
    public static final String RULE_INSTRUCTIONS_CD = "I";
    public static final String ACTION_REQUEST_DEFAULT_CD = "D";


    /**
     * Priority level that indicates an ad hoc request. If the priority is above this value, then the request is considered an ad hoc request and is processed accordingly. This is used most often when an ad hoc request must follow the end of a route level. For example, if a user wants the ad hoc to happen after all requests at route level 3 have been processed, but before any requests at route level 4. This is also the upper, exclusive bound for normal request priorities.
     */
    public static final int ACTION_REQUEST_ADHOC_PRIORITY = 1000;
    public static final String ACTION_REQUEST_DONE_STATE_LABEL = "DONE";
    /** code to indicate the request has been satisfied */
    public static final String ACTION_REQUEST_DONE_STATE = "D";
    public static final String ACTION_REQUEST_ACTIVATED_LABEL = "ACTIVATED";
    /** Code to indicate the request is currently active */
    public static final String ACTION_REQUEST_ACTIVATED = "A";
    public static final String ACTION_REQUEST_INITIALIZED_LABEL = "INITIALIZED";
    /** Code to indicate the request has not been activated */
    public static final String ACTION_REQUEST_INITIALIZED = "I";
    public static final String ACTION_REQUEST_CANCEL_REQ_LABEL = "CANCEL";
    /**
     * Requested action is to Cancel document
     */
    public static final String ACTION_REQUEST_CANCEL_REQ = "X";
    public static final String ACTION_REQUEST_ACKNOWLEDGE_REQ_LABEL = "ACKNOWLEDGE";
    /**
     * Requested action is ACKKNOWLEDGE. This action does not hold up routing, but action request will not be marked DONE until an ACKNOWLEDGE actiontaken is recorded.
     */
    public static final String ACTION_REQUEST_ACKNOWLEDGE_REQ = "K";
    public static final String ACTION_REQUEST_FYI_REQ_LABEL = "FYI";
    /**
     * The action is an FYI notification only. This action request is marked DONE as soon as it is activated.
     */
    public static final String ACTION_REQUEST_FYI_REQ = "F";
    public static final String ACTION_REQUEST_APPROVE_REQ_LABEL = "APPROVE";
    /** Requested action is to approve the document. */
    public static final String ACTION_REQUEST_APPROVE_REQ = "A";
    public static final String ACTION_REQUEST_COMPLETE_REQ_LABEL = "COMPLETE";
    /** Requested action is to complete the document, however that is defined by the application. */
    public static final String ACTION_REQUEST_COMPLETE_REQ = "C";
    public static final String ACTION_REQUEST_DISAPPROVE_LABEL = "DISAPPROVE";

    public static final String ACTION_REQUEST_TO_BE_GENERATED = "-1";
    /** Largest value allowed for a workgroupID. Any value larger is assumed to be a sequenceId of a user */
    public static final int WORKGROUP_MAX_WORKGROUP_ID = 100000000;
    /** Last approval taken applies to entire request */
    //public static final String WORKGROUP_LAST_APPROVAL = "L";
    /** First action taken applies to entire request */
    //public static final String WORKGROUP_FIRST_ACTION = "F";
    /** Personal workgroup code */
    //public static final String WORKGROUP_PERSONAL = "P";

    public static final Map ACTION_REQUEST_CODES = new HashMap();
    static {
    	ACTION_REQUEST_CODES.put(ACTION_REQUEST_COMPLETE_REQ, ACTION_REQUEST_COMPLETE_REQ_LABEL);
    	ACTION_REQUEST_CODES.put(ACTION_REQUEST_APPROVE_REQ, ACTION_REQUEST_APPROVE_REQ_LABEL);
    	ACTION_REQUEST_CODES.put(ACTION_REQUEST_ACKNOWLEDGE_REQ, ACTION_REQUEST_ACKNOWLEDGE_REQ_LABEL);
    	ACTION_REQUEST_CODES.put(ACTION_REQUEST_FYI_REQ, ACTION_REQUEST_FYI_REQ_LABEL);
    }

    /** Route Queue Priorities */
    public static final Integer ROUTE_QUEUE_DEFAULT_PRIORITY = new Integer(5);
    public static final Integer ROUTE_QUEUE_BLANKET_APPROVE_PRIORITY = new Integer(4);
    public static final Integer ROUTE_QUEUE_RERESOLVE_PRIORITY = new Integer(10);
    public static final Integer ROUTE_QUEUE_REQUEUE_PRIORITY = new Integer(10);
    public static final Integer ROUTE_QUEUE_EMAIL_PRIORITY = new Integer(20);
    public static final Integer ROUTE_QUEUE_RULE_CACHE_PRIORITY = new Integer(0);
    public static final Integer ROUTE_QUEUE_EXCEPTION_PRIORITY = new Integer(21);
    public static final Integer ROUTE_QUEUE_UPGRADE_PRIORITY = new Integer(30);

    public static final String ROUTE_QUEUE_EXCEPTION = "E";
    public static final String ROUTE_QUEUE_ROUTING = "R";
    public static final String ROUTE_QUEUE_QUEUED = "Q";
    public static final String ROUTE_QUEUE_EXCEPTION_LABEL = "EXCEPTION";
    public static final String ROUTE_QUEUE_ROUTING_LABEL = "ROUTING";
    public static final String ROUTE_QUEUE_QUEUED_LABEL = "QUEUED";

    public static final String RULE_RESPONSIBILITY_WORKFLOW_ID = "F";
    public static final String RULE_RESPONSIBILITY_GROUP_ID = "G";
    public static final String RULE_RESPONSIBILITY_ROLE_ID = "R";

    public static final String DEFAULT_RULE_DOCUMENT_NAME = "EDENSERVICE-DOCS.RuleDocument";

    public static class Sorting {
        public static final String SORT_COLUMN_SEQUENCES = "columnSortSequences";
        public static final String SORT_SEQUENCE_ASC = "ASCENDING";
        public static final String SORT_SEQUENCE_DSC = "DESCENDING";
        public static final String SORT_DOC_ID = "docId";
        public static final String SORT_DOC_ID_NUMBER = "docIdNumber";
        public static final String SORT_RULE_ID = "ruleId";
        public static final String SORT_RULE_ID_NUMBER = "ruleIdNumber";
        public static final String SORT_DOC_TITLE = "docTitle";
        public static final String SORT_DOC_TYPE_ID = "docTypeId";
        public static final String SORT_DOC_TYPE_ID_NUMBER = "docTypeIdNumber";
        public static final String SORT_DOC_TYPE_NAME = "docTypeName";
        public static final String SORT_DOC_TYPE_FULL_NAME = "docTypeFullName";
        public static final String SORT_DOC_TYPE_LABEL = "docTypeLabel";
        public static final String SORT_ACTION_REQUESTED = "actionRequested";
        public static final String SORT_ACTION_REQUESTED_LABEL = "actionRequestedLabel";
        public static final String SORT_CHART = "chart";
        public static final String SORT_ORG = "org";
        public static final String SORT_REVIEWERS_ALL_NAMES = "reviewersAllNames";
        public static final String SORT_REVIEWER_EMPLY_ID = "reviewerEmplyId";
        public static final String SORT_REVIEWER_NETWORK_ID = "reviewerNetworkId";
        public static final String SORT_REVIEWER_FULL_NAME = "reviewerFullName";
        public static final String SORT_ACTIVE_IND = "activeIndicator";
        public static final String SORT_ACTIVE_IND_LABEL = "activeIndicatorLabel";
        public static final String SORT_DOC_TYPE_GROUP_ID = "docTypeGroupId";
        public static final String SORT_DOC_TYPE_GROUP_ID_NUMBER = "docTypeGroupIdNumber";
        public static final String SORT_DOC_TYPE_GROUP_NAME = "docTypeGroupName";
        public static final String SORT_DOC_TYPE_GROUP_FULL_NAME = "docTypeGroupFullName";
        public static final String SORT_DOC_TYPE_GROUP_PARENT_NAME = "docTypeGroupParentName";
        public static final String SORT_DOC_TYPE_GROUP_PARENT_FULL_NAME = "docTypeGroupParentFullName";
        public static final String SORT_DOC_TYPE_GROUP_LABEL = "docTypeGroupLabel";
        public static final String SORT_FISCAL_UPAA = "fiscalUpaa";
        public static final String SORT_FISCAL_UPAA_LABEL = "fiscalUpaaLabel";
        public static final String SORT_POSITION_TYPE = "positionType";
        public static final String SORT_POSITION_TYPE_LABEL = "positionTypeLabel";
        public static final String SORT_SALARY_PLAN = "salaryPlanSort";
        public static final String SORT_DELEGATES_ALL = "delegatesAll";
        public static final String SORT_DELEGATE_EMPLY_ID = "delegateEmplyId";
        public static final String SORT_DELEGATE_NETWORK_ID = "delegateNetworkId";
        public static final String SORT_DELEGATE_FULL_NAME = "delegateFullName";
        public static final String SORT_ROUTE_MODULE_NAME = "routeModuleName";
        public static final String SORT_WORKGROUP_ID = "workGroupId";
        public static final String SORT_WORKGROUP_ID_NUMBER = "workGroupIdNumber";
        public static final String SORT_WORKGROUP_NAME = "workGroupName";
        public static final String SORT_WORKGROUP_FULL_NAME = "workGroupFullName";
        public static final String SORT_USER_FULL_NAME = "userFullName";
        public static final String SORT_USER_NETWORK_ID = "userNetworkId";
        public static final String SORT_MONITOR_ID_NUMBER = "monitorId";
        public static final String SORT_MONITOR_DOC_TYPE = "monitorDocType";
        public static final String SORT_MONITOR_TYPE = "monitorType";
        public static final String SORT_MONITOR_TIMER = "monitorTimer";
        public static final String SORT_MONITOR_ACTION_TYPE = "monitorActionType";
    }

    public static final String WORKGROUP = "workgroup";
    public static final String PERSON = "person";
    public static final String ROLE = "role";

    public static final String DOC_HANDLER_RETURN_URL = "docHandlerReturnUrl";
    //document operation constants
    public static final String ADD = "add";
    public static final String NOOP = "noop";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String TIMESTAMP_DATE_FORMAT_PATTERN = "yyyy-mm-dd hh:mm:ss.fffffffff";
    public static final String TIMESTAMP_DATE_FORMAT_PATTERN2 = "MM/dd/yyyy hh:mm a";

    public static final Map ACTION_REQUEST_RECIPIENT_TYPE;
    static {
        ACTION_REQUEST_RECIPIENT_TYPE = new HashMap();
        ACTION_REQUEST_RECIPIENT_TYPE.put(KEWConstants.ACTION_REQUEST_GROUP_RECIPIENT_CD, KEWConstants.WORKGROUP);
        ACTION_REQUEST_RECIPIENT_TYPE.put(KEWConstants.ACTION_REQUEST_USER_RECIPIENT_CD, KEWConstants.PERSON);
        ACTION_REQUEST_RECIPIENT_TYPE.put(KEWConstants.ACTION_REQUEST_ROLE_RECIPIENT_CD, KEWConstants.ROLE);
    }

    public static final Map ACTION_REQUEST_CD;
    static{
        ACTION_REQUEST_CD = new HashMap();
        ACTION_REQUEST_CD.put(KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ, KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ_LABEL);
        ACTION_REQUEST_CD.put(KEWConstants.ACTION_REQUEST_APPROVE_REQ, KEWConstants.ACTION_REQUEST_APPROVE_REQ_LABEL);
        ACTION_REQUEST_CD.put(KEWConstants.ACTION_REQUEST_COMPLETE_REQ, KEWConstants.ACTION_REQUEST_COMPLETE_REQ_LABEL);
        ACTION_REQUEST_CD.put(KEWConstants.ACTION_REQUEST_FYI_REQ, KEWConstants.ACTION_REQUEST_FYI_REQ_LABEL);
    }

    public static final Map ACTION_REQUEST_STATUS;
    static {
        ACTION_REQUEST_STATUS = new HashMap();
        ACTION_REQUEST_STATUS.put(KEWConstants.ACTION_REQUEST_ACTIVATED, KEWConstants.ACTIVE_LABEL);
        ACTION_REQUEST_STATUS.put(KEWConstants.ACTION_REQUEST_INITIALIZED, KEWConstants.ACTION_REQUEST_INITIALIZED_LABEL);
        ACTION_REQUEST_STATUS.put(KEWConstants.ACTION_REQUEST_DONE_STATE, KEWConstants.ACTION_REQUEST_DONE_STATE_LABEL);
    }

    public static final Map ACTION_TAKEN_CD;
    static{
        ACTION_TAKEN_CD = new HashMap();
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_ACKNOWLEDGED_CD, KEWConstants.ACTION_TAKEN_ACKNOWLEDGED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_ADHOC_CD, KEWConstants.ACTION_TAKEN_ADHOC);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_ADHOC_REVOKED_CD, KEWConstants.ACTION_TAKEN_ADHOC_REVOKED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_APPROVED_CD, KEWConstants.ACTION_TAKEN_APPROVED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_BLANKET_APPROVE_CD, KEWConstants.ACTION_TAKEN_BLANKET_APPROVE);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_CANCELED_CD, KEWConstants.ACTION_TAKEN_CANCELED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_COMPLETED_CD, KEWConstants.ACTION_TAKEN_COMPLETED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_DENIED_CD, KEWConstants.ACTION_TAKEN_DENIED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_FYI_CD, KEWConstants.ACTION_TAKEN_FYI);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_SAVED_CD, KEWConstants.ACTION_TAKEN_SAVED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_RETURNED_TO_PREVIOUS_CD, KEWConstants.ACTION_TAKEN_RETURNED_TO_PREVIOUS);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_LOG_DOCUMENT_ACTION_CD, KEWConstants.ACTION_TAKEN_LOG_DOCUMENT_ACTION);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_SU_APPROVED_CD, KEWConstants.ACTION_TAKEN_SU_APPROVED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_SU_CANCELED_CD, KEWConstants.ACTION_TAKEN_SU_CANCELED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_SU_DISAPPROVED_CD, KEWConstants.ACTION_TAKEN_SU_DISAPPROVED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_SU_ROUTE_LEVEL_APPROVED_CD, KEWConstants.ACTION_TAKEN_SU_ROUTE_LEVEL_APPROVED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_APPROVED_CD, KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_APPROVED);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_SU_RETURNED_TO_PREVIOUS_CD, KEWConstants.ACTION_TAKEN_SU_RETURNED_TO_PREVIOUS);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY_CD, KEWConstants.ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY);
        ACTION_TAKEN_CD.put(KEWConstants.ACTION_TAKEN_MOVE_CD, KEWConstants.ACTION_TAKEN_MOVE);
    }

    public static final String DOCUMENT_CONTENT_ELEMENT = "documentContent";
    public static final String ATTRIBUTE_CONTENT_ELEMENT = "attributeContent";
    public static final String SEARCHABLE_CONTENT_ELEMENT = "searchableContent";
    public static final String APPLICATION_CONTENT_ELEMENT = "applicationContent";
    public static final String DEFAULT_DOCUMENT_CONTENT = "<"+DOCUMENT_CONTENT_ELEMENT+"/>";
    public static final String DEFAULT_DOCUMENT_CONTENT2 = "<"+DOCUMENT_CONTENT_ELEMENT+"></"+DOCUMENT_CONTENT_ELEMENT+">";

    public static final String DOCUMENT_CONTENT_VERSION_1 = "1";
    public static final String DOCUMENT_CONTENT_VERSION_2 = "2";

    public static final String XML_FILE_NOT_FOUND = "general.error.filenotfound";
    public static final String XML_PARSE_ERROR = "general.error.parsexml";



    public static final String RULE_ATTRIBUTE_TYPE = "RuleAttribute";
    public static final String SEARCHABLE_ATTRIBUTE_TYPE = "SearchableAttribute";
    public static final String RULE_XML_ATTRIBUTE_TYPE = "RuleXmlAttribute";
    public static final String SEARCHABLE_XML_ATTRIBUTE_TYPE = "SearchableXmlAttribute";
    public static final String EXTENSION_ATTRIBUTE_TYPE = "ExtensionAttribute";
    public static final String EMAIL_ATTRIBUTE_TYPE = "EmailAttribute";
    public static final String NOTE_ATTRIBUTE_TYPE = "NoteAttribute";
    public static final String ACTION_LIST_ATTRIBUTE_TYPE = "ActionListAttribute";
    public static final String RULE_VALIDATION_ATTRIBUTE_TYPE = "RuleValidationAttribute";
    public static final String SEARCH_CRITERIA_PROCESSOR_ATTRIBUTE_TYPE = "DocumentSearchCriteriaProcessorAttribute";
    public static final String SEARCH_GENERATOR_ATTRIBUTE_TYPE = "DocumentSearchGeneratorAttribute";
    public static final String SEARCH_RESULT_PROCESSOR_ATTRIBUTE_TYPE = "DocumentSearchResultProcessorAttribute";
    public static final String SEARCH_RESULT_XML_PROCESSOR_ATTRIBUTE_TYPE = "DocumentSearchXMLResultProcessorAttribute";
    public static final String DOCUMENT_SEARCH_SECURITY_FILTER_ATTRIBUTE_TYPE = "DocumentSearchSecurityFilterAttribute";
    public static final String QUALIFIER_RESOLVER_ATTRIBUTE_TYPE = "QualifierResolver";

    public static final String RULE_ATTRIBUTE_TYPE_LABEL = "Rule Attribute";
    public static final String SEARCHABLE_ATTRIBUTE_TYPE_LABEL = "Searchable Attribute";
    public static final String RULE_XML_ATTRIBUTE_TYPE_LABEL = "Rule Xml Attribute";
    public static final String SEARCHABLE_XML_ATTRIBUTE_TYPE_LABEL = "Searchable Xml Attribute";
    public static final String EXTENSION_ATTRIBUTE_TYPE_LABEL = "Extension Attribute";
    public static final String EMAIL_ATTRIBUTE_TYPE_LABEL = "Email Attribute";
    public static final String NOTE_ATTRIBUTE_TYPE_LABEL = "Note Attribute";
    public static final String ACTION_LIST_ATTRIBUTE_TYPE_LABEL = "Action List Attribute";
    public static final String RULE_VALIDATION_ATTRIBUTE_TYPE_LABEL = "Rule Validation Attribute";
    public static final String SEARCH_CRITERIA_PROCESSOR_ATTRIBUTE_TYPE_LABEL = "Document Search Criteria Processor Attribute";
    public static final String SEARCH_GENERATOR_ATTRIBUTE_TYPE_LABEL = "Document Search Generator Attribute";
    public static final String SEARCH_RESULT_PROCESSOR_ATTRIBUTE_TYPE_LABEL = "Document Search Result Processor Attribute";
    public static final String SEARCH_RESULT_XML_PROCESSOR_ATTRIBUTE_TYPE_LABEL = "Document Search Result Processor XML Attribute";
    public static final String DOCUMENT_SEARCH_SECURITY_FILTER_ATTRIBUTE_TYPE_LABEL = "Document Search Security Filter Attribute";
    public static final String QUALIFIER_RESOLVER_ATTRIBUTE_TYPE_LABEL = "Qualifier Resolver";

    public static final String[] RULE_ATTRIBUTE_TYPES = {
    	RULE_ATTRIBUTE_TYPE,
        SEARCHABLE_ATTRIBUTE_TYPE,
        RULE_XML_ATTRIBUTE_TYPE,
        SEARCHABLE_XML_ATTRIBUTE_TYPE,
        EXTENSION_ATTRIBUTE_TYPE,
        EMAIL_ATTRIBUTE_TYPE,
        NOTE_ATTRIBUTE_TYPE,
        ACTION_LIST_ATTRIBUTE_TYPE,
        RULE_VALIDATION_ATTRIBUTE_TYPE,
        SEARCH_CRITERIA_PROCESSOR_ATTRIBUTE_TYPE,
        SEARCH_GENERATOR_ATTRIBUTE_TYPE,
        SEARCH_RESULT_PROCESSOR_ATTRIBUTE_TYPE,
        SEARCH_RESULT_XML_PROCESSOR_ATTRIBUTE_TYPE,
        DOCUMENT_SEARCH_SECURITY_FILTER_ATTRIBUTE_TYPE,
        QUALIFIER_RESOLVER_ATTRIBUTE_TYPE
    };

    public static final Map<String, String> RULE_ATTRIBUTE_TYPE_MAP;
    static {
    	RULE_ATTRIBUTE_TYPE_MAP = new HashMap<String, String>();
    	RULE_ATTRIBUTE_TYPE_MAP.put(RULE_ATTRIBUTE_TYPE, RULE_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(SEARCHABLE_ATTRIBUTE_TYPE, SEARCHABLE_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(RULE_XML_ATTRIBUTE_TYPE, RULE_XML_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(SEARCHABLE_XML_ATTRIBUTE_TYPE, SEARCHABLE_XML_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(EXTENSION_ATTRIBUTE_TYPE, EXTENSION_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(EMAIL_ATTRIBUTE_TYPE, EMAIL_ATTRIBUTE_TYPE_LABEL);
       	RULE_ATTRIBUTE_TYPE_MAP.put(NOTE_ATTRIBUTE_TYPE, NOTE_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(ACTION_LIST_ATTRIBUTE_TYPE, ACTION_LIST_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(RULE_VALIDATION_ATTRIBUTE_TYPE, RULE_VALIDATION_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(SEARCH_CRITERIA_PROCESSOR_ATTRIBUTE_TYPE, SEARCH_CRITERIA_PROCESSOR_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(SEARCH_GENERATOR_ATTRIBUTE_TYPE, SEARCH_GENERATOR_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(SEARCH_RESULT_PROCESSOR_ATTRIBUTE_TYPE, SEARCH_RESULT_PROCESSOR_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(SEARCH_RESULT_XML_PROCESSOR_ATTRIBUTE_TYPE, SEARCH_RESULT_XML_PROCESSOR_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(DOCUMENT_SEARCH_SECURITY_FILTER_ATTRIBUTE_TYPE, DOCUMENT_SEARCH_SECURITY_FILTER_ATTRIBUTE_TYPE_LABEL);
        RULE_ATTRIBUTE_TYPE_MAP.put(QUALIFIER_RESOLVER_ATTRIBUTE_TYPE, QUALIFIER_RESOLVER_ATTRIBUTE_TYPE_LABEL);
    };

    public static final String DAILY = "daily";
    public static final String WEEKLY = "weekly";
    public static final Long EMAIL_QUEUE_ENTRY_ROUTE_HEADER_ID = new Long(-1);
    public static final int MAX_ANNOTATION_LENGTH = 2000;
    public static final String XML_FILE_PARSE_ERROR = "general.error.parsexml";


    public static final Long MACHINE_GENERATED_RESPONSIBILITY_ID = new Long(0);
    public static final Long ADHOC_REQUEST_RESPONSIBILITY_ID = new Long(-1);
    public static final Long EXCEPTION_REQUEST_RESPONSIBILITY_ID = new Long(-2);
    public static final Long SAVED_REQUEST_RESPONSIBILITY_ID = new Long(-3);

    public static final int DEFAULT_WEB_SERVICE_RETRY = 1;

    public static final String PRIMARY_PROCESS_NAME = "PRIMARY";
    public static final String PRIMARY_BRANCH_NAME = "PRIMARY";
    public static final String DEFAULT_EXCEPTION_PROCESS_NAME = "exception";

    // Document type versions
    public static final String ROUTING_VERSION_ROUTE_LEVEL = "1";
    public static final String ROUTING_VERSION_NODAL = "2";
    public static final String CURRENT_ROUTING_VERSION = ROUTING_VERSION_NODAL;

    // Document and Action Request versions
    public static final int DOCUMENT_VERSION_ROUTE_LEVEL = 0;
    public static final int DOCUMENT_VERSION_NODAL = 1;
    public static final int CURRENT_DOCUMENT_VERSION = DOCUMENT_VERSION_NODAL;

    public static final String EDL_ATTRIBUTE_GLOBAL_ERROR_KEY = "global";
    public static final String POST_PROCESSOR_FAILURE_MESSAGE = "PostProcessor failed to process document: ";

    public static final String DEFAULT_CONFIG_LOCATION_PARAM = "default.config.location";
    public static final String DEFAULT_APPLICATION_CONFIG_LOCATION = "classpath:META-INF/workflow.xml";
    public static final String DEFAULT_SERVER_CONFIG_LOCATION = "classpath:META-INF/server-workflow.xml";
    public static final String DEFAULT_GLOBAL_CONFIG_LOCATION = "classpath:META-INF/kew-config-defaults.xml";
    public static final String ADDITIONAL_CONFIG_LOCATIONS_PARAM = "additional.config.locations";

    // client protocols
    public static final String WEBSERVICE_CLIENT_PROTOCOL = "webservice";
    public static final String LOCAL_CLIENT_PROTOCOL = "local";
    public static final String EMBEDDED_CLIENT_PROTOCOL = "embedded";
    public static final String RMI_CLIENT_PROTOCOL = "rmi";
    public static final String SPRING_INVOKER_CLIENT_PROTOCOL = "springInvoker";

    public static final Set CLIENT_PROTOCOLS = new HashSet();
    static {
    	CLIENT_PROTOCOLS.add(LOCAL_CLIENT_PROTOCOL);
    	CLIENT_PROTOCOLS.add(EMBEDDED_CLIENT_PROTOCOL);
    	CLIENT_PROTOCOLS.add(WEBSERVICE_CLIENT_PROTOCOL);
    	CLIENT_PROTOCOLS.add(RMI_CLIENT_PROTOCOL);
    }

    // system branch state keys
    public static final String POST_PROCESSOR_PROCESSED_KEY = "System.PostProcessorProcessed";
    public static final String POST_PROCESSOR_FINAL_KEY = "System.PostProcessorFinal";

    // custom http header keys
    public static final String DIGITAL_SIGNATURE_HEADER = "KEW_DIGITAL_SIGNATURE";
	public static final String KEYSTORE_ALIAS_HEADER = "KEW_KEYSTORE_ALIAS";

	public static final int DEFAULT_TRANSACTION_TIMEOUT_SECONDS = 3600;
	public static final int MAX_RETURNED_ROWS = 1000;

    public static final String HTML_NON_BREAKING_SPACE = "&nbsp;";

    public static final String LEGACY_DEFAULT_WORKGROUP_TYPE = "W";

    public static final String SIMPLE_DOCUMENT_ACTIONS_SECURITY = "simpleDocumentActionsService.security";
    public static final String DAILY_EMAIL_CRON_EXPRESSION = "dailyEmail.cronExpression";
    public static final String WEEKLY_EMAIL_CRON_EXPRESSION = "weeklyEmail.cronExpression";
    public static final String DAILY_EMAIL_ACTIVE = "dailyEmail.active";
    public static final String WEEKLY_EMAIL_ACTIVE = "weeklyEmail.active";

    public static final String REMOVE_REPLACE_DOCUMENT_TYPE = "EDENSERVICE-DOCS.RemoveReplaceUser";
    public static final String ACTION_LIST_NO_REFRESH = "ActionList.norefresh";


    // receive future action request contants
    public static final String RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY = "_receive_future_requests";
    public static final String DEACTIVATED_FUTURE_REQUESTS_BRANCH_STATE_KEY = "_deactivated_future_requests";
    public static final String DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE = "NO";
    public static final String RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE = "YES";
    public static final String CLEAR_FUTURE_REQUESTS_BRANCH_STATE_VALUE = "CLEAR";
    public static final String USE_REMOTE_EMAIL_SERVICES = "Email.useRemoteServices";

    // lookupable column types
    public static final String LOOKUP_COLUMN_TYPE_TEXT = "text";
    public static final String LOOKUP_COLUMN_TYPE_INTEGER = "integer";
    public static final String LOOKUP_COLUMN_TYPE_LONG = "long";
    public static final String LOOKUP_COLUMN_TYPE_FLOAT = "float";
    public static final String LOOKUP_COLUMN_TYPE_DATETIME = "datetime";

    // document search route status qualifiers
    public static final String DOC_SEARCH_ROUTE_STATUS_QUALIFIER_EXACT = "equal";
    public static final String DOC_SEARCH_ROUTE_STATUS_QUALIFIER_BEFORE = "before";
    public static final String DOC_SEARCH_ROUTE_STATUS_QUALIFIER_AFTER = "after";
    public static final Map<String,String> DOC_SEARCH_ROUTE_STATUS_QUALIFIERS;
    static {
        DOC_SEARCH_ROUTE_STATUS_QUALIFIERS = new HashMap<String,String>();
        DOC_SEARCH_ROUTE_STATUS_QUALIFIERS.put(DOC_SEARCH_ROUTE_STATUS_QUALIFIER_EXACT, "Exactly");
        DOC_SEARCH_ROUTE_STATUS_QUALIFIERS.put(DOC_SEARCH_ROUTE_STATUS_QUALIFIER_BEFORE, "Before");
        DOC_SEARCH_ROUTE_STATUS_QUALIFIERS.put(DOC_SEARCH_ROUTE_STATUS_QUALIFIER_AFTER, "After");
    }

    // service name constants

	public static final String WORKFLOW_UTILITY_SERVICE = "enWorkflowUtilityService";

	public static final String WORKFLOW_DOCUMENT_ACTIONS_SERVICE = "enWorkflowDocumentActionsService";

	public static final String SIMPLE_DATE_FORMAT_FOR_DATE = RiceConstants.SIMPLE_DATE_FORMAT_FOR_DATE;
    public static final String SIMPLE_DATE_FORMAT_FOR_TIME = RiceConstants.SIMPLE_DATE_FORMAT_FOR_TIME;
    public static final String DEFAULT_DATE_FORMAT_PATTERN = RiceConstants.DEFAULT_DATE_FORMAT_PATTERN;

	public static final String ACTIONLIST_COMMAND = "displayActionListView";

	public static final String ACTIONLIST_INLINE_COMMAND = "displayActionListInlineView";

	public static final String EMAIL_COMMAND = "displayEmailView";

	public static final String DOCSEARCH_COMMAND = "displayDocSearchView";

	public static final String SUPERUSER_COMMAND = "displaySuperUserView";

	public static final String HELPDESK_ACTIONLIST_COMMAND = "displayHelpDeskActionListView";

	public static final String INITIATE_COMMAND = "initiate";

	public static final String COMMAND_PARAMETER = "command";

	public static final String ROUTEHEADER_ID_PARAMETER = "docId";

	public static final String BACKDOOR_ID_PARAMETER = "backdoorId";

	public static final String DOCTYPE_PARAMETER = "docTypeName";

	public static final String INITIATE_URL = "initiateURL";

	public static final String ADVANCE_SEARCH_URL = "advanceSearchURL";

	public static final String DOCHANDLER_METHOD = "dochandlerMethod";

	public static final String KEW_NAMESPACE = "KR-WKFLW";

	public static final String DEFAULT_RESPONSIBILITY_TEMPLATE_NAME = "Review";
	
	public static final String EXCEPTION_ROUTING_RESPONSIBILITY_TEMPLATE_NAME = "Resolve Exception";

	// Permission Details

	public static final String DOCUMENT_TYPE_NAME_DETAIL = "documentTypeName";
	public static final String ACTION_REQUEST_CD_DETAIL = "actionRequestCd";
	public static final String ROUTE_NODE_NAME_DETAIL = "routeNodeName";
	public static final String DOCUMENT_STATUS_DETAIL = "routeStatusCode";

	// Permissions

	public static final String BLANKET_APPROVE_PERMISSION = "Blanket Approve Document";
	public static final String AD_HOC_REVIEW_PERMISSION = "Ad Hoc Review Document";
	public static final String ADMINISTER_ROUTING_PERMISSION = "Administer Routing for Document";
	public static final String CANCEL_PERMISSION = "Cancel Document";
	public static final String INITIATE_PERMISSION = "Initiate Document";
	public static final String ROUTE_PERMISSION = "Route Document";
	public static final String SAVE_PERMISSION = "Save Document";

	// system parameters
	public static final String KIM_PRIORITY_ON_DOC_TYP_PERMS_IND = "KIM_PRIORITY_ON_DOC_TYP_PERMS_IND";
    public static final String ACTION_LIST_DOCUMENT_POPUP_IND = "ACTION_LIST_DOCUMENT_POPUP_IND";
    public static final String ACTION_LIST_ROUTE_LOG_POPUP_IND  = "ACTION_LIST_ROUTE_LOG_POPUP_IND";
    public static final String ACTION_LIST_SEND_EMAIL_NOTIFICATION_IND = "SEND_EMAIL_NOTIFICATION_IND";
    public static final String APPLICATION_CONTEXT = "APPLICATION_CONTEXT";
    public static final String SHOW_BACK_DOOR_LOGIN_IND = "SHOW_BACK_DOOR_LOGIN_IND";
    public static final String RULE_DELEGATE_LIMIT = "DELEGATE_LIMIT";
    public static final String RULE_LOCKING_ON_IND = "RULE_LOCKING_ON_IND";
    public static final String BACKDOOR_TARGET_FRAME_NAME = "TARGET_FRAME_NAME";
    public static final String EMAIL_REMINDER_FROM_ADDRESS = "FROM_ADDRESS";
    public static final String MAX_NODES_BEFORE_RUNAWAY_PROCESS = "MAXIMUM_NODES_BEFORE_RUNAWAY";
    public static final String NOTIFICATION_EXCLUDED_USERS_WORKGROUP_NAME_IND = "NOTIFY_EXCLUDED_USERS_IND";
    public static final String DOC_SEARCH_FETCH_MORE_ITERATION_LIMIT = "FETCH_MORE_ITERATION_LIMIT";
    public static final String DOCUMENT_SEARCH_DOCUMENT_POPUP_IND = "DOCUMENT_SEARCH_POPUP_IND";
    public static final String DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND = "DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND";
    public static final String DOC_SEARCH_RESULT_CAP = "RESULT_CAP";
    public static final String DOCUMENT_TYPE_SEARCH_INSTRUCTION = "DOCUMENT_TYPE_SEARCH_INSTRUCTION";
    public static final String EDL_DEBUG_TRANSFORM_IND = "DEBUG_TRANSFORM_IND";
    public static final String EDL_USE_XSLTC_IND = "USE_XSLTC_IND";
    public static final String IS_LAST_APPROVER_ACTIVATE_FIRST_IND = "IS_LAST_APPROVER_ACTIVATE_FIRST_IND";
    public static final String GLOBAL_REVIEWER_REPLACE_INSTRUCTION = "REPLACE_INSTRUCTION";
    public static final String HELP_DESK_ACTION_LIST = "HELP_DESK_NAME_GROUP";
    public static final String NOTE_CREATE_NEW_INSTRUCTION = "NOTE_CREATE_NEW_INSTRUCTION";
    public static final String QUICK_LINKS_RESTRICT_DOCUMENT_TYPES = "RESTRICT_DOCUMENT_TYPES";
    public static final String RULE_CUSTOM_DOC_TYPES = "CUSTOM_DOCUMENT_TYPES";
    public static final String RULE_CREATE_NEW_INSTRUCTION = "RULE_CREATE_NEW_INSTRUCTION";
    public static final String RULE_ROUTE_LOG_POPUP_IND = "ROUTE_LOG_POPUP_IND";
    public static final String RULE_SEARCH_INSTRUCTION = "RULE_SEARCH_INSTRUCTION";
    public static final String RULE_GENERATE_ACTION_REQESTS_IND = "GENERATE_ACTION_REQUESTS_IND";
    public static final String RULE_TEMPLATE_CREATE_NEW_INSTRUCTION = "RULE_TEMPLATE_CREATE_NEW_INSTRUCTION";
    public static final String RULE_TEMPLATE_SEARCH_INSTRUCTION = "RULE_TEMPLATE_SEARCH_INSTRUCTION";
    public static final String SHOW_ATTACHMENTS_IND = "SHOW_ATTACHMENTS_IND";
    public static final String RULE_CACHE_REQUEUE_DELAY = "RULE_CACHE_REQUEUE_DELAY";
    public static final String ACTIONLIST_EMAIL_TEST_ADDRESS = "EMAIl_NOTIFICATION_TEST_ADDRESS";

    //System parameter value comparisons
    public static final String ACTION_LIST_SEND_EMAIL_NOTIFICATION_VALUE = "Y";
    public static final String YES_RULE_CHANGE_AR_GENERATION_VALUE = "Y";
    public static final String YES_DELEGATE_CHANGE_AR_GENERATION_VALUE = "Y";
    public static final String DOCUMENT_SEARCH_ROUTE_LOG_POPUP_VALUE = "Y";
    public static final String DOCUMENT_SEARCH_DOCUMENT_POPUP_VALUE = "Y";
    
    public class PermissionTemplateNames {
        public static final String VIEW_OTHER_ACTION_LIST = "View Other Action List";
        public static final String UNFILTERED_DOCUMENT_SEARCH = "Unfiltered Document Search";
    }
}
