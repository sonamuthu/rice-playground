package org.kuali.rice.krad.messages.providers.table;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.krad.messages.Message;
import org.kuali.rice.krad.messages.MessageProvider;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link MessageProvider} that stores messages in a database
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class DatabaseMessageProvider implements MessageProvider {
    private BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.rice.krad.messages.MessageProvider#getMessage(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public Message getMessage(String namespace, String component, String name, String locale) {
        Collection<Message> results = getMessageByCriteria(namespace, component, name, locale);

        if ((results != null) && !results.isEmpty()) {
            return results.iterator().next();
        }

        return null;
    }

    /**
     * @see org.kuali.rice.krad.messages.MessageProvider#getAllMessagesForComponent(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public Collection<Message> getAllMessagesForComponent(String namespace, String component, String locale) {
        return getMessageByCriteria(namespace, component, null, locale);
    }

    /**
     * Performs a query using the {@link BusinessObjectService} to retrieve messages that match the given
     * namespace, component, name, and locale. Not parameters maybe empty in which case they will not be added
     * to the criteria
     *
     * @param namespace - namespace code to search for
     * @param component - component code to search for
     * @param name - name of the parameter to find
     * @param locale - locale code to search for
     * @return Collection<Message> matching messages or empty collection if not are found
     */
    protected Collection<Message> getMessageByCriteria(String namespace, String component, String name, String locale) {
        Collection<Message> results = null;

        Map<String, String> criteria = new HashMap<String, String>();

        if (StringUtils.isNotBlank(namespace)) {
            criteria.put("namespaceCode", namespace);
        }
        if (StringUtils.isNotBlank(component)) {
            criteria.put("componentCode", component);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.put("name", name);
        }
        if (StringUtils.isNotBlank(locale)) {
            criteria.put("locale", locale);
        }

        results = businessObjectService.findMatching(Message.class, criteria);

        return results;
    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KRADServiceLocator.getBusinessObjectService();
        }

        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
