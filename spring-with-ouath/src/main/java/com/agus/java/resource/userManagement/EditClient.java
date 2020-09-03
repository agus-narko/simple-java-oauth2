package com.agus.java.resource.userManagement;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.model.userManagement.Client;
import com.agus.java.model.userManagement.ClientDao;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.ValidationUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EditClient implements DataProcess {

	@Autowired
	ClientDao clientDao;


	@Override
    @SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {

	    //Validation
        List<String> validationFieldList = new ArrayList<String>();
        validationFieldList.add("clientId");
        validationFieldList.add("newClientId");
        validationFieldList.add("newClientSecret");
        ValidationUtil.checkForUnsupportedParameters(serviceInput,validationFieldList);
        ValidationUtil.valBlankOrNullList(serviceInput,validationFieldList);


		// prepare data
		String clientId = String.valueOf(serviceInput.get("clientId"));
		String newClientId = String.valueOf(serviceInput.get("newClientId"));
		String newClientSecret ="{noop}"+String.valueOf(serviceInput.get("newClientSecret"));

		String errorMessage = "";
		JSONObject jsonOutput = new JSONObject();

		try {
			if (!newClientId.isEmpty() && !newClientSecret.isEmpty()) {
				Query checkData = clientDao.createQuery("Select count(1) from "
						+ Client.ENTITY_NAME + " where clientId = :clientId ");
				checkData.setParameter("clientId", clientId);

				Long result = (Long) checkData.uniqueResult();

				clientDao.closeSessionCreateQuery();
				if (result == 1) {



					StringBuilder sb = new StringBuilder();
					sb.append("UPDATE ")
							.append(Client.ENTITY_NAME)
							.append(" SET clientId = :newClientId, clientSecret = :newClientSecret ")
							.append(" WHERE clientId = :clientId ");

					Query query = clientDao.createQuery(sb.toString());
					query.setParameter("newClientId", newClientId);
					query.setParameter("newClientSecret", newClientSecret);
					query.setParameter("clientId", clientId);

					query.executeUpdate();
					clientDao.closeSessionCreateQuery();

					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_SUCCESS);
				} else {
					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_FAILED);
					jsonOutput.put("errorMessage", "clientId tidak di temukan");
				}

			} else {
				jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
				jsonOutput.put("errorMessage", "tidak ada data yg di update");
			}

		} catch (Exception e) {
			e.printStackTrace();

			errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();

			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
		}

		return jsonOutput;
	}

}