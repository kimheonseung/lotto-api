package com.devh.api.lotto.constant;

/**
 * <pre>
 * Description :
 *     로또 관련 상수 모음 클래스
 * ===============================================
 * Member fields :
 *     LottoMethod
 *     LottoRank
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021-04-10
 * </pre>
 */
public class LottoConstant {

    /**
     * <pre>
     * Description :
     *     당첨 방식 관련 상수
     * ===============================================
     * Member fields :
     *     AUTO
     *     SEMI_AUTO
     *     MANUAL
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-03-09
     * </pre>
     */
    public enum LottoMethod {
        AUTO("자동", "auto"),
        SEMI_AUTO("반자동", "semi_auto"),
        MANUAL("수동", "manual");

        private final String koreanValue;
        private final String englishValue;
        LottoMethod(String koreanValue, String englishValue) {
            this.koreanValue = koreanValue;
            this.englishValue = englishValue;
        }

        public String getKoreanValue() {
            return koreanValue;
        }

        public String getEnglishValue() {
            return englishValue;
        }
    }

    /**
     * <pre>
     * Description :
     *     로또 순위 관련 상수
     * ===============================================
     * Member fields :
     *     FIRST
     *     SECOND
     *     THIRD
     *     FOURTH
     *     FIFTH
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-02-28
     * </pre>
     */
    public enum LottoRank {
        FIRST(1, "당첨번호 6개 숫자일치"),
        SECOND(2, "당첨번호 5개 숫자일치 + 보너스 숫자일치"),
        THIRD(3, "당첨번호 5개 숫자일치"),
        FOURTH(4, "당첨번호 4개 숫자일치"),
        FIFTH(5, "당첨번호 3개 숫자일치");

        private final int rank;
        private final String description;
        LottoRank(int rank, String description) {
            this.rank = rank;
            this.description = description;
        }

        public int getRank() {
            return rank;
        }
        public String getDescription() {
            return description;
        }
    }

    /**
     * <pre>
     * Description :
     *     lotto_result_store 컬럼명 또는 필드명 관련 상수
     * ===============================================
     * Member fields :
     *     private String rowId
     *     private Integer turn
     *     private Integer rank
     *     private String method
     *     private Integer storeNumber
     *     private String storeName
     *     private String storeAddress
     *     private String storeAddress1
     *     private String storeAddress2
     *     private String storeAddress3
     *     private String storePhone
     *     private String storeMapId
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-03-13
     * </pre>
     */
    public enum LottoResultStoreKey {
        ROW_ID("row_id", "rowId"),
        TURN("turn", "turn"),
        RANK("rank", "rank"),
        METHOD("method", "method"),
        STORE_NUMBER("store_number", "storeNumber"),
        STORE_NAME("store_name", "storeName"),
        STORE_ADDRESS("store_address", "storeAddress"),
        STORE_ADDRESS1("store_address1", "storeAddress1"),
        STORE_ADDRESS2("store_address2", "storeAddress2"),
        STORE_ADDRESS3("store_address3", "storeAddress3"),
        STORE_LOCATION("store_location", "storeLocation"),
        STORE_LOCATION_LAT("lat", "lat"),
        STORE_LOCATION_LON("lon", "lon"),
        STORE_PHONE("store_phone", "storePhone"),
        STORE_MAP_ID("store_map_id", "storeMapId");

        final String snakeCase;
        final String camelCase;
        LottoResultStoreKey(String snakeCase, String camelCase) {
            this.snakeCase = snakeCase;
            this.camelCase = camelCase;
        }

        public String getCamelCase() {
            return this.camelCase;
        }

        public String getSnakeCase() {
            return this.snakeCase;
        }
    }

    public enum LottoResultKey {
        TURN("turn", "turn"),
        DATE("date", "date"),
        NUMBER1("number1", "number1"),
        NUMBER2("number2", "number2"),
        NUMBER3("number3", "number3"),
        NUMBER4("number4", "number4"),
        NUMBER5("number5", "number5"),
        NUMBER6("number6", "number6"),
        NUMBER7("number7", "number7"),
        TOTAL_SALES_PRICE("total_sales_price", "totalSalesPrice"),
        AUTO_WINNER_COUNT("auto_winner_count", "autoWinnerCount"),
        SEMI_AUTO_WINNER_COUNT("semi_auto_winner_count", "semiAutoWinnerCount"),
        MANUAL_WINNER_COUNT("manual_winner_count", "manualWinnerCount");

        final String snakeCase;
        final String camelCase;
        LottoResultKey(String snakeCase, String camelCase) {
            this.snakeCase = snakeCase;
            this.camelCase = camelCase;
        }

        public String getCamelCase() {
            return camelCase;
        }

        public String getSnakeCase() {
            return snakeCase;
        }

        public static LottoResultKey getByCamelCase(String key) {
            LottoResultKey result = null;

            for(LottoResultKey value : values()) {
                if(value.getCamelCase().equals(key)) {
                    result = value;
                    break;
                }
            } 

            return result;
        }
        public static LottoResultKey getBySnakeCase(String key) {
            LottoResultKey result = null;

            for(LottoResultKey value : values()) {
                if(value.getSnakeCase().equals(key)) {
                    result = value;
                    break;
                }
            } 

            return result;
        }
    }

    public enum LottoResultDetailKey {
        ROW_ID("row_id", "rowId"),
        RANK("rank", "rank"),
        TOTAL_WINNER_COUNT("total_winner_count", "totalWinnerCount"),
        TOTAL_PRIZE("total_prize", "totalPrize"),
        PER_PERSON_PRIZE("per_person_prize", "perPersonPrize"),
        LOTTO_RESULT_TURN("lotto_result_turn", "lottoResultTurn");
        final String snakeCase;
        final String camelCase;
        LottoResultDetailKey(String snakeCase, String camelCase) {
            this.snakeCase = snakeCase;
            this.camelCase = camelCase;
        }

        public String getCamelCase() {
            return camelCase;
        }

        public String getSnakeCase() {
            return snakeCase;
        }

    }
}
