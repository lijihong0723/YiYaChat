package com.jerry.yiyachat.search;

import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.jivesoftware.smackx.xdata.Form;

public class SearchModel implements SearchContract.ISearchModel {
    @Override
    public String search(String searchString) {
        AbstractXMPPConnection connection = XMPPServer.getConnection();
        UserSearchManager searchManager = new UserSearchManager(connection);

        String searchService = "search." + connection.getServiceName();
        try {
            Form searchForm = searchManager.getSearchForm(searchService);
            Form answerForm = searchForm.createAnswerForm();
            answerForm.setAnswer("Username", true);
            answerForm.setAnswer("search", searchString);
            ReportedData reportedData = searchManager.getSearchResults(answerForm, searchService);
            if (reportedData.getRows().isEmpty()) {
                return "";
            }

            ReportedData.Row row = reportedData.getRows().get(0);
            return row.getValues("jid").get(0);
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean isInRoster(String jid) {
        Roster roster = Roster.getInstanceFor(XMPPServer.getConnection());
        return roster.getEntry(jid) == null;
    }
}