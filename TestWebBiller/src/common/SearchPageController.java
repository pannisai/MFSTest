package common;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ListModel;

import mfs.biller.util.AppUtil;

import org.apache.log4j.Logger;

public abstract class SearchPageController extends BaseControllor {

	private Logger log = Logger.getLogger(SearchPageController.class);
	@Wire
	protected Grid gdResult;
	@Wire
	protected Paging pagingTop;
	@Wire
	protected Paging paging;

	public SearchPageController(String pageName) throws BillerWebSessionTimeOutException {
		String init = getParameter("init");
		if ((!AppUtil.isEmpty(init)) && init.equals("true")) {
			super.setAuthorizationSession();
		}
		super.pageName = pageName;
	}

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		try {

			log.info(getOperationLogMessage(pageName, "Init Page", "Start Do AffterCompose"));
			paging.setPageSize(Integer.parseInt(AppConfig.getValue(AppConfig.PAGE_SIZE_KEY)));
			if (pagingTop != null) {
				pagingTop.setPageSize(Integer.parseInt(AppConfig.getValue(AppConfig.PAGE_SIZE_KEY)));
			}
			init(com);
		} catch (Exception ex) {
			log.error(getErrorLogMessage(pageName, "Init Page", ex));
			showErrorMessage(ex);
		} finally {

		}
		log.info("Stop:" + System.currentTimeMillis());
	}

	public void doFinally() throws Exception {
		super.doFinally();
		checkSessionTimeOut("Init " + pageName);
	}

	@Listen("onLastInputRender=listbox")
	public void onAfterRender() {
		log.info(getOperationLogMessage(pageName, "Start page from session", ""));
		try {
			setupPageFromSession();
		} catch (BillerWebSessionTimeOutException stex) {
			throw stex;
		} catch (Exception ex) {
			log.error(getErrorLogMessage(pageName, "Start page from session", ex));
			showErrorMessage(ex);
		} finally {

		}
	}

	protected abstract void init(Component com) throws Exception;

	protected abstract void setupPageFromSession();

	protected <V> void setGridResultModel(ListModel<V> results) throws Exception {
		paging.setTotalSize(results.getSize());
		gdResult.setModel(results);
		if (pagingTop != null) {
			pagingTop.setTotalSize(results.getSize());
			setAutoOnPagingEventTop(pagingTop, paging);
		}

	}

	protected <V> void setGridResultModel(AbstractPagingListModel<V> results) throws Exception {
		results.refreshPaging(paging.getActivePage() + 1, Integer.parseInt(AppConfig.getValue(AppConfig.PAGE_SIZE_KEY)), getUserInfo());
		super.setResultTablePaging(pagingTop, paging, gdResult, results, paging.getActivePage(), Integer.parseInt(AppConfig.getValue(AppConfig.PAGE_SIZE_KEY)));
		gdResult.invalidate();
	}

	private <T> void setAutoOnPagingEventTop(final Paging pagingTop, final Paging paging) {
		try {
			pagingTop.addEventListener("onPaging", new EventListener() {
				@Override
				public void onEvent(Event even) throws Exception {
					paging.setActivePage(pagingTop.getActivePage());
				}
			});
			paging.addEventListener("onPaging", new EventListener() {
				@Override
				public void onEvent(Event even) throws Exception {
					pagingTop.setActivePage(paging.getActivePage());
				}
			});
		} catch (Exception ex) {
			throw new BillerManageWebException(ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "null" })
	public byte[] generateCsvFile(List listData, Class cls, String criteria, String strHeader, String fieldHeader) throws ReportServiceException, FileNotFoundException, IOException {
		int totalColumn = 0;
		int row = 1;
		String[] header = null;
		String[] field = null;
		StringBuffer sbf = new StringBuffer();
		byte[] bytes = null;	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("en", "EN"));
		
		try {
			if (!isEmptyString(strHeader)) {
				header = strHeader.split("\\|");
				totalColumn = header.length;
			} else {
				totalColumn = cls.getDeclaredFields().length;
				header = new String[totalColumn];
				for (int i = 0; i < totalColumn; i++) {
					header[i] = cls.getFields()[i].getName();
				}
			}
			if (!isEmptyString(fieldHeader)) {
				field = fieldHeader.split("\\|");
			} else {
				header = new String[totalColumn];
				for (int i = 0; i < totalColumn; i++) {
					field[i] = cls.getFields()[i].getName();
				}
			}
		
			// gen Criteria
			if(criteria!=null){					
			}
			// gen Header
			for (int i = 0; i < totalColumn; i++) {			
				sbf.append(new String(header[i].getBytes("UTF-8"), "UTF-8"));
				if(i < totalColumn - 1){
					sbf.append(",");
				}
			}
			// gen Body								
			for(Object arrayObj : listData){
				sbf.append(System.getProperty("line.separator"));																
				Class clss = arrayObj.getClass();
				if (clss != null) {
					for (int j = 0; j < totalColumn; j++) {	
						if("row".equalsIgnoreCase(field[j])){
							sbf.append(row);
						}else{						
							Field fields[] = clss.getDeclaredFields();
							for (Field fieldData : fields) {
								if (field[j].equals(fieldData.getName())) {
									fieldData.setAccessible(true);	
									if(fieldData.getType().isAssignableFrom(Date.class)){
										sbf.append((fieldData.get(arrayObj) != null ? "'"+String.valueOf(sdf.format(fieldData.get(arrayObj))) : ""));
									}else{
										sbf.append((fieldData.get(arrayObj) != null ? String.valueOf((fieldData.get(arrayObj))) : ""));										
									}								
								}
							}
						}
						if(j < totalColumn-1){
							sbf.append(",");
						}
					}						
				}
				row++;
			}			
			 bytes = sbf.toString().getBytes("TIS-620");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	private static boolean isEmptyString(String str) {
		boolean ch = true;
		if (str != null && !"".equals(str)) {
			ch = false;
		}
		return ch;
	}
}
