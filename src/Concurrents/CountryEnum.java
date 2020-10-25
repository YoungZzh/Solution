package Concurrents;

/**
 * Author:Young
 * Date:2020/10/20-21:44
 */
public enum CountryEnum {
    ONE(0,"齐"),
    TWO(1,"楚"),
    THREE(2,"燕"),
    FOUR(3,"赵"),
    FIVE(4,"魏"),
    SIX(5,"韩")
    ;

    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum element : values){
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
