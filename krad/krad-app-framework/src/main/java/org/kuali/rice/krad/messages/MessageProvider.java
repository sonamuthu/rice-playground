package org.kuali.rice.krad.messages;

import java.util.Collection;

/**
 * API for message providers
 *
 * <p>
 * A message provider fronts an external repository and provides messages from that repository
 * to the application. The provider must support the following message retrieval methods and be registered
 * with the {@link MessageService} implementation
 * </p>
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public interface MessageProvider {

    /**
     * Gets the {@link Message} object that has the given namespace, component, name, and locale
     *
     * @param namespace - namespace code the message belongs to
     * @param component - component code the namespace is associated with
     * @param name - name that identifies the message within the namespace and component
     * @param locale - locale code for the message to return
     * @return Message matching message object, or null if a message was not found
     */
    public Message getMessage(String namespace, String component, String name, String locale);

    /**
     * Gets all message objects for the given namespace, component, and locale
     *
     * @param namespace - namespace code the message belongs to
     * @param component - component code the namespace is associated with
     * @param locale - locale code for the message to return
     * @return Collection<Message> collection of messages that match, or empty collection if no messages
     *         are found
     */
    public Collection<Message> getAllMessagesForComponent(String namespace, String component, String locale);
}
