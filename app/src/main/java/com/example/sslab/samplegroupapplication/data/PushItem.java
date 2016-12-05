package com.example.sslab.samplegroupapplication.data;

/**
 * Created by SSLAB on 2016-12-05.
 */

public class PushItem {
    public Long	    pushSeqentialNumber;   //	푸시일련번호
    public String	pushFullContent;//	푸시내용[전체] 전체내용 - 공지(scrnMoveCd:3)인 경우 팝업시에 본 항목을 노출
    public String	pushSummaryContent;    //	푸시내용 축약내용- 공지(scrnMoveCd:3)인경우 축약이 적용
    public boolean	confirmYesOrNo;     //	확인여부  [미확인:0, 확인:1]
    public String	registDate;     //	등록일시 YYYY-MM-DD 오늘인 경우 'Today' 로 전달함


    public PushItem(Long pushSeqentialNumber, String pushFullContent, String pushSummaryContent, String registDate) {
        this.pushSeqentialNumber = pushSeqentialNumber;
        this.pushFullContent = pushFullContent;
        this.pushSummaryContent = pushSummaryContent;
        this.registDate = registDate;
        this.confirmYesOrNo = false;
    }

    public PushItem(Long pushSeqentialNumber, String pushFullContent, String pushSummaryContent, boolean confirmYesOrNo, String registDate) {
        this.pushSeqentialNumber = pushSeqentialNumber;
        this.pushFullContent = pushFullContent;
        this.pushSummaryContent = pushSummaryContent;
        this.confirmYesOrNo = confirmYesOrNo;
        this.registDate = registDate;
    }

    public Long getPushSeqentialNumber() {
        return pushSeqentialNumber;
    }

    public void setPushSeqentialNumber(Long pushSeqentialNumber) {
        this.pushSeqentialNumber = pushSeqentialNumber;
    }

    public String getPushFullContent() {
        return pushFullContent;
    }

    public void setPushFullContent(String pushFullContent) {
        this.pushFullContent = pushFullContent;
    }

    public String getPushSummaryContent() {
        return pushSummaryContent;
    }

    public void setPushSummaryContent(String pushSummaryContent) {
        this.pushSummaryContent = pushSummaryContent;
    }

    public boolean isConfirmYesOrNo() {
        return confirmYesOrNo;
    }

    public void setConfirmYesOrNo(boolean confirmYesOrNo) {
        this.confirmYesOrNo = confirmYesOrNo;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }
}
