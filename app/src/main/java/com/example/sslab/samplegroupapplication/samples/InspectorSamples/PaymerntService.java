package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

/**
 * Created by SSLAB on 2017-03-02.
 */
public class PaymerntService {


    /**
     *
     * @param code 할인상품 코드값
     * @param productAmount 가격
     * @return
     */
    public Discount getDiscount (String code ,long productAmount){


        // 할인코드 (naver -10%, 다나와 -15퍼, 팬카페 1000원
        String discountCode = code;

        long discountAmount = getDisCountAmount(code,productAmount);

        Discount obj = new Discount(code,discountAmount);
        return obj;
    }


    public void payment(long productCode,long amount,boolean isDiscount){


        // 상품금액
        long productAmount = 0;

        if( isDiscount ){
            Discount discount = getDiscount("NAVER",1000);

            // 결재 금액
            long paymentAmount = productAmount * amount - discount.getDiscountAmount();

            // something process

        }
    }

    /**
     * 할인에 관한 정책분기를 결제 서비스 객체의 책임으로 두어야 하는 것인가 .
     * 할인과 결제는 분리되어야한다.
     * 추상적인 시각에서 책임 (역할)을 기반으로 분리한다.
     * 다시말해 getDiscountAmout 가 분리할 행위가 되고 , 추상화시키면 (interface) 된다.
     *
     * @param discountCode
     * @param productAmount
     * @return
     */
    private long getDisCountAmount(String discountCode,long productAmount){
        long discountAmount = 0;
        switch ( discountCode ){
            case "NAVER":
                discountAmount  = productAmount / 10;
                break;
            case "DANAWA":
                discountAmount  = productAmount / 20 * 3;
                break;
            case "FANCAFE":
                if( productAmount > 2000)
                    discountAmount = 2000;
                break;
        }

        return discountAmount;
    }

    public class Discount{
        private String code;
        private long discountAmount;

        public Discount(String code, long discountAmount) {
            this.code = code;
            this.discountAmount = discountAmount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(long discountAmount) {
            this.discountAmount = discountAmount;
        }
    }
}
