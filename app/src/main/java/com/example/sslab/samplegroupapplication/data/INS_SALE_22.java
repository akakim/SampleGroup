package com.example.sslab.samplegroupapplication.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SSLAB on 2016-12-23.
 */
public class INS_SALE_22 extends ICFO{

    private String	prgssStateGbn	;   //	진행상태구분
    private String	prgssStepCd	;       //	화면단계코드
    private String	carRegNo	;       //	자동차등록번호
    private String	custNm	;           //	고객명
    private String	listCnt	;           //	요청할 목록의 수
    private String	beginIdx	;       //	시작할 index

    //out data.
//	private int listCnt;
//	private int beginIdx;
    private int endIdx;
    private int itemCnt;
    private int totalCnt;
    private int nextYn;

    private ArrayList<TodoListSale> todoListSales;

    @Override
    public JSONObject getJSONParams() {
        try{
            jsonObject.put("ikenId",ikenId);
            jsonObject.put("prgssStateGbn",prgssStateGbn);
            jsonObject.put("prgssStepCd",prgssStepCd);
            jsonObject.put("carRegNo",carRegNo);
            jsonObject.put("custNm",custNm);
            jsonObject.put("listCnt",listCnt);
            jsonObject.put("beginIdx",beginIdx);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    public class TodoListSale{
        public String prgssStateCd;
        public String prgssStateNm;
        public String prgssStepCd;		//할일구분코드
        public String prgssStepNm;		//할일구분
        public String carRegNo;		    //자동차등록번호
        public String modelMarkNm;		//모델명
        public String makcomMarkNm;		//제조사
        public String vhcleSn;			//차량일련번호
        public String invNo;			//재고번호
        public String stockGbNm;		//입고상태명
        public String custNm;			//고객명
        public String saleStateNm;		//판매상태명
        public String vhclePrdtnYr;		//연식
        public String saleReqNo;		//판매신청번호
    }

    public String getPrgssStateGbn() {
        return prgssStateGbn;
    }

    public void setPrgssStateGbn(String prgssStateGbn) {
        this.prgssStateGbn = prgssStateGbn;
    }

    public String getPrgssStepCd() {
        return prgssStepCd;
    }

    public void setPrgssStepCd(String prgssStepCd) {
        this.prgssStepCd = prgssStepCd;
    }

    public String getCarRegNo() {
        return carRegNo;
    }

    public void setCarRegNo(String carRegNo) {
        this.carRegNo = carRegNo;
    }

    public String getCustNm() {
        return custNm;
    }

    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }

    public String getListCnt() {
        return listCnt;
    }

    public void setListCnt(String listCnt) {
        this.listCnt = listCnt;
    }

    public String getBeginIdx() {
        return beginIdx;
    }

    public void setBeginIdx(String beginIdx) {
        this.beginIdx = beginIdx;
    }
}
