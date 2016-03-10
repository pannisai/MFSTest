package common;

import java.awt.Component;

import mfs.biller.util.AppUtil;

import org.apache.log4j.Logger;

import exception.BillerManageWebException;

public abstract class EditPageController extends BaseControllor {
	private Logger log = Logger.getLogger(EditPageController.class);
	private boolean dialog = false;
	protected String callback = "";
	protected Integer oid;

	public EditPageController(String pageName) {
		super.pageName = pageName;
	}

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		try {
			log.info(getOperationLogMessage(pageName, "Init Page", "Start Do AffterCompose"));
			dialog = isDialog();
			callback = getDialogCallBackUrl();
			String oidStr = "";
			oidStr = getArgument("oid");
			if (AppUtil.isEmpty(oidStr)) {
				oidStr = getParameter("oid");
			}
			if (!AppUtil.isEmpty(oidStr)) {
				oid = Integer.parseInt(oidStr);
			} else {
				oid = -1;
			}
			log.info("oid::" + oid);
			log.info("dialog ::" + dialog);
			init();

		} catch (Exception ex) {
			log.error(getErrorLogMessage(pageName, "Init Page", "OID::" + oid, ex));
			showErrorMessage(ex);
		} finally {

		}
		log.info("Stop:" + System.currentTimeMillis());
	}

	public void doFinally() throws Exception {
		super.doFinally();
		checkSessionTimeOut("Init " + pageName);
	}

	protected abstract void init() throws Exception;

	protected boolean isDialog() {
		if (!dialog) {
			String dialogStr = getArgument("dialog");
			if (dialogStr.equalsIgnoreCase("true")) {
				dialog = true;
			}
		}
		return dialog;

	}

	private String getDialogCallBackUrl() {
		String callback = "";
		try {
			callback = getArgument("callback");
			log.debug("Callback::" + callback);
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
		return callback;
	}

}
