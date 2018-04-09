package com.jerry.yiyachat.search;

import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.jivesoftware.smackx.xdata.Form;

public class SearchModel implements SearchContract.ISearchModel {
    @Override
    public boolean search(String searchString) {
        AbstractXMPPConnection connection = XMPPServer.getConnection();
        UserSearchManager searchManager = new UserSearchManager(connection);

        String searchService = "search." + connection.getServiceName();
        try {
            Form searchForm = searchManager.getSearchForm(searchService);
            Form answerForm = searchForm.createAnswerForm();
            answerForm.setAnswer("Username", true);
            answerForm.setAnswer("search", searchString);
            ReportedData reportedData = searchManager.getSearchResults(answerForm, searchService);
            return !reportedData.getRows().isEmpty();
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException e) {
            e.printStackTrace();
            return false;
        }
    }
}