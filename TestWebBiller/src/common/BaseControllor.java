package common;

import java.awt.Button;
import java.awt.Component;
import java.awt.Window;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.AppUtil;

import org.apache.log4j.Logger;

import sun.net.www.http.HttpClient;
import exception.BillerManageWebException;
import exception.BillerWebSessionTimeOutException;

public abstract class BaseControllor extends SelectorComposer<Component> {
	private Logger log = Logger.getLogger(BaseControllor.class);
	private UserInfoBean userInfo = null;
	protected String pageName = "";

	protected void checkSessionTimeOut(String operName) throws BillerWebSessionTimeOutException {
//		String responseCode = "";
//		Map<String, String> resultMap = null;
//		Authorization auth = null;
//		StringBuilder url = null;
//		StringBuilder params = null;
//		String responseDesc = null;
//		auth = getAuthorization();
//		if (auth == null || AppUtil.isEmpty(auth.getUsername())) {
//			log.info("Session Timeout Authorization Is null");
//			// throw new BillerWebSessionTimeOutException();
//			// Executions.getCurrent().sendRedirect("../timeout.zul");
//			goToSessionTimeOutPage();
//			return;
//		}
//		try {
//			url = new StringBuilder();
//			url.append(AppConfig.getValue(AppConfig.CHK_SESS_TIMEOUT_URL));
//			params = new StringBuilder();
//			params.append("userName=" + URLEncoder.encode(auth.getUsername()));
//			params.append("&refId=" + URLEncoder.encode(auth.getRefId()));
//			operName = (operName != null && operName.length() > 29) ? operName.substring(0, 29) : operName;
//			params.append("&operName=" + URLEncoder.encode(operName));
//			params.append("&clientIpAddr=" + URLEncoder.encode(auth.getClientIP()));
//			resultMap = HttpClient.get(url.toString() + "?" + params.toString());
//			responseCode = resultMap.get(AppConfig.getValue(AppConfig.CHK_SESS_TIMEOUT_ERR_CODE_KEY));
//			responseDesc = resultMap.get(AppConfig.getValue(AppConfig.CHK_SESS_TIMEOUT_ERR_DESC_KEY));
//
//		} catch (Exception ex) {
//			removeSession(AppConstant.S_AUTHORIZATION);
//			throw new BillerManageWebException(ex);
//		} finally {
//			resultMap = null;
//
//		}
//		if (AppUtil.trim(responseCode).equalsIgnoreCase(AppConfig.getValue(AppConfig.CHK_SESS_TIMEOUT_SUCCESS_CODE))) {
//		} else {
//			log.info("URL::" + url.toString() + "?" + params.toString());
//			log.info("Check Session Timeout::[Error Code:" + responseCode + ",Error Desc:" + responseDesc + ",refId:" + auth.getRefId() + ",OperName:" + operName);
//			url = null;
//			params = null;
//			removeSession(AppConstant.S_AUTHORIZATION);
//			goToSessionTimeOutPage();
//			// Executions.getCurrent().sendRedirect("../timeout.zul");
//			// throw new BillerWebSessionTimeOutException();
//		}
	}

