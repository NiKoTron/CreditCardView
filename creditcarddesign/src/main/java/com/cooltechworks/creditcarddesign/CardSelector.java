package com.cooltechworks.creditcarddesign;

import android.text.TextUtils;

import com.cooltechworks.creditcarddesign.interval.IntervalTree;

public class CardSelector {

    public static final CardSelector VISA = new CardSelector(R.drawable.card_color_round_rect_purple, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_visa_logo);
    public static final CardSelector MIR = new CardSelector(R.drawable.card_color_round_rect_blue, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_mir_logo);
    public static final CardSelector MASTER = new CardSelector(R.drawable.card_color_round_rect_pink, R.drawable.chip_yellow, R.drawable.chip_yellow_inner, android.R.color.transparent, R.drawable.ic_billing_mastercard_logo);
    public static final CardSelector AMEX = new CardSelector(R.drawable.card_color_round_rect_green, android.R.color.transparent, android.R.color.transparent, R.drawable.img_amex_center_face, R.drawable.ic_billing_amex_logo1);
    public static final CardSelector DEFAULT = new CardSelector(R.drawable.card_color_round_rect_default, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, android.R.color.transparent);


    public static final CardSelector UNION_PAY = new CardSelector(R.drawable.card_color_round_rect_green, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_unionpay_logo);
    public static final CardSelector DISCOVER = new CardSelector(R.drawable.card_color_round_rect_green, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_discover_logo);
    public static final CardSelector JCB = new CardSelector(R.drawable.card_color_round_rect_green, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_jcb_logo);

    private static IntervalTree<Integer, CardSelector> dictionary = null;

    private int mResCardId;
    private int mResChipOuterId;
    private int mResChipInnerId;
    private int mResCenterImageId;
    private int mResLogoId;
    
    public CardSelector(int mDrawableCard, int mDrawableChipOuter, int mDrawableChipInner, int mDrawableCenterImage, int logoId) {
        this.mResCardId = mDrawableCard;
        this.mResChipOuterId = mDrawableChipOuter;
        this.mResChipInnerId = mDrawableChipInner;
        this.mResCenterImageId = mDrawableCenterImage;
        this.mResLogoId = logoId;
    }


    public int getResCardId() {
        return mResCardId;
    }

    public void setResCardId(int mResCardId) {
        this.mResCardId = mResCardId;
    }

    public int getResChipOuterId() {
        return mResChipOuterId;
    }

    public void setResChipOuterId(int mResChipOuterId) {
        this.mResChipOuterId = mResChipOuterId;
    }

    public int getResChipInnerId() {
        return mResChipInnerId;
    }

    public void setResChipInnerId(int mResChipInnerId) {
        this.mResChipInnerId = mResChipInnerId;
    }

    public int getResCenterImageId() {
        return mResCenterImageId;
    }

    public void setResCenterImageId(int mResCenterImageId) {
        this.mResCenterImageId = mResCenterImageId;
    }

    public int getResLogoId() {
        return mResLogoId;
    }


    public void setResLogoId(int mResLogoId) {
        this.mResLogoId = mResLogoId;
    }

    public static IntervalTree<Integer, CardSelector> getCardDicitionary(){
        if(dictionary == null) {
            dictionary = new IntervalTree<>();
            dictionary.put(3400, 3499, AMEX);
            dictionary.put(3700, 3799, AMEX);
            dictionary.put(6200, 6299, UNION_PAY);
            dictionary.put(6011, DISCOVER);
            dictionary.put(6221, 6229, DISCOVER); //622126, 622925
            dictionary.put(6440, 6499, DISCOVER);
            dictionary.put(6500, 6599, DISCOVER);
            dictionary.put(3528, 3589, JCB);
            dictionary.put(2221, 2720, MASTER); //since 2017
            dictionary.put(5100, 5599, MASTER);
            dictionary.put(2200, 2204, MIR);
            dictionary.put(4000, 4999, VISA);
        }
        return dictionary;
    }

    public static CardSelector selectCard(String cardNumber) {

        CardSelector selector = DEFAULT;

        if(cardNumber != null && cardNumber.length() > 3) {

            String subs = cardNumber.substring(0, 4).replaceAll("\\D+", "");
            if(TextUtils.isEmpty(subs)){
                return selector;
            }

            selector = getCardDicitionary().search(Integer.parseInt(subs));

            if(selector == null){
                selector = DEFAULT;
            }

            if(selector != DEFAULT) {

                int[] drawables = { R.drawable.card_color_round_rect_brown, R.drawable.card_color_round_rect_green, R.drawable.card_color_round_rect_pink, R.drawable.card_color_round_rect_purple, R.drawable.card_color_round_rect_blue};
                int hash = cardNumber.substring(0,3).hashCode();

                if(hash<0) {
                    hash = hash * -1;
                }

                int index = hash % drawables.length;

                int chipIndex = hash % 3;
                int[] chipOuter = { R.drawable.chip, R.drawable.chip_yellow, android.R.color.transparent};
                int[] chipInner = { R.drawable.chip_inner, R.drawable.chip_yellow_inner,android.R.color.transparent};


                selector.setResCardId(drawables[index]);
                selector.setResChipOuterId(chipOuter[chipIndex]);
                selector.setResChipInnerId(chipInner[chipIndex]);
            }
        }
        return selector;
    }
}
