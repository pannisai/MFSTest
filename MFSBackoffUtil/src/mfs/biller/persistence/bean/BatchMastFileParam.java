package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchMastFileParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer  BTCH_MAST_FILE_ID;
	private Date FROM_DTTM;
	private Date TO_DTTM;
	private String BTCH_DEST_CODE;
	private String BTCH_MAST_FILE_STTS;
	private Integer PAGE_NO; 
	private Integer PAGE_SIZE;
	
	//Configurable implementation : file (billerInquiryWebConfig.properties)
	private String enableAuthorize;
	private String textFilePattern;
	private String summaryReportFilePattern;
	private String detailReportFilePattern;
	private String ignoreFilePattern;
	
	private List Listparam =new ArrayList<String>();	

	public List getListparam() {
		return Listparam;
	}
	public void setListparam(List listparam) {
		Listparam = listparam;
	}
	public Integer getBTCH_MAST_FILE_ID() {
		return BTCH_MAST_FILE_ID;
	}
	public void setBTCH_MAST_FILE_ID(Integer bTCH_MAST_FILE_ID) {
		BTCH_MAST_FILE_ID = bTCH_MAST_FILE_ID;
	}
	public Date getFROM_DTTM() {
		return FROM_DTTM;
	}
	public void setFROM_DTTM(Date fROM_DTTM) {
		FROM_DTTM = fROM_DTTM;
	}
	public Date getTO_DTTM() {
		return TO_DTTM;
	}
	public void setTO_DTTM(Date tO_DTTM) {
		TO_DTTM = tO_DTTM;
	}
	public Integer getPAGE_NO() {
		return PAGE_NO;
	}
	public void setPAGE_NO(Integer pAGE_NO) {
		PAGE_NO = pAGE_NO;
	}
	public Integer getPAGE_SIZE() {
		return PAGE_SIZE;
	}
	public void setPAGE_SIZE(Integer pAGE_SIZE) {
		PAGE_SIZE = pAGE_SIZE;
	}
	public String getBTCH_DEST_CODE() {
		return BTCH_DEST_CODE;
	}
	public void setBTCH_DEST_CODE(String tBTCH_DEST_CODE) {
		BTCH_DEST_CODE = tBTCH_DEST_CODE;
	}
	public String getBTCH_MAST_FILE_STTS() {
		return BTCH_MAST_FILE_STTS;
	}
	public void setBTCH_MAST_FILE_STTS(String tBTCH_MAST_FILE_STTS) {
		BTCH_MAST_FILE_STTS = tBTCH_MAST_FILE_STTS;
	}
	
	public String toString() {
		return BTCH_MAST_FILE_ID+"|"+FROM_DTTM+"|"+TO_DTTM+"|"+BTCH_DEST_CODE+"|"+BTCH_MAST_FILE_STTS+"|"+PAGE_NO+"|"
	+PAGE_SIZE
	+"Listparam|"+Listparam.size();
	}
	public String getEnableAuthorize() {
		return enableAuthorize;
	}
	public void setEnableAuthorize(String enableAuthorize) {
		this.enableAuthorize = enableAuthorize;
	}
	public String getTextFilePattern() {
		return textFilePattern;
	}
	public void setTextFilePattern(String textFilePattern) {
		this.textFilePattern = textFilePattern;
	}
	public String getSummaryReportFilePattern() {
		return summaryReportFilePattern;
	}
	public void setSummaryReportFilePattern(String summaryReportFilePattern) {
		this.summaryReportFilePattern = summaryReportFilePattern;
	}
	public String getDetailReportFilePattern() {
		return detailReportFilePattern;
	}
	public void setDetailReportFilePattern(String detailReportFilePattern) {
		this.detailReportFilePattern = detailReportFilePattern;
	}
	public String getIgnoreFilePattern() {
		return ignoreFilePattern;
	}
	public void setIgnoreFilePattern(String ignoreFilePattern) {
		this.ignoreFilePattern = ignoreFilePattern;
	}
}