	private void goToSessionTimeOutPage() {
		try {
			removeSession(AppConstant.S_AUTHORIZATION);
			Executions.getCurrent().sendRedirect(AppConfig.getValue(AppConfig.SESS_TIMEOUT_ERR_PAGE_URL), "_top");
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	protected void setAuthorizationSession() {
		String ip = null;
		String username = null;
		String refId = null;
		String menuId = null;
		String bllr_srvc_id_list = null;
		String serviceCode = null;
		String accessTextFileFlag = null;
		String accessReportFileFlag = null;
		String branch = null;
		
		StringBuilder url = null;
		Authorization auth = null;
		Map<String, String> resultMap = null;		
		try {
			removeSession(AppConstant.S_AUTHORIZATION);			
			ip = Executions.getCurrent().getHeader("X-Forwarded-For");
			log.debug("X-Forwarded-For:" + ip);
			if (ip==null||"".equals(ip)) {
				ip = Executions.getCurrent().getRemoteAddr();
			}
			log.info("Clien IP:" + ip);			
			boolean chk = Executions.getCurrent().getParameter("input") != null;
			if(chk){				
				resultMap = HttpClient.getParameter(AppConfig.getValue(AppConfig.SECURITY_DECRYPTED_ENDPOINT), URLEncoder.encode(Executions.getCurrent().getParameter("input")));				
				refId = resultMap.get("refId");
				username = resultMap.get("userName");
				menuId = resultMap.get("menuId");
				bllr_srvc_id_list = resultMap.get("bllr_srvc_id_list");			
				serviceCode = resultMap.get("serviceCode");
				accessTextFileFlag = resultMap.get("accessTextFileFlag");
				accessReportFileFlag = resultMap.get("accessReportFileFlag");
				branch = resultMap.get("branch");
			}else{
				refId = Executions.getCurrent().getParameter("refId");			
				username = Executions.getCurrent().getParameter("userName");
				menuId = Executions.getCurrent().getParameter("menuId");
				bllr_srvc_id_list = Executions.getCurrent().getParameter("bllr_srvc_id_list");	
			}
			
			/* ### Set test Value ### */
			username = "UserTest";
			String permList = "1000,2000,3000,4000";
			refId = "0000000000";
			bllr_srvc_id_list = "12,37,32,31,13";
//			serviceCode = "0007";
//			accessTextFileFlag = "Y";
//			accessReportFileFlag = "Y";
//			branch = "3440";
						
			auth = new Authorization();			
			auth.setUsername(username);
			auth.setRefId(refId);
			auth.setMenuId(menuId);
			auth.setClientIP(ip);
			auth.setServiceIds(bllr_srvc_id_list);			
//			auth.setPermission(permList);
			
			log.info("username:" + username);
			log.info("refId:" + refId);
			log.info("menuId:" + menuId);
			log.info("bllr_srvc_id_list:" + bllr_srvc_id_list);
			
			if(chk){
				auth.setServiceCode(serviceCode);
				auth.setAccessTextFileFlag(accessTextFileFlag);
				auth.setAccessReportFileFlag(accessReportFileFlag);
				auth.setBranch(branch);
				
				log.info("serviceCode:" + serviceCode);
				log.info("accessTextFileFlag:" + accessTextFileFlag);
				log.info("accessReportFileFlag:" + accessReportFileFlag);
				log.info("branch:" + branch);
			}
			
//			auth = setPermission(auth);	
			Session session = Sessions.getCurrent();
			session.setAttribute(AppConstant.S_AUTHORIZATION, auth);
		} catch (Exception ex) {
			throw new BillerManageWebException("Set Session fail" + ex);
		} finally {
			ip = null;
			username = null;
			refId = null;
			menuId = null;
			serviceCode = null;
			accessTextFileFlag = null;
			accessReportFileFlag = null;
			branch = null;
		}
	}

	private Authorization setPermission(Authorization auth) throws BillerWebSessionTimeOutException {
		Map<String, String> resultMap = new HashMap<String, String>();
		StringBuilder url = null;
		StringBuilder params = null;
		String responseCode = "";
		String responseDesc = null;
		String permList = "";
		if (auth == null || AppUtil.isEmpty(auth.getUsername())) {
			log.info("Session Timeout Authorization Is null");
			goToSessionTimeOutPage();
			return auth;
		}
		try {
			url = new StringBuilder();
			url.append(AppConfig.getValue(AppConfig.GET_PERMISSION_URL));
			params = new StringBuilder();
			params.append("userName=" + URLEncoder.encode(auth.getUsername()));
			params.append("&refId=" + URLEncoder.encode(auth.getRefId()));
			params.append("&clientIpAddr=" + URLEncoder.encode(auth.getClientIP()));
			params.append("&menuId=" + URLEncoder.encode(auth.getMenuId()));
			log.debug("URL:" + url.toString() + "?" + params.toString());
			resultMap = HttpClient.get(url.toString() + "?" + params.toString());
			responseCode = resultMap.get(AppConfig.getValue(AppConfig.GET_PERMISSION_ERR_CODE_KEY));
			responseDesc = resultMap.get(AppConfig.getValue(AppConfig.GET_PERMISSION_ERR_DESC_KEY));

			if (AppUtil.isEmpty(responseCode)) {
				permList = resultMap.get("permList");
				log.debug("permList::" + permList);
				auth.setPermission(permList);
			} else {
				log.info("URL::" + url.toString() + "?" + params.toString());
				log.info("Set permission list::[Error Code:" + responseCode + ",Error Desc:" + responseDesc + ",userName:" + auth.getUsername() + ",refId:" + auth.getRefId() + ",clientIpAddr:" + auth.getClientIP() + ",menuId:" + auth.getMenuId());
			}
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			url = null;
			params = null;
			resultMap = null;
		}

		return auth;
	}

	protected Authorization getAuthorization() {
		Authorization auth = null;
		try {
			auth = (Authorization) Sessions.getCurrent().getAttribute(AppConstant.S_AUTHORIZATION);
		} catch (Exception ex) {
			log.error("Can't find user session");
		}
		return auth;
	}

	protected UserInfoBean getUserInfo() {
		Authorization auth = null;
		try {
			if (userInfo == null) {
				auth = getAuthorization();
				userInfo = new UserInfoBean();
				if (auth != null) {
					userInfo.setName(auth.getUsername());
					userInfo.setIpAddress(auth.getClientIP());
				}
			}
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			auth = null;
		}

		return userInfo;
	}

	protected String getUserName() {
		Authorization auth = null;
		String username = "";
		try {
			auth = getAuthorization();
			if (auth != null) {
				username = auth.getUsername();
			}

		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			auth = null;
		}
		return username;
	}

	protected List<Integer> getServiceIds() {
		Authorization auth = null;
		List<Integer> serviceIds = null;		
		try {
			auth = getAuthorization();
			if (auth != null) {
				serviceIds = auth.getServiceIds();
			}

		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			auth = null;
		}
		if (AppUtil.isEmpty(serviceIds)) {
			serviceIds = new ArrayList<Integer>();
		}
		return serviceIds;
	}

	protected void chkSearchPermission(Button btSearch) {
		Authorization auth = getAuthorization();
		try {

			if (auth == null) {
				btSearch.setVisible(false);
			} else {
				if (auth.isSearch()) {
					btSearch.setVisible(true);
				} else {
					btSearch.setVisible(false);
				}
			}
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			auth = null;
		}
	}
	
	protected void chkExportPermission(Button btExport) {
		Authorization auth = getAuthorization();
		try {

			if (auth == null) {
				btExport.setVisible(false);
			} else {
				if (auth.isExport()) {
					btExport.setVisible(true);
				} else {
					btExport.setVisible(false);
				}
			}
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			auth = null;
		}
	}

	protected void chkInsertPermission(Button btInsert, Button... edits) {
		Authorization auth = getAuthorization();
		boolean isInsert = false;
		try {

			if (auth == null) {
				isInsert = false;
			} else {
				if (auth.isInsert()) {
					isInsert = true;
				} else {
					isInsert = false;
				}
				if (!auth.isUpdate() && edits != null) {
					for (Button editButton : edits) {
						editButton.setLabel("View");
					}
				}
			}
			btInsert.setVisible(isInsert);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			auth = null;
		}
	}

	protected boolean chkUpdatePermission(Collection<Component> comps, Button... buttons) {

		try {
			return chkUpdatePermission(comps, null, buttons);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {

		}

	}

	protected boolean chkUpdatePermission(Collection<Component> comps, Listbox[] listboxs, Button... buttons) {
		try {
			return chkUpdatePermission(comps, listboxs, null, buttons);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {

		}
	}

	protected boolean chkUpdatePermission(Collection<Component> comps, Listbox[] listboxs, Button[] viewButtons, Button... buttons) {
		Authorization auth = getAuthorization();
		boolean isUpdate = false;
		try {

			if (auth == null) {
				isUpdate = false;
			} else {
				if (auth.isUpdate()) {
					isUpdate = true;
				} else {
					isUpdate = false;
					if (viewButtons != null) {
						for (Button editButton : viewButtons) {
							editButton.setLabel("View");
						}
					}
				}
				if (!auth.isInsert() && listboxs != null) {
					setupUpdateListbox(listboxs, isUpdate);
				}
			}

			for (Button button : buttons) {
				button.setVisible(isUpdate);
			}

			setUpViewPage(comps, isUpdate);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			auth = null;
		}
		return isUpdate;
	}

	private void setupUpdateListbox(Listbox[] listboxs, boolean isUpdate) {
		try {
			/* Set view page */
			for (Listbox listbox : listboxs) {
				if (listbox.getItemCount() > 0) {
					Listitem createNew = listbox.getItemAtIndex(listbox.getItemCount() - 1);

					createNew.setVisible(!isUpdate);
					// createNew=null;
				}
			}
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {

		}
	}

	private void setUpViewPage(Collection<Component> comps, boolean isUpdate) {
		try {
			/* Set view page */
			for (Component comp : comps) {
				log.debug("Comp : " + comp);
				log.debug("isUpdate : " + isUpdate);
				if (InputElement.class.isAssignableFrom(comp.getClass())) {
					((InputElement) comp).setReadonly(!isUpdate);
				}
				if (Listbox.class.isAssignableFrom(comp.getClass())) {
					((Listbox) comp).setDisabled(!isUpdate);
				}
				if (Spinner.class.isAssignableFrom(comp.getClass())) {
					((Spinner) comp).setButtonVisible(isUpdate);
				}
				if (Doublespinner.class.isAssignableFrom(comp.getClass())) {
					((Doublespinner) comp).setButtonVisible(isUpdate);
				}
				if (Datebox.class.isAssignableFrom(comp.getClass())) {
					((Datebox) comp).setButtonVisible(isUpdate);
				}

				if (Radiogroup.class.isAssignableFrom(comp.getClass())) {
					for (Component com : ((Radiogroup) comp).getChildren()) {
						if (Radio.class.isAssignableFrom(com.getClass())) {
							((Radio) com).setDisabled(!isUpdate);
						}
					}
				}
				if (Timebox.class.isAssignableFrom(comp.getClass())) {
					((Timebox) comp).setButtonVisible(isUpdate);
				}

			}
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {

		}
	}

	protected <T> void setSession(String name, T t) {
		removeSession(name);
		Session session = Sessions.getCurrent();
		session.setAttribute(name, t);
	}

	protected Object getSession(String name) {
		Object result = Sessions.getCurrent().getAttribute(name);
		return result;
	}

	protected void removeSession(String name) {
		Session session = Sessions.getCurrent();
		session.removeAttribute(name);
	}

	protected <T> void setResultTablePaging(final Paging pagingTop, Paging paging, Grid grid, AbstractPagingListModel<T> model, int startPage, int pageSize) throws Exception {
		grid.setModel(model.getItems());
		paging.setPageSize(pageSize);
		// paging.setActivePage(startPage);
		paging.setTotalSize(model.getTotalSize(getUserInfo()));
		setOnPagingEvent(pagingTop, paging, grid, model, pageSize);
		if (pagingTop != null) {
			pagingTop.setPageSize(pageSize);
			// pagingTop.setActivePage(startPage);
			pagingTop.setTotalSize(paging.getTotalSize());
			setOnPagingEventTop(pagingTop, paging);
		}
	}

	private <T> void setOnPagingEventTop(final Paging pagingTop, final Paging paging) {
		try {
			pagingTop.addEventListener("onPaging", new EventListener() {
				@Override
				public void onEvent(Event even) throws Exception {
					paging.setActivePage(pagingTop.getActivePage());
					Event event = new Event("onPaging");
					Events.sendEvent(paging, event);
				}
			});
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
	}

	private <T> void setOnPagingEvent(final Paging pagingTop, final Paging paging, final Grid grid, final AbstractPagingListModel<T> model, final int pageSize) {
		try {
			paging.addEventListener("onPaging", new EventListener() {
				@Override
				public void onEvent(Event even) throws Exception {
					grid.setModel(model.getPageData(paging.getActivePage() + 1, pageSize, getUserInfo()));
					if (pagingTop != null) {
						pagingTop.setActivePage(paging.getActivePage());
					}
				}
			});
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
	}

	protected void showErrorMessage(Exception ex) {
		showErrorMessage("", ex);
	}

	protected void showErrorMessage(String message, Exception ex) {
		String errMessage = ex.getMessage();

		if (AppUtil.isEmpty(errMessage)) {
			try {
				errMessage = ex.getCause().getMessage();
			} catch (NullPointerException npe) {

			}
		}

		message = message + ":" + errMessage;
		showErrorMessage(message);
	}

	protected void showErrorMessage(String message) {
		Messagebox messageBox = new Messagebox();
		Messagebox.show(message, "Error", Messagebox.OK, Messagebox.ERROR);
	}

	protected void showSaveConfirmMessage(EventListener<Event> listener) {
		try {
			showConfirmMessage(AppMessage.getMessage(AppMessage.MSG_SAVE_CONFIRM), listener);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
	}

	protected void showResetConfirmMessage(EventListener<Event> listener) {
		try {
			showConfirmMessage(AppMessage.getMessage(AppMessage.MSG_RESET_CONFIRM), listener);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
	}

	private void showConfirmMessage(String message, EventListener<Event> listener) {
		try {
			Messagebox.show(message, "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, listener);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}

	}

	protected void showSaveSuccessMessage(EventListener<Event> listener) {
		showInfomationMessage(AppMessage.getMessage(AppMessage.MSG_SAVE_SUCCESS), listener);
	}

	protected void showSaveFailMessage() {
		showErrorMessage(AppMessage.getMessage(AppMessage.MSG_SAVE_FAIL));
	}

	protected void showInfomationMessage(String message) {
		showInfomationMessage(message, null);
	}

	private void showInfomationMessage(String message, EventListener<Event> listener) {
		Messagebox.show(message, "Information", Messagebox.OK, Messagebox.INFORMATION, listener);
	}

	protected void openMedalDialog(String src, String callbackUrl) {
		Include mainContentTemplet = null;
		try {

			Window window = (Window) Executions.createComponents("/page/dialog/modalDialog.zul", null, null);
			// log.debug("Page::"+Path.getComponent("//modalPage"));
			mainContentTemplet = (Include) Path.getComponent("/modalDialog/dialogContent");
			src = src + "?dialog=true";
			if (!AppUtil.isEmpty(callbackUrl)) {
				src = src + "&callback=" + URLEncoder.encode(callbackUrl);
			}
			log.debug("Dialog src::" + src);
			mainContentTemplet.setMode("instant");
			mainContentTemplet.setSrc(src);

			window.doModal();

		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			mainContentTemplet = null;
		}
	}
	
	protected String getParameter(String key) {
		String value = "";
		try {
			value = Executions.getCurrent().getParameter(key);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
		return AppUtil.trim(value);
	}

	protected String getArgument(String key) {
		Object var = null;
		try {
			Map arg = Executions.getCurrent().getArg();
			if (AppUtil.isEmpty(arg)) {
				return "";
			}
			var = arg.get(key);
			if (var == null) {
				return "";
			}
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
		return AppUtil.trim(var.toString());
	}

	protected void closeMedalDialog(String callbackUrl) {
		try {
			super.getSelf().getParent().getParent().detach();
			Executions.getCurrent().sendRedirect(callbackUrl);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
	}

	protected void redirect(String url) {
		try {
			Executions.getCurrent().sendRedirect(url);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {

		}
	}

	protected String getOperationLogMessage(String page, String oper, Map<String, String> params) {
		String log = "";
		try {
			log = getOperationLogMessage(page, oper, "", params);

		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
		return log;
	}

	protected String getOperationLogMessage(String page, String oper, String msg) {
		String log = "";
		try {
			log = getOperationLogMessage(page, oper, msg, null);

		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
		return log;
	}

	protected String getOperationLogMessage(String page, String oper, String msg, Map<String, String> params) {
		Authorization auth = getAuthorization();
		StringBuilder logStr = new StringBuilder();
		String log = "";
		try {
			if (auth == null) {
				auth = new Authorization();
			}
			logStr.append("[Page:" + page);
			logStr.append(",Operation:" + oper);
			logStr.append(",User:" + auth.getUsername());
			logStr.append(",Start:" + System.currentTimeMillis());
			logStr.append("]");
			if (!AppUtil.isEmpty(params)) {
				logStr.append(",Param:{");
				for (String key : params.keySet()) {

					logStr.append(key + ":" + params.get(key));
					logStr.append(",");
				}
				logStr.append("}");
			}
			if (!AppUtil.isEmpty(msg)) {
				logStr.append("," + msg);
			}
			log = logStr.toString();
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			logStr = null;
			auth = null;
		}
		return log;
	}

	protected String getStopLogMessage(String page) {
		Authorization auth = getAuthorization();
		StringBuilder logStr = new StringBuilder();
		String log = "";
		try {
			if (auth == null) {
				auth = new Authorization();
			}
			logStr.append("[Page:" + page);
			logStr.append(",User:" + auth.getUsername());
			logStr.append(",RefId:" + auth.getRefId());
			logStr.append("]");
			logStr.append(",Stop:" + System.currentTimeMillis());
			log = logStr.toString();
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		} finally {
			logStr = null;
			auth = null;
		}
		return log;
	}

	protected String getErrorLogMessage(String page, String oper, Exception ex) {
		String log = "";
		try {
			log = getErrorLogMessage(page, oper, "", null, ex);

		} catch (Exception e) {
			throw new BillerManageWebException(e);
		}
		return log;
	}

	protected String getErrorLogMessage(String page, String oper, String msg, Exception ex) {
		String log = "";
		try {
			log = getErrorLogMessage(page, oper, msg, null, ex);

		} catch (Exception e) {
			throw new BillerManageWebException(e);
		}
		return log;
	}

	protected String getErrorLogMessage(String page, String oper, Map<String, String> params, Exception ex) {
		String log = "";
		try {
			log = getErrorLogMessage(page, oper, "", params, ex);

		} catch (Exception e) {
			throw new BillerManageWebException(e);
		}
		return log;
	}

	protected String getErrorLogMessage(String page, String oper, String msg, Map<String, String> params, Exception ex) {
		Authorization auth = getAuthorization();
		StringBuilder logStr = new StringBuilder();
		PrintWriter pintWriter = null;
		Writer writer = null;
		String log = "";
		String exStr = "";
		try {
			if (auth == null) {
				auth = new Authorization();
			}
			logStr.append("[Page:" + page);
			logStr.append(",MenuID:" + auth.getMenuId());
			logStr.append(",Operation:" + oper);
			logStr.append(",RefId:" + auth.getRefId());
			logStr.append(",User:" + auth.getUsername());
			logStr.append(",Ip:" + auth.getClientIP());
			logStr.append("]");
			if (!AppUtil.isEmpty(params)) {
				logStr.append(",Param:{");
				for (String key : params.keySet()) {

					logStr.append(key + ":" + params.get(key));
					logStr.append(",");
				}
				logStr.append("}");
			}
			if (!AppUtil.isEmpty(msg)) {
				logStr.append("," + msg);
			}
			if (!AppUtil.isEmpty(ex)) {
				exStr = ex.getMessage();
				if (AppUtil.isEmpty(exStr)) {
					try {
						exStr = ex.getCause().getMessage();
					} catch (NullPointerException npe) {

					}
				}
				logStr.append("," + exStr);
				if (AppConfig.getValue(AppConfig.LOG_PRINT_STACK_TRACE).equals("true")) {

					logStr.append("\n" + ExceptionUtils.getStackTrace(ex));
				}
			}
			/* ## print strack trace ## */
			log = logStr.toString();
		} catch (Exception e) {
			throw new BillerManageWebException(e);
		} finally {
			logStr = null;
			auth = null;
			writer = null;
			pintWriter = null;
		}
		return log;
	}

}
